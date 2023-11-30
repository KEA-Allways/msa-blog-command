package com.allways.domain.theme.service;

import com.allways.common.factory.theme.ThemeCreateRequestFactory;
import com.allways.common.factory.theme.ThemeUpdateRequestFactory;
import com.allways.domain.theme.dto.ThemeCreateRequest;
import com.allways.domain.theme.dto.ThemeUpdateRequest;
import com.allways.domain.theme.entity.Theme;
import com.allways.domain.theme.repository.ThemeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

public class ThemeServiceTest {
    @Mock private ThemeRepository themeRepository;
    @InjectMocks private ThemeService themeService;
    @Captor private ArgumentCaptor<Theme> ThemeArgumentCaptor;

    // create와 update의 경우 feign이 들어가기에 수행X

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createThemeTest() {
        // Given
        Long userSeq = 1L;
        ThemeCreateRequest createRequest = ThemeCreateRequestFactory
                .createThemeCreateRequest();

        // When
        themeService.createThemeForTest(createRequest, userSeq);

        // Then
        verify(themeRepository).save(ThemeArgumentCaptor.capture());

        Theme savedTheme = ThemeArgumentCaptor.getValue();

        assertEquals(createRequest.getThemeName(), savedTheme.getThemeName());
    }

    @Test
    void updateThemeTest() {
        // Given
        Long themeSeq = 1L;
        Long themeOrder = 10L;
        ThemeUpdateRequest updateRequest = ThemeUpdateRequestFactory
                .createThemeUpdateRequest();

        // When
        themeService.updateThemeForTest(updateRequest, themeSeq);

        // Then
        verify(themeRepository).updateByThemeSeq(
                themeSeq,
                updateRequest.getThemeName(),
                themeOrder
        );
    }

    @Test
    void deleteThemeTest() {
        // Given
        Long themeSeq = 1L;

        // When
        themeService.deleteTheme(themeSeq);

        // Then
        verify(themeRepository).deleteById(themeSeq);
    }
}
