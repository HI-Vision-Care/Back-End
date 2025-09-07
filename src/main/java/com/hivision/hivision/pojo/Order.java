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
@Table(name = "Orders")
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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "FacilityID", nullable = false)
    Facility facilityID;

    @ManyToOne
    @JoinColumn(name = "AccountID")
    Account account;

    @Column(name = "OrderDate")
    Instant orderDate;

    @Size(max = 50)
    @Nationalized
    @Column(name = "Status", length = 50)
    String status;

    @Column(name = "TotalAmount", precision = 18, scale = 2)
    double totalAmount;

    @Size(max = 500)
    @Nationalized
    @Column(name = "ShippingAddress", length = 500)
    String shippingAddress;


}