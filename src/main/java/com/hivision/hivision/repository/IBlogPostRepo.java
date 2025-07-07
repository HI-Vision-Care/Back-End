package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlogPostRepo extends JpaRepository<BlogPost, Integer> {

}