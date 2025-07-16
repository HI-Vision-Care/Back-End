package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.BlogPost;
import com.hivision.hivision.pojo.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContentRepo extends JpaRepository<Content, Integer> {
        List<Content> findByBlogPost(BlogPost blogPost);
        void deleteByBlogPost(BlogPost blogPost);
}