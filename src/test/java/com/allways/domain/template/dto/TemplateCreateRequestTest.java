package com.allways.domain.template.dto;

import com.allways.common.factory.template.TemplateCreateRequestFactory;
import com.allways.domain.template.entity.Template;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateCreateRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void templateCreateRequestValidation() {
        // Given
        TemplateCreateRequest createRequest = TemplateCreateRequestFactory
                .createTemplateCreateRequest();

        // When
        Set<ConstraintViolation<TemplateCreateRequest>> violations =
                validator.validate(createRequest);

        // Then
        assertEquals(0, violations.size(), "위반사항이 없습니다.");
    }

    @Test
    void templateCreateTemplateTitleValidation() {
        // Given
        TemplateCreateRequest createRequest = TemplateCreateRequestFactory
                .createTemplateCreateRequest(
                "", "templateContent");

        // When
        Set<ConstraintViolation<TemplateCreateRequest>> violations =
                validator.validate(createRequest);

        // Then
        // templateTitle은 NotBlank를 위반한다.
        assertEquals(1, violations.size(),
                "template 제목을 입력해주세요.");
    }

    @Test
    void templateCreateTemplateContentValidation() {
        // Given
        TemplateCreateRequest createRequest = TemplateCreateRequestFactory
                .createTemplateCreateRequest(
                "templateTitle", "");

        // When
        Set<ConstraintViolation<TemplateCreateRequest>> violations =
                validator.validate(createRequest);

        // Then
        // templateContent는 NotBlank를 위반한다.
        assertEquals(1, violations.size(),
                "template 내용을 입력해주세요.");
    }

    @Test
    void toEntityTest() {
        // Given
        TemplateCreateRequest request = TemplateCreateRequestFactory
                .createTemplateCreateRequest(
                "템플릿 제목",
                "템플릿 내용");
        Long userSeq = 1L;

        // When
        Template template = request.toEntity(request, userSeq);

        // Then
        assertEquals("템플릿 제목", template.getTemplateTitle());
        assertEquals("템플릿 내용", template.getTemplateContent());
        assertEquals(userSeq, template.getUserSeq());
    }
}
