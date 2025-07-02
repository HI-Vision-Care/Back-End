package com.hivision.hivision.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "ARV_Indications")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Access(AccessType.FIELD)
public class ArvIndication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IndicationID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARV_ID")
    private ARV arv;

    @Size(max = 255)
    @Column(name = "Indication")
    private String indication;

}