package com.allways.domain.theme.controller;

import com.allways.common.factory.theme.ThemeCreateRequestFactory;
import com.allways.common.factory.theme.ThemeUpdateRequestFactory;
import com.allways.domain.theme.dto.ThemeCreateRequest;
import com.allways.domain.theme.dto.ThemeUpdateRequest;
import com.allways.domain.theme.service.ThemeService;
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

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ThemeCommandControllerTest {
    private MockMvc mockMvc;
    @InjectMocks private ThemeCommandController themeCommandController;
    @Mock private ThemeService themeService;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(themeCommandController).build();
    }

    @Test
    void createThemeTest() throws Exception {
        // Given
        ThemeCreateRequest createRequest = ThemeCreateRequestFactory
                .createThemeCreateRequest();

        // When, Then
        MvcResult result = mockMvc.perform(post("/api/theme")
                        .header("userSeq", String.valueOf(1L))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        MockHttpServletRequest request = result.getRequest();
        String userSeq = request.getHeader("userSeq");

        verify(themeService).createTheme(createRequest, Long.parseLong(userSeq));
    }

    @Test
    void updateThemeTest() throws Exception {
        // Given
        Long themeSeq = 1L;
        ThemeUpdateRequest updateRequest = ThemeUpdateRequestFactory
                .createThemeUpdateRequest();

        // When, Then
        MvcResult result = mockMvc.perform(put("/api/theme/{themeSeq}", themeSeq)
                        .header("userSeq", String.valueOf(1L))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateRequest)))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletRequest request = result.getRequest();
        String userSeq = request.getHeader("userSeq");

        verify(themeService).updateTheme(updateRequest, Long.parseLong(userSeq), themeSeq);
    }

    @Test
    void deleteThemeTest() throws Exception {
        // Given
        Long themeSeq = 1L;

        // When, Then
        mockMvc.perform(delete("/api/theme/{themeSeq}", themeSeq))
                .andExpect(status().isOk());

        verify(themeService).deleteTheme(themeSeq);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
