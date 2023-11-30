package com.allways.common.factory.category;

import com.allways.domain.category.dto.CategoryCreateRequest;

public class CategoryCreateRequestFactory {
    public static CategoryCreateRequest createCategoryCreateRequest() {
        return new CategoryCreateRequest(
                "categoryName",
                1L,
                1L);
    }

    public static CategoryCreateRequest createCategoryCreateRequest(
            String categoryName,
            Long categoryOrder,
            Long themeSeq) {
        return new CategoryCreateRequest(
                categoryName,
                categoryOrder,
                themeSeq);
    }
}
