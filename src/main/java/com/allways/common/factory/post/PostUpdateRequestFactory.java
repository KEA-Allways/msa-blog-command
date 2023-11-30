package com.allways.common.factory.post;

import com.allways.domain.post.dto.PostUpdateRequest;

public class PostUpdateRequestFactory {
    public static PostUpdateRequest createPostUpdateRequest() {
        return new PostUpdateRequest(
                "newPostTitle",
                "newPostContent",
                1L,
                "postImgUrl");
    }

    public static PostUpdateRequest createPostUpdateRequest(
            String newPostTitle,
            String newPostContent,
            Long newCategorySeq,
            String newPostImgUrl) {
        return new PostUpdateRequest(
                newPostTitle,
                newPostContent,
                newCategorySeq,
                newPostImgUrl);
    }
}
