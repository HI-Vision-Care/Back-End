package com.hivision.hivision.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "ARV")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Arv {
    @Id
    @Size(max = 255)
    @Column(name = "ARV_ID", nullable = false)
    String arvId;

    @Size(max = 255)
    @Column(name = "GenericName")
    String genericName;

    @Size(max = 50)
    @Column(name = "DrugClass", length = 50)
    String drugClass;

    @Size(max = 50)
    @Column(name = "DosageStrength", length = 50)
    String dosageStrength;

    @Size(max = 50)
    @Column(name = "AdmRoute", length = 50)
    String admRoute;

    @Size(max = 255)
    @Column(name = "RcmDosage")
    String rcmDosage;

    @Size(max = 50)
    @Column(name = "ShelfLife", length = 50)
    String shelfLife;

    @Size(max = 255)
    @Column(name = "FundingSource")
    String fundingSource;

    @Size(max = 50)
    @Column(name = "RegimenLevel", length = 50)
    String regimenLevel;

    @Column(name = "Last_Updated")
    Instant lastUpdated;

}