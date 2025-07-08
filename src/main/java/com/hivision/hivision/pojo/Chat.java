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
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"No\"", nullable = false)
    Integer id;

    @Size(max = 50)
    @Column(name = "Name", length = 50)
    String name;

    @Lob
    @Column(name = "Message")
    String message;

    @Column(name = "\"Date\"")
    Instant date;

    @ManyToOne
    @JoinColumn(name = "ChatID")
    ChatBox chatID;

}