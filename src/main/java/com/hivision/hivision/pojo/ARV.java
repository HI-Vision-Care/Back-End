package com.hivision.hivision.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Access(AccessType.FIELD)
public class ARV {
    @Id
    @Size(max = 255)
    @Column(name = "ARV_ID", nullable = false)
    private String arvId;

    @Size(max = 255)
    @Column(name = "GenericName")
    private String genericName;

    @Size(max = 50)
    @Column(name = "DrugClass", length = 50)
    private String drugClass;

    @Size(max = 50)
    @Column(name = "DosageStrength", length = 50)
    private String dosageStrength;

    @Size(max = 50)
    @Column(name = "AdmRoute", length = 50)
    private String admRoute;

    @Size(max = 255)
    @Column(name = "RcmDosage")
    private String rcmDosage;

    @Size(max = 50)
    @Column(name = "ShelfLife", length = 50)
    private String shelfLife;

    @Size(max = 255)
    @Column(name = "FundingSource")
    private String fundingSource;

    @Size(max = 50)
    @Column(name = "RegimenLevel", length = 50)
    private String regimenLevel;

    @Column(name = "LastUpdated")
    private Instant lastUpdated;

}