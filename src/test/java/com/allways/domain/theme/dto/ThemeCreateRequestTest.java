package com.allways.domain.theme.dto;

import com.allways.common.factory.theme.ThemeCreateRequestFactory;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThemeCreateRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void themeCreateRequestValidation() {
        // Given
        ThemeCreateRequest createRequest = ThemeCreateRequestFactory
                .createThemeCreateRequest();

        // When
        Set<ConstraintViolation<ThemeCreateRequest>> violations =
                validator.validate(createRequest);

        // Then
        assertEquals(0, violations.size(),
                "위반 사항이 없습니다.");
    }

    @Test
    void themeCreateThemeNameValidation() {
        // Given
        ThemeCreateRequest createRequest = ThemeCreateRequestFactory
                .createThemeCreateRequest("", "imageUrl");

        // When
        Set<ConstraintViolation<ThemeCreateRequest>> violations = 
                validator.validate(createRequest);

        // Then
        assertEquals(1, violations.size(), 
                "테마 이름이 Blank 입니다.");
    }

    @Test
    void themeCreateImageUrlValidation() {
        // Given
        ThemeCreateRequest createRequest = ThemeCreateRequestFactory
                .createThemeCreateRequest("themeName", "");

        // When
        Set<ConstraintViolation<ThemeCreateRequest>> violations =
                validator.validate(createRequest);

        // Then
        assertEquals(1, violations.size(),
                "이미지 Url이 Blank 입니다.");
    }
}
