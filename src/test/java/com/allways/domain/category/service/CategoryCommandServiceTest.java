package com.allways.domain.category.service;

import com.allways.common.factory.category.CategoryCreateRequestFactory;
import com.allways.common.feign.category.CategoryFeignService;
import com.allways.domain.category.dto.CategoryCreateRequest;
import com.allways.domain.category.entity.Category;
import com.allways.domain.category.repository.CategoryRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryCommandServiceTest {
    @Mock private CategoryRepository categoryRepository;
    @Mock private CategoryFeignService categoryFeignService;
    @InjectMocks private CategoryCommandService categoryCommandService;
    @Captor private ArgumentCaptor<Category> categoryArgumentCaptor;

    @Test
    void CreateCategoryTest() {
        // Given
        Long themeSeq = 1L;
        CategoryCreateRequest createRequest = CategoryCreateRequestFactory.createCategoryCreateRequest();
        Long nextOrder = 10L;

        when(categoryFeignService.readCategoryOrder(anyLong())).thenReturn(nextOrder);

        // When
        categoryCommandService.createCategory(themeSeq, createRequest);

        // Then
        verify(categoryFeignService, times(1)).readCategoryOrder(themeSeq);
        categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryRepository, times(1)).save(categoryArgumentCaptor.capture());

        Category savedCategory = categoryArgumentCaptor.getValue();

        assertEquals(createRequest.getCategoryName(), savedCategory.getCategoryName());
        assertEquals(nextOrder, savedCategory.getCategoryOrder());
        assertEquals(themeSeq, savedCategory.getThemeSeq());
    }

    @Test
    void testDeleteCategory() {
        // Given
        Long categorySeq = 1L;

        // When
        categoryCommandService.deleteCategory(categorySeq);

        // Then
        verify(categoryRepository, times(1)).deleteById(categorySeq);
    }
}
