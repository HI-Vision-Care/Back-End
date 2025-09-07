package com.hivision.hivision.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "StockTransactions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Access(AccessType.FIELD)
public class StockTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionID", nullable = false)
    Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "InventoryID", nullable = false)
    Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "AccountID")
    Account account;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "TransactionType", nullable = false, length = 50)
    String transactionType;

    @NotNull
    @Column(name = "QuantityChange", nullable = false)
    Integer quantityChange;

    @Size(max = 500)
    @Nationalized
    @Column(name = "Reason", length = 500)
    String reason;

    @Column(name = "TransactionDate")
    Instant transactionDate;

}