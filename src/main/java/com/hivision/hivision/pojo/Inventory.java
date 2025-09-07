package com.hivision.hivision.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Access(AccessType.FIELD)
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InventoryID", nullable = false)
    Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ProductID", nullable = false)
    Product product;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "FacilityID", nullable = false)
    Facility facility;

    @NotNull
    @Column(name = "Quantity", nullable = false)
    Integer quantity;

    @Column(name = "LowStockThreshold")
    Integer lowStockThreshold;

    @Column(name = "ExpiryDate")
    LocalDate expiryDate;


    @Column(name = "LastUpdated")
    Instant lastUpdated;



}