package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.WalletDTO;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.enums.TransactionsEnum;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.IWalletMapper;
import com.hivision.hivision.payload.request.WalletRequest;
import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.pojo.Transactions;
import com.hivision.hivision.pojo.Wallet;
import com.hivision.hivision.repository.IAccountRepo;
import com.hivision.hivision.repository.ITransactionsRepo;
import com.hivision.hivision.repository.IWalletRepo;
import com.hivision.hivision.service.iservice.IWalletService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WalletService implements IWalletService {

    IAccountRepo accountRepo;
    IWalletRepo walletRepo;
    IWalletMapper walletMapper;
    ITransactionsRepo transactionsRepo;


    @Override
    public String createUrl(WalletRequest walletRequest, String accountId) throws Exception {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Wallet wallet = walletRepo.findWalletByAccount(account);
        if (wallet == null) {
            throw new AppException(ErrorCode.WALLET_NOT_FOUND);
        }

        Transactions transactions = Transactions.builder()
                .amount(walletRequest.getBalance())
                .description("Deposit to Wallet")
                .type(TransactionsEnum.DEPOSIT)
                .status("PENDING")
                .date(Instant.now())
                .wallet(wallet)
                .build();

        Transactions savedTransaction = transactionsRepo.save(transactions);
        String transactionId = savedTransaction.getTransactionId();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String createDate = LocalDateTime.now().format(formatter);

        // Payment parameters
        String tmnCode = "8TLX9C45";
        String secretKey = "OIHP9HPL5O7QYVG4HIZ49LIT1LRG6GAU";
        String vnpUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";

        // Return URL after payment
        String returnUrl = "http://10.87.52.113:8081/success?transactionId=" + transactionId;
//        String returnUrl = "http://192.168.222.57:8081/success?transactionId=" + transactionId;

        String currCode = "VND";

        // Create payment parameters map
        Map<String, String> vnpParams = new TreeMap<>();
        vnpParams.put("vnp_Version", "2.1.0");
        vnpParams.put("vnp_Command", "pay");
        vnpParams.put("vnp_TmnCode", tmnCode);
        vnpParams.put("vnp_Locale", "vn");
        vnpParams.put("vnp_CurrCode", currCode);
        vnpParams.put("vnp_TxnRef",  transactionId);
        vnpParams.put("vnp_OrderInfo", "Nap Tien Vao Vi: " + accountId);
        vnpParams.put("vnp_OrderType", "other");
        vnpParams.put("vnp_Amount", String.valueOf((int) (walletRequest.getBalance() * 100))); // Convert to integer for VNPay


        vnpParams.put("vnp_ReturnUrl", returnUrl);
        vnpParams.put("vnp_CreateDate", createDate);
        vnpParams.put("vnp_IpAddr", "128.199.178.23");

        // Generate secure hash
        String signData = buildQueryString(vnpParams);
        String signed = generateHMAC(secretKey, signData);
        vnpParams.put("vnp_SecureHash", signed);

        String paymentUrl = vnpUrl + "?" + buildQueryString(vnpParams); // dùng lại luôn
        return paymentUrl;

    }

    private String buildQueryString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            builder.append("&");
        }
        builder.deleteCharAt(builder.length() - 1); // remove last '&'
        return builder.toString();
    }

    private String generateHMAC(String secretKey, String signData) throws NoSuchAlgorithmException, InvalidKeyException {

        Mac hmacSha512 = Mac.getInstance("HmacSHA512");
        SecretKeySpec KeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmacSha512.init(KeySpec);

        byte[] hmacBytes = hmacSha512.doFinal(signData.getBytes(StandardCharsets.UTF_8));

        StringBuilder result = new StringBuilder();
        for (byte b : hmacBytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    @Override
    public void handleVnPayCallback(String transactionId, String responseCode, double vnpAmount) {
        //Lấy giao dịch từ ID
        Transactions transactions = transactionsRepo.findById(transactionId)
                .orElseThrow(() -> new AppException(ErrorCode.TRANSACTION_NOT_FOUND));

        if(transactions.getStatus().equals("COMPLETED")){
            throw new AppException(ErrorCode.TRANSACTION_COMPLETED);
        }

        Wallet wallet = transactions.getWallet();

        if ("00".equals(responseCode)) {
            // Cập nhật số dư ví và trạng thái giao dịch
            double amount = vnpAmount / 100;  // VNPay gửi số tiền * 100

            wallet.setBalance(wallet.getBalance() + amount);
            walletRepo.save(wallet);  // Lưu ví với số dư mới

            transactions.setStatus("COMPLETED");
        } else {
            // Giao dịch thất bại
            transactions.setStatus("FAILED");
            transactions.setDescription("Fail Deposit to Wallet");
        }

        // Lưu giao dịch với trạng thái mới
        transactionsRepo.save(transactions);
    }

    @Override
    public WalletDTO getWallet(String accountId) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Wallet wallet = walletRepo.findWalletByAccount(account);

        return walletMapper.toWalletDTO(wallet);
    }

    @Override
    public void deposit(String accountId, WalletRequest request) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Wallet wallet = walletRepo.findWalletByAccount(account);

        wallet.setBalance(wallet.getBalance() + request.getBalance());
        walletRepo.save(wallet);
    }

    @Override
    public void withdraw(String accountId, double amount) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Wallet wallet = walletRepo.findWalletByAccount(account);
        if (wallet.getBalance() < amount) {
            throw new AppException(ErrorCode.INSUFFICIENT_BALANCE);
        }

        wallet.setBalance(wallet.getBalance() - amount);

        Transactions transactions = Transactions.builder()
                .wallet(wallet)
                .amount(-amount)
                .description("Withdraw from Wallet")
                .type(TransactionsEnum.WITHDRAWAL)
                .status("COMPLETED")
                .date(Instant.now())
                .build();

        walletRepo.save(wallet);
        transactionsRepo.save(transactions);
    }
}
