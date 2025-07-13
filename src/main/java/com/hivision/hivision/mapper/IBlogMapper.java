package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.BlogPostDTO;
import com.hivision.hivision.payload.request.BlogPostRequest;
import com.hivision.hivision.payload.request.ContentRequest;
import com.hivision.hivision.pojo.BlogPost;
import com.hivision.hivision.pojo.Content;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface IBlogMapper {

    @Mapping(source = "account.email", target = "author")
    BlogPostDTO toBlogPostDTO(BlogPost blogPost);
    List<BlogPostDTO> toBlogPostDTOs(List<BlogPost> blogPosts);

    void updateBlogPost(@MappingTarget BlogPost blogPost, BlogPostRequest blogPostRequest);
    void updateContent(@MappingTarget Content content, ContentRequest contentRequest);
}
