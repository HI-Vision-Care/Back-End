package com.hivision.hivision.service.iservice;

import com.hivision.hivision.dto.BlogPostDTO;
import com.hivision.hivision.payload.request.BlogPostDataWrapper;
import com.hivision.hivision.payload.request.BlogPostRequest;
import com.hivision.hivision.payload.request.ContentRequest;
import com.hivision.hivision.payload.response.BlogPostResponse;
import com.hivision.hivision.pojo.BlogPost;

import java.util.List;

public interface IBlogPostService {
    void createBlogPost(BlogPostRequest blogPostRequest, List<ContentRequest> contentRequests, String accountID);
    BlogPostResponse getContentByBlog(int blogID);
    List<BlogPostDTO> getAllBlogPost();
}