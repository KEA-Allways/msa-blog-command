package com.allways.domain.post.repository;

import com.allways.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {

    @Modifying
    @Query("update Post p set p.postTitle = :postTitle, p.postContent = :postContent, p.categorySeq = :categorySeq where p.postSeq = :postSeq" )
    void updatePostByPostSeq(Long postSeq, Long categorySeq, String postTitle, String postContent);
}
