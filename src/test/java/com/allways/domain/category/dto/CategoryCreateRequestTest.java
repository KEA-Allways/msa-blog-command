package com.allways.domain.category.dto;

import com.allways.common.factory.category.CategoryCreateRequestFactory;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryCreateRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void categoryCreateRequestValidation() {
        // Given
        CategoryCreateRequest createRequest = CategoryCreateRequestFactory
                .createCategoryCreateRequest();

        // When
        Set<ConstraintViolation<CategoryCreateRequest>> violations =
                validator.validate(createRequest);

        // Then
        assertEquals(0, violations.size(),
                "위반 사항이 없습니다.");
    }

    @Test
    void categoryCreateCategoryNameValidation() {
        // Given
        CategoryCreateRequest createRequest = CategoryCreateRequestFactory
                .createCategoryCreateRequest("");

        // When
        Set<ConstraintViolation<CategoryCreateRequest>> violations =
                validator.validate(createRequest);

        // Then
        assertEquals(1, violations.size(),
                "카테고리 이름을 입력해주세요.");
    }
}
