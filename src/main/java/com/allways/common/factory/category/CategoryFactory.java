package com.allways.common.factory.category;

import com.allways.domain.category.entity.Category;

public class CategoryFactory {
    public static Category createCategory() {
        return new Category(
                "categoryName",
                1L,
                1L
        );
    }

    public static Category createCategory(
            String categoryName,
            Long categoryOrder,
            Long themeSeq) {
        return new Category(
                categoryName,
                categoryOrder,
                themeSeq
        );
    }
}
