package com.hivision.hivision.pojo;

import com.hivision.hivision.enums.BlogStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;

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

    @Size(max = 255)
    @Column(name = "Title")
    String title;

    @Size(max = 255)
    @Column(name = "Topic")
    String topic;

    @Lob
    @Column(name = "Banner")
    String banner;

    @Builder.Default
    @Column(name = "CreateAt")
    Instant createAt = Instant.now();

//    @Builder.Default
    @Column(name = "isHide")
    Boolean isHide;

    @Builder.Default
    @Column(name = "Status")
    BlogStatus status = BlogStatus.PENDING;
}