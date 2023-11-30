package com.allways.domain.post.service;

import com.allways.common.factory.post.PostCreateRequestFactory;
import com.allways.common.factory.post.PostUpdateRequestFactory;
import com.allways.domain.post.dto.PostCreateRequest;
import com.allways.domain.post.dto.PostUpdateRequest;
import com.allways.domain.post.entity.Post;
import com.allways.domain.post.repository.PostRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @Mock private PostRepository postRepository;
    @InjectMocks private PostService postService;
    @Captor private ArgumentCaptor<Post> postArgumentCaptor;

    @Test
    void createPostTest() {
        // Given
        Long userSeq = 1L;
        PostCreateRequest createRequest = PostCreateRequestFactory
                .createPostCreateRequest();

        // When
        // 원래 쓰던 createPost는 feign을 쓰는 부분이 있어서 따로 테스트 용 코드 만들어줬음
        postService.createPostForTest(createRequest, userSeq);

        // Then
        verify(postRepository).save(postArgumentCaptor.capture());

        Post savedPost = postArgumentCaptor.getValue();

        assertEquals(createRequest.getPostTitle(), savedPost.getPostTitle());
        assertEquals(createRequest.getPostContent(), savedPost.getPostContent());
        assertEquals(createRequest.getCategorySeq(), savedPost.getCategorySeq());
    }

    @Test
    void updatePostTest() {
        // Given
        Long postSeq = 1L;
        PostUpdateRequest updateRequest = PostUpdateRequestFactory
                .createPostUpdateRequest();

        // When
        postService.updatePost(updateRequest, postSeq);

        // Then
        // postRepository의 updatePostByPostSeq 메서드가 호출되었는지 확인
        verify(postRepository).updatePostByPostSeq(
                postSeq,
                updateRequest.getCategorySeq(),
                updateRequest.getPostTitle(),
                updateRequest.getPostContent()
        );
    }

    @Test
    void deletePostTest() {
        // Given
        Long postSeq = 1L;

        // When
        postService.deletePost(postSeq);

        // Then
        // postRepository의 deleteById 메서드가 호출되었는지 확인
        verify(postRepository).deleteById(postSeq);
    }
}
