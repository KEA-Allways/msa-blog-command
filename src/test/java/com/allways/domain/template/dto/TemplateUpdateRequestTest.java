package com.allways.domain.template.dto;

import com.allways.common.factory.template.TemplateUpdateRequestFactory;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateUpdateRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void templateUpdateRequestValidation() {
        // Given
        TemplateUpdateRequest updateRequest = TemplateUpdateRequestFactory
                .createTemplateUpdateRequest();

        // When
        Set<ConstraintViolation<TemplateUpdateRequest>> violations =
                validator.validate(updateRequest);

        // Then
        assertEquals(0, violations.size(), "위반 사항이 없습니다.");
    }

    @Test
    void templateUpdateTemplateTitleValidation() {
        // Given
        TemplateUpdateRequest updateRequest = TemplateUpdateRequestFactory
                .createTemplateUpdateRequest(
                        "",
                        "updateTemplateContent");

        // When
        Set<ConstraintViolation<TemplateUpdateRequest>> violations =
                validator.validate(updateRequest);

        // Then
        assertEquals(1, violations.size(), "templateTitle이 blank 입니다.");
    }

    @Test
    void templateUpdateTemplateContentValidation() {
        // Given
        TemplateUpdateRequest updateRequest = TemplateUpdateRequestFactory
                .createTemplateUpdateRequest(
                        "updateTemplateTitle",
                        "");

        // When
        Set<ConstraintViolation<TemplateUpdateRequest>> violations =
                validator.validate(updateRequest);

        // Then
        assertEquals(1, violations.size(), "templateContent가 blank 입니다.");
    }
}
