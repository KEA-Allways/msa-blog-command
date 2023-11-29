package com.allways.domain.template.service;

import com.allways.common.factory.template.TemplateCreateRequestFactory;
import com.allways.common.factory.template.TemplateUpdateRequestFactory;
import com.allways.domain.template.dto.TemplateCreateRequest;
import com.allways.domain.template.dto.TemplateUpdateRequest;
import com.allways.domain.template.entity.Template;
import com.allways.domain.template.repository.TemplateRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TemplateCommandServiceTest {
    @Mock private TemplateRepository templateRepository;
    @InjectMocks private TemplateCommandService templateCommandService;
    @Captor private ArgumentCaptor<Template> TemplateArgumentCaptor;

    @Test
    void createTemplateTest() {
        // Given
        TemplateCreateRequest createRequest = TemplateCreateRequestFactory.createTemplateCreateRequest();
        Long userSeq = 1L;

        // When
        templateCommandService.createTemplate(createRequest, userSeq);

        // Then
        verify(templateRepository).save(TemplateArgumentCaptor.capture());

        Template savedTemplate = TemplateArgumentCaptor.getValue();

        assertEquals(createRequest.getTemplateTitle(), savedTemplate.getTemplateTitle());
        assertEquals(createRequest.getTemplateContent(), savedTemplate.getTemplateContent());
    }

    @Test
    void updateTemplateTest() {
        // Given
        Long templateSeq = 1L;
        TemplateUpdateRequest updateRequest = TemplateUpdateRequestFactory.createTemplateUpdateRequest();

        // When
        templateCommandService.updateTemplate(updateRequest, templateSeq);

        // Then
        verify(templateRepository).updateBySeq(
                templateSeq,
                updateRequest.getTemplateContent(),
                updateRequest.getTemplateTitle()
        );
    }

    @Test
    void deleteTemplateTest() {
        // Given
        Long templateSeq = 1L;

        // When
        templateCommandService.deleteTemplate(templateSeq);

        // Then
        verify(templateRepository).deleteById(templateSeq);
    }
}
