package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.BlogPostDTO;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.IBlogMapper;
import com.hivision.hivision.payload.request.BlogPostRequest;
import com.hivision.hivision.payload.request.ContentRequest;
import com.hivision.hivision.payload.response.BlogPostResponse;
import com.hivision.hivision.payload.response.ContentResponse;
import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.pojo.BlogPost;
import com.hivision.hivision.pojo.Content;
import com.hivision.hivision.repository.IAccountRepo;
import com.hivision.hivision.repository.IBlogPostRepo;
import com.hivision.hivision.repository.IContentRepo;
import com.hivision.hivision.service.iservice.IBlogPostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class BlogPostService implements IBlogPostService {
    IBlogPostRepo blogPostRepo;
    IContentRepo contentRepo;
    IAccountRepo accountRepo;

    IBlogMapper blogMapper;

    @Override
    public void createBlogPost(BlogPostRequest request, List<ContentRequest> contentRequests, String accountID) {
        Account account = accountRepo.findById(accountID)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        BlogPost blogPost = BlogPost.builder()
                .account(account)
                .title(request.getTitle())
                .banner(request.getBanner())
                .topic(request.getTopic())
                .build();
        blogPostRepo.save(blogPost);
        for (ContentRequest contentRequest : contentRequests) {
            contentRepo.save(Content.builder()
                    .blogPost(blogPost)
                    .header(contentRequest.getHeader())
                    .body(contentRequest.getBody())
                    .photo(contentRequest.getPhoto())
                    .build());
        }


    }

    @Override
    public BlogPostResponse getContentByBlog(int blogID) {
        BlogPost blogPost = blogPostRepo.findById(blogID)
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));
        Account account = blogPost.getAccount();
        List<Content> contents = contentRepo.findByBlogPost(blogPost);
        List<ContentResponse> contentResponses = new ArrayList<>();
        for (Content content : contents) {
            ContentResponse contentResponse = ContentResponse.builder()
                    .header(content.getHeader())
                    .body(content.getBody())
                    .photo(content.getPhoto())
                    .build();
            contentResponses.add(contentResponse);
        }


        return BlogPostResponse.builder()
                .author(account.getEmail())
                .title(blogPost.getTitle())
                .banner(blogPost.getBanner())
                .topic(blogPost.getTopic())
                .createdAt(blogPost.getCreateAt())
                .contents(contentResponses)
                .build();
    }

    @Override
    public List<BlogPostDTO> getAllBlogPost() {
        return blogMapper.toBlogPostDTOs(blogPostRepo.findAll());
    }
}
