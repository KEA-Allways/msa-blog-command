package com.allways.domain.post.dto;

import com.allways.common.factory.post.PostUpdateRequestFactory;

import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class PostUpdateRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void PostCreateRequestValidateTest() {
        // Given
        PostUpdateRequest createRequest = PostUpdateRequestFactory.createPostUpdateRequest();

        // When
        Set<String> violations = validator.validate(createRequest).stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .collect(Collectors.toSet());

        // Then
        assertEquals(0, violations.size(), "이 내용은 validation 검사를 통과 해야 합니다.");
    }

    @Test
    void PostTitleBlankTest() {
        // Given
        PostUpdateRequest createRequest = PostUpdateRequestFactory.createPostUpdateRequest(
                "",
                "postContent",
                1L,
                "postImgUrl"
        );

        // When
        Set<String> violations = validator.validate(createRequest).stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .collect(Collectors.toSet());

        // Then
        assertFalse(violations.isEmpty(), "위반 사항이 존재합니다.");
        assertEquals(1, violations.size(), "PostTitle은 Blank가 아니어야합니다.");
    }

    @Test
    void PostContentBlankTest() {
        // Given
        PostUpdateRequest createRequest = PostUpdateRequestFactory.createPostUpdateRequest(
                "postTitle",
                "",
                1L,
                "postImgUrl"
        );

        // When
        Set<String> violations = validator.validate(createRequest).stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .collect(Collectors.toSet());

        // Then
        assertFalse(violations.isEmpty(), "위반 사항이 존재합니다.");
        assertEquals(1, violations.size(), "PostContent는 Blank가 아니어야합니다.");
    }

    @Test
    void NegativeCategorySeqTest() {
        // Given
        PostUpdateRequest createRequest = PostUpdateRequestFactory.createPostUpdateRequest(
                "postTitle",
                "postContent",
                -1L,
                "postImgUrl"
        );

        // When
        Set<String> violations = validator.validate(createRequest).stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .collect(Collectors.toSet());

        // Then
        assertFalse(violations.isEmpty(), "위반 사항이 존재합니다.");
        assertEquals(1, violations.size(), "CategorySeq는 음수가 아니어야합니다.");
    }

    @Test
    void PostImgUrlBlankTest() {
        // Given
        PostUpdateRequest createRequest = PostUpdateRequestFactory.createPostUpdateRequest(
                "postTitle",
                "postContent",
                1L,
                ""
        );

        // When
        Set<String> violations = validator.validate(createRequest).stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .collect(Collectors.toSet());

        // Then
        assertFalse(violations.isEmpty(), "위반 사항이 존재합니다.");
        assertEquals(1, violations.size(), "PostImgUrl은 Blank가 아니어야합니다.");
    }
}
