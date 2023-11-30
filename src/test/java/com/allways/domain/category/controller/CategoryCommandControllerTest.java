package com.allways.domain.category.controller;

import com.allways.common.factory.category.CategoryCreateRequestFactory;
import com.allways.common.factory.category.CategoryUpdateRequestFactory;
import com.allways.domain.category.dto.CategoryCreateRequest;
import com.allways.domain.category.dto.CategoryUpdateRequest;
import com.allways.domain.category.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryCommandControllerTest {
    @Mock private CategoryService categoryService;
    @InjectMocks private CategoryCommandController categoryCommandController;
    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        // MockMvc를 설정하여 컨트롤러를 테스트할 수 있는 환경을 구성합니다.
        mockMvc = MockMvcBuilders.standaloneSetup(categoryCommandController).build();
    }

    @Test
    void createCategoryTest() throws Exception {
        // Given
        Long themeSeq = 1L;
        CategoryCreateRequest createRequest = CategoryCreateRequestFactory
                .createCategoryCreateRequest();

        // When, Then
        mockMvc.perform(post("/api/theme/{themeSeq}/category", themeSeq)
                        .contentType("application/json")
                        .content(asJsonString(createRequest)))
                .andExpect(status().isCreated());

        verify(categoryService).createCategory(themeSeq, createRequest);
    }

    @Test
    void updateCategoryTest() throws Exception {
        // Given
        Long categorySeq = 1L;
        CategoryUpdateRequest updateRequest = CategoryUpdateRequestFactory
                .createCategoryUpdateRequest();

        // When, Then
        mockMvc.perform(put("/api/category/{categorySeq}", categorySeq)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateRequest)))
                .andExpect(status().isOk());

        verify(categoryService).updateCategory(updateRequest, categorySeq);
    }

    @Test
    void deleteCategoryTest() throws Exception {
        // Given
        Long categorySeq = 1L;

        // When, Then
        mockMvc.perform(delete("/api/category/{categorySeq}", categorySeq)
                        .contentType("application/json"))
                .andExpect(status().isOk());

        verify(categoryService).deleteCategory(categorySeq);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
