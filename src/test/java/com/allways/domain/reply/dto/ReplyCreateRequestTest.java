package com.allways.domain.reply.dto;

import com.allways.common.factory.reply.ReplyCreateRequestFactory;

import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ReplyCreateRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void ReplyCreateRequestValidation() {
        // Given
        ReplyCreateRequest createRequest = ReplyCreateRequestFactory.createReplyCreateRequest();

        // When
        Set<ConstraintViolation<ReplyCreateRequest>> violations = validator.validate(createRequest);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    void ReplyContentBlankValidation() {
        // Given
        ReplyCreateRequest createRequest = ReplyCreateRequestFactory.createReplyCreateRequest("");

        // When
        Set<ConstraintViolation<ReplyCreateRequest>> violations = validator.validate(createRequest);

        // Then
        assertEquals(1, violations.size());
        ConstraintViolation<ReplyCreateRequest> violation = violations.iterator().next();
        assertEquals("댓글 내용을 작성해주세요.", violation.getMessage());
    }
}
