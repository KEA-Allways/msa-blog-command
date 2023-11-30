package com.allways.domain.category.repository;

import com.allways.common.factory.category.CategoryFactory;
import com.allways.domain.category.entity.Category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 데이터베이스 사용
@SpringBootTest
@ActiveProfiles("test")
class CategoryRepositoryTest {
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private EntityManager entityManager;

    @Test
    @Transactional
    void updateByCategorySeqTest() {
        // Given
        String newCategoryName = "updateCategoryName";
        Long newCategoryOrder = 2L;
        Long newThemeSeq = 2L;

        Category category = CategoryFactory.createCategory();
        entityManager.persist(category);

        // When
        categoryRepository.updateByCategorySeq(
                category.getCategorySeq(),
                newCategoryName,
                newCategoryOrder,
                newThemeSeq);
        entityManager.flush();
        entityManager.clear();
        Category updatedCategory = categoryRepository.findById(category.getCategorySeq()).orElse(null);

        // Then
        assertThat(updatedCategory).isNotNull();
        assertThat(updatedCategory.getCategoryName()).isEqualTo(newCategoryName);
        assertThat(updatedCategory.getCategoryOrder()).isEqualTo(newCategoryOrder);
        assertThat(updatedCategory.getThemeSeq()).isEqualTo(newThemeSeq);

        // 입력된 데이터 삭제
        categoryRepository.deleteById(updatedCategory.getCategorySeq());
    }
}
