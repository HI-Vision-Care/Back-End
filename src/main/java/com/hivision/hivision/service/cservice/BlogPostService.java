package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.BlogPostDTO;
import com.hivision.hivision.enums.BlogStatus;
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
    public void updateBlogPost(BlogPostRequest request, List<ContentRequest> contentRequests, String accountID,int blogID) {
        Account account = accountRepo.findById(accountID)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        BlogPost blogPost = blogPostRepo.findById(blogID)
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));

        blogMapper.updateBlogPost(blogPost,request);
        blogPostRepo.save(blogPost);

        List<Content> existingContents = contentRepo.findByBlogPost(blogPost);


        if (existingContents.size() != contentRequests.size()) {
            // Xoá toàn bộ content cũ
            contentRepo.deleteAll(existingContents);
            // Tạo lại từ đầu
            for (ContentRequest contentRequest : contentRequests) {
                Content newContent = Content.builder()
                        .blogPost(blogPost)
                        .header(contentRequest.getHeader())
                        .body(contentRequest.getBody())
                        .photo(contentRequest.getPhoto())
                        .build();
                contentRepo.save(newContent);
            }
        } else {
            // Cập nhật từng content theo vị trí
            for (int i = 0; i < contentRequests.size(); i++) {
                Content existingContent = existingContents.get(i);
                ContentRequest contentRequest = contentRequests.get(i);
                blogMapper.updateContent(existingContent, contentRequest);
                contentRepo.save(existingContent);
            }
        }
    }

    @Override
    public void hideBlogPost(int blogID) {


        BlogPost blogPost = blogPostRepo.findById(blogID)
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));
        blogPost.setIsHide(true);
        blogPostRepo.save(blogPost);
    }

    @Override
    public void showBlogPost(int blogID) {
        BlogPost blogPost = blogPostRepo.findById(blogID)
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));

        blogPost.setIsHide(false);
        blogPostRepo.save(blogPost);
    }

    @Override
    public void approveBlogPost(int blogID,String accountID) {
        Account account = accountRepo.findById(accountID)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        BlogPost blogPost = blogPostRepo.findById(blogID)
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));
        blogPost.setStatus(BlogStatus.APPROVED);
        blogPostRepo.save(blogPost);
    }

    @Override
    public void rejectBlogPost(int blogID,String accountID) {
        Account account = accountRepo.findById(accountID)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        BlogPost blogPost = blogPostRepo.findById(blogID)
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));
        blogPost.setStatus(BlogStatus.REJECTED);
        blogPostRepo.save(blogPost);
    }

    @Override
    public void adjustBlogPost(int blogID,String accountID) {
        Account account = accountRepo.findById(accountID)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        BlogPost blogPost = blogPostRepo.findById(blogID)
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));
        blogPost.setStatus(BlogStatus.ADJUSTING);
        blogPostRepo.save(blogPost);
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

    @Override
    public List<BlogPostDTO> getBlogPostIsHide() {
        List<BlogPost> blogPosts = blogPostRepo.findBlogPostByIsHide(true);
        return blogMapper.toBlogPostDTOs(blogPosts);
    }

    @Override
    public List<BlogPostDTO> getBlogPostIsShow() {
        List<BlogPost> blogPosts = blogPostRepo.findBlogPostByIsHide(false);
        return blogMapper.toBlogPostDTOs(blogPosts);
    }
}
