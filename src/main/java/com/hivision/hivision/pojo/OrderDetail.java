package com.hivision.hivision.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "OrderDetails")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Access(AccessType.FIELD)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderDetailID", nullable = false)
    Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "OrderID", nullable = false)
    Order order;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ProductID", nullable = false)
    Product product;

    @NotNull
    @Column(name = "Quantity", nullable = false)
    Integer quantity;

    @NotNull
    @Column(name = "PriceAtTimeOfOrder", nullable = false, precision = 18, scale = 2)
    double priceAtTimeOfOrder;

}