package com.hivision.hivision.pojo;


import com.hivision.hivision.enums.TransactionsEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "TransactionID")
    String transactionId;

    @Column(name = "Amount")
    double amount;

    @Column(name = "Description")
    String description;

    @Enumerated(EnumType.STRING)
    TransactionsEnum type;

    @Column(name = "Status")
    String status;

    @Column(name = "Date")
    Instant date;

    @ManyToOne
    @JoinColumn(name = "WalletID",referencedColumnName = "WalletID")
    Wallet wallet;
}
