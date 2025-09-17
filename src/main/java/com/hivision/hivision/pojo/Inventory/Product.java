package com.hivision.hivision.pojo.Inventory;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "Products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Access(AccessType.FIELD)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID", nullable = false)
    Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "SKU", nullable = false)
    String sku;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "ProductName", nullable = false)
    String productName;

    @Nationalized
    @Lob
    @Column(name = "Description")
    String description;

    @NotNull
    @Column(name = "Price", nullable = false)
    Double price;

    @Size(max = 50)
    @Nationalized
    @Column(name = "Unit", length = 50)
    String unit;

    @Lob
    @Column(name = "ImageUrl")
    String imageUrl;

    @Builder.Default
    @Column(name = "IsActive")
    Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "CategoryID")
    Category category;

    @ManyToOne
    @JoinColumn(name = "SupplierID")
    Supplier supplier;
}