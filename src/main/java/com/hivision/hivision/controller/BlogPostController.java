package com.hivision.hivision.controller;

import com.hivision.hivision.dto.BlogPostDTO;
import com.hivision.hivision.payload.request.BlogPostDataWrapper;
import com.hivision.hivision.payload.request.BlogPostRequest;
import com.hivision.hivision.payload.request.ContentRequest;
import com.hivision.hivision.payload.response.BlogPostResponse;
import com.hivision.hivision.pojo.BlogPost;
import com.hivision.hivision.service.iservice.IBlogPostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog-post")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogPostController {
    IBlogPostService blogPostService;

    @PostMapping("/create/{accountID}")
    public ResponseEntity<Void> createBlogPost(@RequestBody BlogPostDataWrapper dataWrapper, @PathVariable String accountID) {
        BlogPostRequest blogPostRequest = dataWrapper.getBlogPostRequest();
        List<ContentRequest> contentRequests = dataWrapper.getContentRequests();
        blogPostService.createBlogPost(blogPostRequest,contentRequests, accountID);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/content/{blogID}")
    public ResponseEntity<BlogPostResponse> getAllBlogPostContent(@PathVariable int blogID) {
        return ResponseEntity.ok(blogPostService.getContentByBlog(blogID));
    }

    @GetMapping()
    public ResponseEntity<List<BlogPostDTO>> getAllBlogPost() {
        return ResponseEntity.ok(blogPostService.getAllBlogPost());
    }
}
