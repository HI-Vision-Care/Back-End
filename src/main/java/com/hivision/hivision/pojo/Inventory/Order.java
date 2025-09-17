package com.hivision.hivision.pojo.Inventory;

import com.hivision.hivision.pojo.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "\"Order\"")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Access(AccessType.FIELD)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID", nullable = false)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "PatientID")
    Patient patient;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ProductID", nullable = false, referencedColumnName = "ProductID")
    Product product;

    @NotNull
    @Column(name = "Quantity", nullable = false)
    Integer quantity;

    @NotNull
    @Column(name = "TotalAmount", nullable = false)
    Double amount;

}