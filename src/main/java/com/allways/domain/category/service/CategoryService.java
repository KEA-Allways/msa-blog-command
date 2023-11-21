package com.allways.domain.category.service;


import com.allways.domain.category.entity.Category;
import com.allways.domain.category.dto.CategoryCreateRequest;
import com.allways.domain.category.repository.CategoryRepository;
import com.allways.domain.theme.exception.ThemeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

//    @Autowired
//    public CategoryService(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }

    @Transactional
    public void createCategory(CategoryCreateRequest req, Long themeSeq){

        // nextOrder 가져오는거 feignClient로 요청해서 값 가져오거나 프론트 단에서 어떻게든 넘기는 방벙으로 수정하기
        Long nextOrder = categoryRepository.findLastCategoryOrderByThemeSeq(themeSeq);
        nextOrder += 1;

        categoryRepository.save(new Category(req.getCategoryName(), nextOrder, themeSeq));
    }

    // delete는 repository에서 가져와서 삭제가 아니라 그냥 deleteById로 삭제가 가능함
    @Transactional
    public void deleteCategory(Long categorySeq){
        categoryRepository.deleteById(categorySeq);
    }
}
