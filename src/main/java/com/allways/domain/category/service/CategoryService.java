package com.allways.domain.category.service;


import com.allways.common.feign.category.CategoryFeignService;
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
    public void createCategory(CategoryCreateRequest req, Long themeSeq){

        Long nextOrder = categoryFeignService.readCategoryOrder(themeSeq);

        categoryRepository.save(new Category(req.getCategoryName(), nextOrder, themeSeq));
    }

    // delete는 repository에서 가져와서 삭제가 아니라 그냥 deleteById로 삭제가 가능함
    @Transactional
    public void deleteCategory(Long categorySeq){
        categoryRepository.deleteById(categorySeq);
    }
}
