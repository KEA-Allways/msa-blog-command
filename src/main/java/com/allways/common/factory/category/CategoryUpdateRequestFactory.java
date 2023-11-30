package com.allways.common.factory.category;

import com.allways.domain.category.dto.CategoryUpdateRequest;

public class CategoryUpdateRequestFactory {
    public static CategoryUpdateRequest createCategoryUpdateRequest() {
        return new CategoryUpdateRequest(
                "newCategoryName",
                1L,
                1L);
    }

    public static CategoryUpdateRequest createCategoryUpdateRequest(
            String newCategoryName,
            Long newCategoryOrder,
            Long newThemeSeq) {
        return new CategoryUpdateRequest(
                newCategoryName,
                newCategoryOrder,
                newThemeSeq);
    }
}
