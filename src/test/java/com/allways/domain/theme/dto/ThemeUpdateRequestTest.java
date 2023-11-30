package com.allways.domain.theme.dto;

import com.allways.common.factory.theme.ThemeUpdateRequestFactory;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThemeUpdateRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void themeUpdateRequestValidation() {
        // Given
        ThemeUpdateRequest updateRequest = ThemeUpdateRequestFactory
                .createThemeUpdateRequest();

        // When
        Set<ConstraintViolation<ThemeUpdateRequest>> violations =
                validator.validate(updateRequest);

        // Then
        assertEquals(0, violations.size(),
                "위반 사항이 없습니다.");
    }

    @Test
    void themeUpdateThemeNameValidation() {
        // Given
        ThemeUpdateRequest updateRequest = ThemeUpdateRequestFactory
                .createThemeUpdateRequest(
                        "",
                        "updateImageUrl");

        // When
        Set<ConstraintViolation<ThemeUpdateRequest>> violations =
                validator.validate(updateRequest);

        // Then
        assertEquals(1, violations.size(),
                "테마 이름이 Blank 입니다.");
    }

    @Test
    void themeUpdateImageUrlValidation() {
        // Given
        ThemeUpdateRequest updateRequest = ThemeUpdateRequestFactory
                .createThemeUpdateRequest(
                        "themeName",
                        "");

        // When
        Set<ConstraintViolation<ThemeUpdateRequest>> violations =
                validator.validate(updateRequest);

        // Then
        assertEquals(1, violations.size(),
                "이미지 Url이 Blank 입니다.");
    }
}
