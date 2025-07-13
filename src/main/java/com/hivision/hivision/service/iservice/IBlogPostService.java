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
    void updateBlogPost(BlogPostRequest blogPostRequest, List<ContentRequest> contentRequests, String accountID,int blogID);
    void hideBlogPost(int blogID);
    void showBlogPost(int blogID);
    void approveBlogPost(int blogID,String accountID);
    void rejectBlogPost(int blogID,String accountID);
    void adjustBlogPost(int blogID,String accountID);
    BlogPostResponse getContentByBlog(int blogID);
    List<BlogPostDTO> getAllBlogPost();
}