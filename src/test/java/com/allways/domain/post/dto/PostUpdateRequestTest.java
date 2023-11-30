package com.allways.domain.post.dto;

import com.allways.common.factory.post.PostUpdateRequestFactory;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PostUpdateRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void postUpdateRequestValidation() {
        // Given
        PostUpdateRequest updateRequest = PostUpdateRequestFactory
                .createPostUpdateRequest();

        // When
        Set<ConstraintViolation<PostUpdateRequest>> violations =
                validator.validate(updateRequest);

        // Then
        assertEquals(0, violations.size(), 
                "위반 사항이 없습니다.");
    }

    @Test
    void postUpdatePostTitleValidation() {
        // Given
        PostUpdateRequest updateRequest = PostUpdateRequestFactory
                .createPostUpdateRequest(
                        "",
                        "postContent",
                        1L,
                        "postImgUrl");

        // When
        Set<ConstraintViolation<PostUpdateRequest>> violations =
                validator.validate(updateRequest);

        // Then
        assertEquals(1, violations.size(),
                "PostTitle이 Blank 입니다.");
    }

    @Test
    void postUpdatePostContentValidation() {
        // Given
        PostUpdateRequest updateRequest = PostUpdateRequestFactory
                .createPostUpdateRequest(
                        "postTitle",
                        "",
                        1L,
                        "postImgUrl");

        // When
        Set<ConstraintViolation<PostUpdateRequest>> violations =
                validator.validate(updateRequest);

        // Then
        assertEquals(1, violations.size(),
                "PostContent가 Blank 입니다.");
    }

    @Test
    void postUpdateCategorySeqValidation() {
        // Given
        PostUpdateRequest updateRequest = PostUpdateRequestFactory
                .createPostUpdateRequest(
                        "postTitle",
                        "postContent",
                        -1L,
                        "postImgUrl");

        // When
        Set<ConstraintViolation<PostUpdateRequest>> violations =
                validator.validate(updateRequest);

        // Then
        assertEquals(1, violations.size(),
                "CategorySeq가 음수가 입니다.");
    }

    @Test
    void postUpdatePostImgUrlValidation() {
        // Given
        PostUpdateRequest updateRequest = PostUpdateRequestFactory
                .createPostUpdateRequest(
                        "postTitle",
                        "postContent",
                        1L,
                        "");

        // When
        Set<ConstraintViolation<PostUpdateRequest>> violations =
                validator.validate(updateRequest);

        // Then
        assertEquals(1, violations.size(),
                "PostImgUrl이 Blank 입니다.");
    }
}
