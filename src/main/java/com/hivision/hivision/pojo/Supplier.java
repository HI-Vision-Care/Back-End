package com.hivision.hivision.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "Suppliers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Access(AccessType.FIELD)
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SupplierID", nullable = false)
    Integer id;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "SupplierName", nullable = false)
    String supplierName;

    @Size(max = 500)
    @Nationalized
    @Column(name = "ContactInfo", length = 500)
    String contactInfo;


}