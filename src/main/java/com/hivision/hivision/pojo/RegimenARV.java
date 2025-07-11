package com.hivision.hivision.pojo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegimenARV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RegArvID", nullable = false)
    Long regArvID;

    @ManyToOne
    @JoinColumn(name = "RegimenID")
    Regimen regimen;

    @ManyToOne
    @JoinColumn(name = "ARV_ID")
    ARV arv;
}
