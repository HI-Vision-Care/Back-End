package com.hivision.hivision.pojo;

import jakarta.persistence.*;
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
@Access(AccessType.FIELD)
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContentID", nullable = false)
    Integer id;

    @Size(max = 255)
    @Column(name = "Header")
    String header;

    @Lob
    @Column(name = "Body")
    String body;

    @Lob
    @Column(name = "Photo")
    String photo;

    @ManyToOne
    @JoinColumn(name = "BlogID")
    BlogPost blogPost;
}