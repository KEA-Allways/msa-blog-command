package com.allways.domain.category.repository;

import com.allways.common.factory.category.CategoryFactory;
import com.allways.domain.category.entity.Category;
import com.allways.domain.category.repository.CategoryRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void updateByCategorySeqTest() {
        // Given
        String newCategoryName = "updateCategoryName";
        Long newCategoryOrder = 2L;
        Long newThemeSeq = 2L;

        Category category = CategoryFactory.createCategory(
                "originalCategoryName",
                12345L,
                12345L);
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
