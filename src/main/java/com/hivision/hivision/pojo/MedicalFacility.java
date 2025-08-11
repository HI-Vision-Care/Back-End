package com.hivision.hivision.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicalFacility {
    @Id
    @Size(max = 255)
    @Column(name = "FacilityID", nullable = false)
    String facilityID;

    @Size(max = 255)
    @Column(name = "Name")
    String name;

    @Size(max = 255)
    @Column(name = "Address")
    String address;

    @Size(max = 50)
    @Column(name = "Phone", length = 50)
    String phone;

}