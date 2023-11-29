package com.allways.domain.category.controller;

import com.allways.common.factory.category.CategoryCreateRequestFactory;
import com.allways.domain.category.dto.CategoryCreateRequest;
import com.allways.domain.category.service.CategoryCommandService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryCommandControllerTest {

    @Mock
    private CategoryCommandService categoryCommandService;

    @InjectMocks
    private CategoryCommandController categoryCommandController;

    private MockMvc mockMvc;

    @Test
    void createCategoryTest() throws Exception {
        // Given
        Long themeSeq = 1L;
        CategoryCreateRequest createRequest = CategoryCreateRequestFactory.createCategoryCreateRequest();
        mockMvc = MockMvcBuilders.standaloneSetup(categoryCommandController).build();

        // When - Then
        mockMvc.perform(post("/api/theme/{themeSeq}/category", themeSeq)
                        .contentType("application/json")
                        .content(asJsonString(createRequest)))
                .andExpect(status().isCreated());

        verify(categoryCommandService, times(1)).createCategory(themeSeq, createRequest);
    }

    @Test
    void deleteCategoryTest() throws Exception {
        // Given
        Long categorySeq = 1L;
        mockMvc = MockMvcBuilders.standaloneSetup(categoryCommandController).build();

        // When - Then
        mockMvc.perform(delete("/api/category/{categorySeq}", categorySeq)
                        .contentType("application/json"))
                .andExpect(status().isOk());

        verify(categoryCommandService, times(1)).deleteCategory(categorySeq);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
