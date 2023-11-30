package com.allways.domain.post.dto;

import com.allways.common.factory.post.PostCreateRequestFactory;
import com.allways.domain.post.entity.Post;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PostCreateRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void postCreateRequestValidation() {
        // Given
        PostCreateRequest createRequest = PostCreateRequestFactory
                .createPostCreateRequest();

        // When
        Set<ConstraintViolation<PostCreateRequest>> violations =
                validator.validate(createRequest);

        // Then
        assertEquals(0, violations.size(),
                "위반 사항이 없습니다.");
    }

    @Test
    void postCreatePostTitleValidation() {
        // Given
        PostCreateRequest createRequest = PostCreateRequestFactory
                .createPostCreateRequest(
                        "",
                        "postContent",
                        1L,
                        "postImgUrl");

        // When
        Set<ConstraintViolation<PostCreateRequest>> violations =
                validator.validate(createRequest);

        // Then
        assertEquals(1, violations.size(),
                "PostTitle이 Blank 입니다.");
    }

    @Test
    void postCreatePostContentValidation() {
        // Given
        PostCreateRequest createRequest = PostCreateRequestFactory
                .createPostCreateRequest(
                        "postTitle",
                        "",
                        1L,
                        "postImgUrl");

        // When
        Set<ConstraintViolation<PostCreateRequest>> violations =
                validator.validate(createRequest);

        // Then
        assertEquals(1, violations.size(),
                "PostContent가 Blank 입니다.");
    }

    @Test
    void postCreateCategorySeqValidation() {
        // Given
        PostCreateRequest createRequest = PostCreateRequestFactory
                .createPostCreateRequest(
                        "postTitle",
                        "postContent",
                        -1L,
                        "postImgUrl");

        // When
        Set<ConstraintViolation<PostCreateRequest>> violations =
                validator.validate(createRequest);

        // Then
        assertEquals(1, violations.size(),
                "CategorySeq가 음수가 입니다.");
    }

    @Test
    void postCreatePostImgUrlValidation() {
        // Given
        PostCreateRequest createRequest = PostCreateRequestFactory.createPostCreateRequest(
                "postTitle",
                "postContent",
                1L,
                "");

        // When
        Set<ConstraintViolation<PostCreateRequest>> violations =
                validator.validate(createRequest);

        // Then
        assertEquals(1, violations.size(),
                "PostImgUrl이 Blank 입니다.");
    }

    @Test
    void toEntityTest() {
        // Given
        PostCreateRequest request = PostCreateRequestFactory.createPostCreateRequest();

        // When
        Long userSeq = 123L;
        Post post = PostCreateRequest.toEntity(request, userSeq);

        // Then
        assertNotNull(post);
        assertEquals(userSeq, post.getUserSeq());
        assertEquals(request.getPostTitle(), post.getPostTitle());
        assertEquals(request.getPostContent(), post.getPostContent());
        assertEquals(request.getCategorySeq(), post.getCategorySeq());
    }
}
