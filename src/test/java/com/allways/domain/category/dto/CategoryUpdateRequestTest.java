package com.allways.domain.category.dto;

import com.allways.common.factory.category.CategoryUpdateRequestFactory;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryUpdateRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void categoryUpdateRequestValidation() {
        // Given
        CategoryUpdateRequest updateRequest = CategoryUpdateRequestFactory
                .createCategoryUpdateRequest();

        // When
        Set<ConstraintViolation<CategoryUpdateRequest>> violations =
                validator.validate(updateRequest);

        // Then
        assertEquals(0, violations.size(),
                "위반 사항이 없습니다.");
    }

    @Test
    void categoryCreateCategoryNameValidation() {
        // Given
        CategoryUpdateRequest updateRequest = CategoryUpdateRequestFactory
                .createCategoryUpdateRequest(
                        "",
                        1L,
                        1L);

        // When
        Set<ConstraintViolation<CategoryUpdateRequest>> violations =
                validator.validate(updateRequest);

        // Then
        assertEquals(1, violations.size(),
                "카테고리 이름을 입력해주세요.");
    }

    @Test
    void categoryCreateCategoryOrderValidation() {
        // Given
        CategoryUpdateRequest updateRequest = CategoryUpdateRequestFactory
                .createCategoryUpdateRequest(
                        "newCategoryName",
                        null,
                        1L);

        // When
        Set<ConstraintViolation<CategoryUpdateRequest>> violations =
                validator.validate(updateRequest);

        // Then
        assertEquals(1, violations.size(),
                "카테고리 Order를 입력해주세요.");
    }

    @Test
    void categoryCreateThemeSeqValidation() {
        // Given
        CategoryUpdateRequest updateRequest = CategoryUpdateRequestFactory
                .createCategoryUpdateRequest(
                        "newCategoryName",
                        1L,
                        null);

        // When
        Set<ConstraintViolation<CategoryUpdateRequest>> violations =
                validator.validate(updateRequest);

        // Then
        assertEquals(1, violations.size(),
                "themeSeq를 입력해주세요.");
    }
}
