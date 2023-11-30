package com.allways.domain.reply.dto;

import com.allways.common.factory.reply.ReplyUpdateRequestFactory;

import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ReplyUpdateRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void ReplyUpdateRequestValidation() {
        // Given
        ReplyUpdateRequest updateRequest = ReplyUpdateRequestFactory
                .createReplyUpdateRequest();

        // When
        Set<ConstraintViolation<ReplyUpdateRequest>> violations =
                validator.validate(updateRequest);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    void ReplyContentBlankValidation() {
        // Given
        ReplyUpdateRequest updateRequest = ReplyUpdateRequestFactory
                .createReplyUpdateRequest("");

        // When
        Set<ConstraintViolation<ReplyUpdateRequest>> violations =
                validator.validate(updateRequest);

        // Then
        assertEquals(1, violations.size(), "새로운 댓글을 입력해주세요.");
    }
}
