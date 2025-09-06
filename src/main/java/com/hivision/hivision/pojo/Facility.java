package com.hivision.hivision.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
public class Facility {
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


    @Column(name = "Description", length = 50)
    String des;

    @Size(max = 255)
    @Column(name = "Img")
    String img;

    @Size(max = 50)
    @Column(name = "\"Time\"", length = 50)
    String time;

    @Size(max = 10)
    @Column(name = "Rating", length = 10)
    String rating;

    @Lob
    @Column(name = "Latitude")
    String latitude;

    @Lob
    @Column(name = "Longitude")
    String longitude;
}