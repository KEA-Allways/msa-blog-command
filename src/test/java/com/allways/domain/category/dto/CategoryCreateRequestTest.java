package com.allways.domain.category.dto;

import com.allways.common.factory.category.CategoryCreateRequestFactory;

import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryCreateRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void CategoryCreateRequestValidateTest() {
        // Given
        CategoryCreateRequest categoryCreateRequest = CategoryCreateRequestFactory.createCategoryCreateRequest();

        // When
        Set<String> violations = validator.validate(categoryCreateRequest).stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .collect(Collectors.toSet());

        // Then
        assertEquals(0, violations.size(),
                "이 내용은 validation 검사를 통과 해야 합니다.");
    }

    @Test
    void CategoryNameBlankTest() {
        // Given
        CategoryCreateRequest categoryCreateRequest = CategoryCreateRequestFactory.createCategoryCreateRequest("");

        // When
        Set<String> violations = validator.validate(categoryCreateRequest).stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .collect(Collectors.toSet());

        // Then
        assertEquals(1, violations.size(),
                "이 내용은 validation 검사를 통과 하지 못 합니다.");
    }
}
