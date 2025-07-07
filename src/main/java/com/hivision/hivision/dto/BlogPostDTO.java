package com.hivision.hivision.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hivision.hivision.pojo.BlogPost;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link BlogPost}
 */
@Value
@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogPostDTO implements Serializable {
    Integer id;
    String author;
    @Size(max = 100)
    String title;
    @Size(max = 100)
    String topic;
    @Size(max = 255)
    String banner;
    Instant createAt;
}