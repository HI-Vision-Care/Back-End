package com.hivision.hivision.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

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
    @Column(name = "Price", nullable = false, precision = 18, scale = 2)
    double price;

    @Size(max = 50)
    @Nationalized
    @Column(name = "Unit", length = 50)
    String unit;

    @ManyToOne
    @JoinColumn(name = "CategoryID")
    Category category;

    @ManyToOne
    @JoinColumn(name = "SupplierID")
    Supplier supplier;

    @Lob
    @Column(name = "ImageUrl")
    String imageUrl;

    @ColumnDefault("1")
    @Column(name = "IsActive")
    Boolean isActive;


}