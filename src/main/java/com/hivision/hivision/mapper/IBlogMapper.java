package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.BlogPostDTO;
import com.hivision.hivision.pojo.BlogPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface IBlogMapper {

    @Mapping(source = "account.email", target = "author")
    BlogPostDTO toBlogPostDTO(BlogPost blogPost);
    List<BlogPostDTO> toBlogPostDTOs(List<BlogPost> blogPosts);
}
