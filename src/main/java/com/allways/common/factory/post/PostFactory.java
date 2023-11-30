package com.allways.common.factory.post;

import com.allways.domain.post.entity.Post;

public class PostFactory {
    public static Post createPost() {
        return new Post(
                "postTitle",
                "postContent",
                1L,
                1L
        );
    }

    public static Post createPost(
            String postTitle,
            String postContent,
            Long userSeq,
            Long categorySeq) {
        return new Post(
                postTitle,
                postContent,
                userSeq,
                categorySeq
        );
    }
}
