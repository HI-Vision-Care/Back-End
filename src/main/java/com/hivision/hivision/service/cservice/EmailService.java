package com.hivision.hivision.service.cservice;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    // Gửi OTP
    public void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();

        // Thiết lập người gửi (từ cấu hình username trong application.yml)
        // message.setFrom("your.gmail.address@gmail.com"); // Không cần thiết lập nếu đã có trong cấu hình

        message.setTo(toEmail);
        message.setSubject("Mã OTP đặt lại mật khẩu của bạn");
        message.setText("Chào bạn,\n\nMã OTP của bạn là: " + otp +
                "\n\nMã này sẽ hết hạn sau 5 phút. Vui lòng không chia sẻ mã này.");

        mailSender.send(message);

        System.out.println("Gửi OTP thành công đến: " + toEmail);
    }
}
