package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.pojo.BlogPost;
import com.hivision.hivision.pojo.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReactionRepo extends JpaRepository<Reaction, Integer> {
    Reaction findReactionByAccountAndBlog(Account account, BlogPost blog);
}