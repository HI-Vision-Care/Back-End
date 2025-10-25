package com.hivision.hivision.controller;

import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.pojo.Transactions;
import com.hivision.hivision.service.cservice.PayosPaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentWebhookController {
    private final PayosPaymentService paymentService;

    @PostMapping("/payos_transfer_handler")
    public ResponseEntity<String> payosWebhook(@RequestBody Object body) {
//        try {
//            // TẠM THỜI DỪNG HÀM XỬ LÝ CHÍNH
//            // paymentService.handleWebhook(body);
//
//            // Ghi log để kiểm tra Payload sau này
//            System.out.println("Webhook Received (Test Mode): " + body.toString());
//
//            // LUÔN TRẢ VỀ 200 OK để PayOS chấp nhận URL
//            return ResponseEntity.ok("Webhook URL check successful (200 OK)");
//
//        } catch (Exception e) {
//            // Ghi log lỗi, nhưng vẫn trả về 200 OK để PayOS chấp nhận URL
//            System.err.println("Error during Webhook handling (Test Mode): " + e.getMessage());
//            return ResponseEntity.ok("Received but error occurred (200 OK)");
//        }
        try {
            paymentService.handleWebhook(body);
            return ResponseEntity.ok("Webhook processed successfully");
        } catch (Exception e) {
            // log
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing webhook: " + e.getMessage());
        }
    }

    // ✅ THÊM PHƯƠNG THỨC GET NÀY (Dành cho PayOS kiểm tra URL)
    @GetMapping("/payos_transfer_handler")
    public ResponseEntity<String> payosWebhookCheck() {
        // Trả về 200 OK. PayOS chỉ cần một phản hồi 200 thành công.
        return ResponseEntity.ok("Webhook GET check successful (200 OK)");
    }

    @GetMapping("/status/{orderCode}")
    public ResponseEntity<Map<String, Object>> getTransactionStatus(@PathVariable Long orderCode) {
        try {
            Transactions tx = paymentService.getTransactionStatus(orderCode);

            // Trả về trạng thái để FE hiển thị: COMPLETED, PENDING, FAILED
            return ResponseEntity.ok(Map.of(
                    "status", tx.getStatus(),
                    "amount", tx.getAmount(),
                    "transactionId", tx.getTransactionId()
            ));
        } catch (AppException e) {
            // Xử lý khi không tìm thấy giao dịch
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("status", "NOT_FOUND", "message", "Transaction not found"));
        } catch (Exception e) {
            // Log lỗi chi tiết ở đây
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("status", "ERROR", "message", "Internal server error"));
        }
    }
}
