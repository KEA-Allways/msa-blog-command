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

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock private CategoryRepository categoryRepository;
    @Mock private CategoryFeignService categoryFeignService;
    @InjectMocks private CategoryService categoryService;
    @Captor private ArgumentCaptor<Category> categoryArgumentCaptor;

    @Test
    void createCategoryTest() {
        // Given
        Long themeSeq = 1L;
        Long nextOrder = 10L;
        CategoryCreateRequest createRequest = CategoryCreateRequestFactory
                .createCategoryCreateRequest();

        // When
        when(categoryFeignService.readCategoryOrder(themeSeq)).thenReturn(nextOrder);

        categoryService.createCategory(themeSeq, createRequest);

        // Then
        verify(categoryFeignService).readCategoryOrder(themeSeq);

        categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryRepository).save(categoryArgumentCaptor.capture());
        Category savedCategory = categoryArgumentCaptor.getValue();

        assertEquals(createRequest.getCategoryName(), savedCategory.getCategoryName());
        assertEquals(nextOrder, savedCategory.getCategoryOrder());
        assertEquals(themeSeq, savedCategory.getThemeSeq());
    }

    @Test
    void deleteCategoryTest() {
        // Given
        Long categorySeq = 1L;

        // When
        categoryService.deleteCategory(categorySeq);

        // Then
        verify(categoryRepository).deleteById(categorySeq);
    }
}
