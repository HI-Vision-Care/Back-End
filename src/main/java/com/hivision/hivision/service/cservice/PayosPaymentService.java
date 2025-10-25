package com.hivision.hivision.service.cservice;

import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.enums.TransactionsEnum;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.pojo.Transactions;
import com.hivision.hivision.pojo.Wallet;
import com.hivision.hivision.repository.IAccountRepo;
import com.hivision.hivision.repository.ITransactionsRepo;
import com.hivision.hivision.repository.IWalletRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.payos.PayOS;
import vn.payos.model.v2.paymentRequests.CreatePaymentLinkRequest;
import vn.payos.model.v2.paymentRequests.CreatePaymentLinkResponse;
import vn.payos.model.v2.paymentRequests.PaymentLinkItem;
import vn.payos.model.webhooks.WebhookData;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PayosPaymentService {
    private final PayOS payOS;
    private final ITransactionsRepo transactionsRepo;
    private final IWalletRepo walletRepo;
    private final IAccountRepo accountRepo;

    @Transactional
    public String createPaymentLink(String accountId, long amount, String returnUrl, String cancelUrl) throws Exception {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Wallet wallet = walletRepo.findWalletByAccount(account);
        if (wallet == null) throw new AppException(ErrorCode.WALLET_NOT_FOUND);

        long orderCode = System.currentTimeMillis() / 1000; // unique order code
        Transactions tx = Transactions.builder()
                .amount(amount)
                .description("Top-up wallet")
                .type(TransactionsEnum.DEPOSIT)
                .status("PENDING")
                .date(Instant.now())
                .wallet(wallet)
                .payosOrderCode(orderCode) // <-- LƯU MÃ SỐ NGUYÊN NÀY VÀO DB
                .build();
        transactionsRepo.save(tx);

        // Build PayOS request (use SDK classes; adapt to actual SDK names)
        CreatePaymentLinkRequest paymentData = CreatePaymentLinkRequest.builder()
                .orderCode(orderCode)
                .amount(amount)
                .description("Topup wallet")
                .returnUrl(returnUrl + "?orderCode=" + orderCode)   // FE return
                .cancelUrl(cancelUrl + "?orderCode=" + orderCode)
                .item(PaymentLinkItem.builder().name("Wallet top-up").price(amount).quantity(1).build())
                .build();

        CreatePaymentLinkResponse resp = payOS.paymentRequests().create(paymentData);
        String checkoutUrl = resp.getCheckoutUrl();
        // optionally store checkoutUrl in tx for audit
        tx.setDescription(tx.getDescription() + " | checkoutUrl=" + checkoutUrl);
        transactionsRepo.save(tx);

        return checkoutUrl;
    }

    @Transactional
    public void handleWebhook(Object body) throws Exception {

        System.out.println("📩 PayOS Webhook received: " + body);

        WebhookData data = payOS.webhooks().verify(body);
        System.out.println("✅ Verified webhook: orderCode=" + data.getOrderCode() + ", code=" + data.getCode());

        Long orderCode = data.getOrderCode();
        String paymentStatus = data.getCode();
        String desc = data.getDesc();

        Transactions tx = transactionsRepo.findByPayosOrderCode(orderCode).orElse(null);
        if (tx == null) {
            System.out.println("⚠️ Transaction not found for orderCode: " + orderCode);
            return;
        }

        if ("COMPLETED".equalsIgnoreCase(tx.getStatus())) {
            System.out.println("ℹ️ Already completed: " + orderCode);
            return;
        }

        if ("00".equals(paymentStatus)) {
            Wallet wallet = tx.getWallet();
            wallet.setBalance(wallet.getBalance() + tx.getAmount());
            walletRepo.save(wallet);
            tx.setStatus("COMPLETED");
            tx.setDescription("Thanh toán thành công: " + desc);
        } else {
            tx.setStatus("FAILED");
            tx.setDescription("Thanh toán thất bại: " + desc);
        }

        tx.setDate(Instant.now());
        transactionsRepo.save(tx);
        System.out.println("💾 Transaction updated: " + tx.getStatus());

//        // Verify using SDK
//        WebhookData data = payOS.webhooks().verify(body);
//
//        // Extract order code and status from webhook payload
//        Long orderCode = data.getOrderCode(); // adapt to actual API
//        String paymentStatus = data.getCode(); // mã trạng thái: "00" = thành công, khác = thất bại
//        String desc = data.getDesc(); // mô tả trạng thái
//        long amount = data.getAmount();
//
//        Transactions tx = transactionsRepo.findByPayosOrderCode(orderCode).orElse(null);
//        if (tx == null) {
//            // log and maybe create record
//            return;
//        }
//
//        // Idempotency: if already COMPLETED, just ignore
//        if ("COMPLETED".equalsIgnoreCase(tx.getStatus())) return;
//
//        if ("00".equals(paymentStatus)) {
//            Wallet wallet = tx.getWallet();
//            wallet.setBalance(wallet.getBalance() + tx.getAmount());
////            wallet.setBalance(wallet.getBalance() + (double) tx.getAmount() / 100.0); // if units differ
//            walletRepo.save(wallet);
//
//            tx.setStatus("COMPLETED");
//            tx.setDescription("Thanh toán thành công: " + desc);
//            tx.setDate(Instant.now());
//            transactionsRepo.save(tx);
//        } else {
//            tx.setStatus("FAILED");
//            tx.setDescription("Thanh toán thất bại: " + desc);
//            tx.setDate(Instant.now());
//            transactionsRepo.save(tx);
//        }
    }

    public Transactions getTransactionStatus(Long orderCode) {
        // Giả định bạn đã có findByPayosOrderCode trong ITransactionsRepo
        return transactionsRepo.findByPayosOrderCode(orderCode)
                .orElseThrow(() -> new AppException(ErrorCode.TRANSACTION_NOT_FOUND));
    }
}
