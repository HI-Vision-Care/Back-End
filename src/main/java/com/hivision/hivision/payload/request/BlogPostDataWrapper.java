package com.hivision.hivision.payload.request;

import com.hivision.hivision.pojo.BlogPost;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogPostDataWrapper {
    BlogPostRequest blogPostRequest;
    List<ContentRequest> contentRequests;
}
