package com.hivision.hivision.pojo;

import com.hivision.hivision.enums.BlogStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Nationalized;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AccountID")
    Account account;

    @Size(max = 255)
    @Nationalized
    @Column(name = "Title")
    String title;

    @Size(max = 255)
    @Nationalized
    @Column(name = "Topic")
    String topic;

    @Lob
    @Column(name = "Banner")
    String banner;

    @Column(name = "CreateAt")
    Instant createAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    BlogStatus status;

    @Column(name = "isHide")
    Boolean hide;

    @Column(name = "Total")
    Integer total;


}