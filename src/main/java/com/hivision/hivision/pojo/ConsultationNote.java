package com.hivision.hivision.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConsultationNote {
    @Id
    @Column(name = "Phone")
    String phone;

    @Column(name = "Name")
    String name;

    @Column(name = "Email")
    String email;

    @Column(name = "Note")
    String note;

    @Column(name = "CreateAt")
    Instant createAt;

}
