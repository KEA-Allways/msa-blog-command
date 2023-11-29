package com.allways.domain.post.repository;

import com.allways.domain.post.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
	@Modifying
	@Query("UPDATE Post p SET p.postView = p.postView + 1L WHERE p.postSeq = :postSeq")
	void increasePostView(Long postSeq);

    @Modifying
    @Query("UPDATE Post p SET p.postTitle = :postTitle, p.postContent = :postContent, p.categorySeq = :categorySeq WHERE p.postSeq = :postSeq" )
    void updatePostByPostSeq(Long postSeq, Long categorySeq, String postTitle, String postContent);
}
