package com.hivision.hivision.pojo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // OTP
    @Column(nullable = false, unique = true)
    String token;

    // Thời gian hết hạn
    @Column(nullable = false)
    LocalDateTime expiryDate;

    // Liên kết với Account (Mối quan hệ 1-1)
    @OneToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "AccountID")
    Account account;

    // Thêm phương thức kiểm tra hết hạn (ví dụ: 5 phút)
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryDate);
    }
}
