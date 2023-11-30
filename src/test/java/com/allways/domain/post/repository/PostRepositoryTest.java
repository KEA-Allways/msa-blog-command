package com.allways.domain.post.repository;

import com.allways.common.factory.post.PostFactory;
import com.allways.common.factory.post.PostUpdateRequestFactory;
import com.allways.domain.post.dto.PostUpdateRequest;
import com.allways.domain.post.entity.Post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 데이터베이스 사용
@SpringBootTest
@ActiveProfiles("test")
public class PostRepositoryTest {
    @Autowired private PostRepository postRepository;
    @Autowired private EntityManager entityManager;

    @Transactional
    @Test
    @Rollback(value = false)
    void increasePostViewTest() {
        // Given
        Post post = PostFactory.createPost(
                "postTitle",
                "postContent",
                1L,
                1L);

        Post makePost = postRepository.save(post);
        Long beforePostView = makePost.getPostView();

        // flush and clear the entity manager to synchronize with the database
        entityManager.flush();
        entityManager.clear();

        Long postSeq = makePost.getPostSeq();

        // When
        postRepository.increasePostView(postSeq);

        // Then
        assertEquals(beforePostView + 1, postRepository.getById(postSeq).getPostView());

        postRepository.deleteById(postSeq);
    }

    @Test
    @Transactional
    void updatePostByPostSeqTest() {
        // Given
        Long postSeq = 1L;
        PostUpdateRequest updateRequest = PostUpdateRequestFactory.createPostUpdateRequest();

        // When
        postRepository.updatePostByPostSeq(
                postSeq,
                updateRequest.getCategorySeq(),
                updateRequest.getPostTitle(),
                updateRequest.getPostContent()
        );

        Post updatedPost = postRepository.getById(postSeq);

        // Then
        assertEquals(updateRequest.getCategorySeq(), updatedPost.getCategorySeq());
        assertEquals(updateRequest.getPostTitle(), updatedPost.getPostTitle());
        assertEquals(updateRequest.getPostContent(), updatedPost.getPostContent());
    }
}
