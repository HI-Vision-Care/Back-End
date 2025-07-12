package com.hivision.hivision.pojo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DiseaseID", nullable = false)
    Long diseaseID;

    @Column(name = "Name")
    String name;
}
