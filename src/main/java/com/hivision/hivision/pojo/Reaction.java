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
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReactID", nullable = false)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "BlogID")
    BlogPost blog;

    @ManyToOne
    @JoinColumn(name = "AccountID")
    Account account;

    @Column(name = "React")
    Boolean react;



}