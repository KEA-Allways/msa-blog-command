package com.allways.domain.category.repository;

import com.allways.domain.category.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Modifying
    @Query("UPDATE Category c SET c.categoryName = :categoryName, c.categoryOrder  = :categoryOrder, c.themeSeq = :themeSeq WHERE c.categorySeq = :categorySeq")
    void updateByCategorySeq(Long categorySeq,
                             String categoryName,
                             Long categoryOrder,
                             Long themeSeq);
}
