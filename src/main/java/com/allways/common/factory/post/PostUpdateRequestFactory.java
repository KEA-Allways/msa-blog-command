package com.allways.common.factory.post;

import com.allways.domain.post.dto.PostUpdateRequest;

public class PostUpdateRequestFactory {
    public static PostUpdateRequest createPostUpdateRequest() {
        return new PostUpdateRequest(
                "newPostTitle",
                "newPostContent",
                1L);
    }

    public static PostUpdateRequest createPostUpdateRequest(
            String newPostTitle,
            String newPostContent,
            Long newCategorySeq) {
        return new PostUpdateRequest(
                newPostTitle, newPostContent, newCategorySeq);
    }
}
