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
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BlogID", nullable = false)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "AccountID")
    Account account;

    @Size(max = 100)
    @Column(name = "Title")
    String title;

    @Size(max = 100)
    @Column(name = "Topic")
    String topic;

    @Size(max = 255)
    @Column(name = "Banner")
    String banner;

    @Builder.Default
    @Column(name = "CreateAt")
    Instant createAt = Instant.now();

}