package com.allways.domain.category.service;

import com.allways.common.feign.category.CategoryFeignService;
import com.allways.domain.category.dto.CategoryUpdateRequest;
import com.allways.domain.category.entity.Category;
import com.allways.domain.category.dto.CategoryCreateRequest;
import com.allways.domain.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryFeignService categoryFeignService;

    @Transactional
    public void createCategory(Long themeSeq, CategoryCreateRequest req){
        Long nextOrder = categoryFeignService.readCategoryOrder(themeSeq);
        categoryRepository.save(new Category(req.getCategoryName(), nextOrder, themeSeq));
    }

    @Transactional
    public void updateCategory(CategoryUpdateRequest req, Long categorySeq){
        categoryRepository.updateByCategorySeq(
                categorySeq,
                req.getCategoryName(),
                req.getCategoryOrder(),
                req.getThemeSeq()
        );
    }

    @Transactional
    public void deleteCategory(Long categorySeq){
        categoryRepository.deleteById(categorySeq);
    }
}
