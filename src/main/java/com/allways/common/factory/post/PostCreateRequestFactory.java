package com.allways.common.factory.post;

import com.allways.domain.post.dto.PostCreateRequest;

public class PostCreateRequestFactory {
    public static PostCreateRequest createPostCreateRequest() {
        return new PostCreateRequest(
                "postTitle",
                "postContent",
                1L,
                "postImgUrl");
    }

    public static PostCreateRequest createPostCreateRequest(String postTitle, String postContent,
                                                            Long categorySeq, String postImgUrl) {
        return new PostCreateRequest(postTitle, postContent, categorySeq, postImgUrl);
    }
}
