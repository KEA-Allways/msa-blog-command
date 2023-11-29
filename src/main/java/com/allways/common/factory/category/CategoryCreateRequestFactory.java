package com.allways.common.factory.category;

import com.allways.domain.category.dto.CategoryCreateRequest;

public class CategoryCreateRequestFactory {
    public static CategoryCreateRequest createCategoryCreateRequest() {
        return new CategoryCreateRequest("categoryName");
    }

    public static CategoryCreateRequest createCategoryCreateRequest(String categoryName) {
        return new CategoryCreateRequest(categoryName);
    }
}
