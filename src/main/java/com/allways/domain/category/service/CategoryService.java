package com.allways.domain.category.service;


import com.allways.domain.category.entity.Category;
import com.allways.domain.category.dto.CategoryCreateRequest;
import com.allways.domain.category.repository.CategoryRepository;
import com.allways.domain.theme.exception.ThemeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void createCategory(CategoryCreateRequest req, Long themeSeq){

        Long nextOrder = categoryRepository.findLastCategoryOrderByThemeSeq(themeSeq);
        nextOrder += 1;

        Category category = categoryRepository.save(new Category(req.getCategoryName(), nextOrder, themeSeq));
    }

    @Transactional
    public void deleteCategory(Long categorySeq){
        Category category = categoryRepository.findById(categorySeq).orElseThrow(ThemeNotFoundException::new);
        categoryRepository.delete(category);
    }
}
