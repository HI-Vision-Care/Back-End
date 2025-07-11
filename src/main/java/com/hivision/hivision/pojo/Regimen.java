package com.hivision.hivision.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Regimen {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "RegimenID", nullable = false)
    private String id;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "RegimenName", nullable = false)
    private String regimenName;

    @Size(max = 100)
    @Nationalized
    @Column(name = "RegimenLevel", length = 100)
    private String regimenLevel;

    @Size(max = 500)
    @Nationalized
    @Column(name = "ARVList", length = 500)
    private String arvList;

    @Nationalized
    @Lob
    @Column(name = "Indication")
    private String indication;

    @Nationalized
    @Lob
    @Column(name = "RecommendedDosage")
    private String recommendedDosage;

    @ColumnDefault("get date()")
    @Column(name = "LastUpdated")
    private Instant lastUpdated;

}