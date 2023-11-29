package com.allways.domain.template.controller;

import com.allways.common.factory.template.TemplateCreateRequestFactory;
import com.allways.common.factory.template.TemplateUpdateRequestFactory;
import com.allways.domain.template.dto.TemplateCreateRequest;
import com.allways.domain.template.dto.TemplateUpdateRequest;
import com.allways.domain.template.service.TemplateCommandService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class TemplateCommandControllerTest {
    private MockMvc mockMvc;
    @Mock private TemplateCommandService templateCommandService;
    @InjectMocks private TemplateCommandController templateCommandController;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(templateCommandController).build();
    }

    @Test
    void createTemplateTest() throws Exception {
        // Given
        TemplateCreateRequest createRequest = TemplateCreateRequestFactory.createTemplateCreateRequest();

        // When, Then
        MvcResult result = mockMvc.perform(post("/api/template")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("userSeq", String.valueOf(1L)) // userSeq 헤더 추가
                        .content(asJsonString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        MockHttpServletRequest request = result.getRequest();
        String userSeq = request.getHeader("userSeq");

        verify(templateCommandService).createTemplate(createRequest, Long.parseLong(userSeq));
    }

    @Test
    void updateTemplateTest() throws Exception {
        // Given
        Long templateSeq = 1L;
        TemplateUpdateRequest updateRequest = TemplateUpdateRequestFactory.createTemplateUpdateRequest();

        // When, Then
        mockMvc.perform(put("/api/template/{templateSeq}", templateSeq)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateRequest)))
                .andExpect(status().isOk());

        verify(templateCommandService).updateTemplate(updateRequest, templateSeq);
    }

    @Test
    void deleteTemplateTest() throws Exception {
        // Given
        Long templateSeq = 1L;

        // When, Then
        mockMvc.perform(delete("/api/template/{templateSeq}", templateSeq))
                .andExpect(status().isOk());

        verify(templateCommandService).deleteTemplate(templateSeq);
    }

    // 객체를 JSON 문자열로 변환하는 유틸리티 메서드
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
