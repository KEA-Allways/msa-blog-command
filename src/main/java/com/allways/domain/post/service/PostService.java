package com.allways.domain.post.service;

import com.allways.domain.category.repository.CategoryRepository;
import com.allways.domain.post.dto.*;
import com.allways.domain.post.entity.Post;
import com.allways.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.allways.domain.post.exception.PostNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public PostCreateResponse createPost(PostCreateRequest req, Long userSeq){
        Post post = postRepository.save(PostCreateRequest.toEntity(req, userSeq));
        return new PostCreateResponse(post.getPostSeq());
    }

    //삭제
    @Transactional
    public void deletePost(Long postSeq){
        postRepository.deleteById(postSeq);
    }

    @Transactional
    public PostUpdateResponse updatePost(PostUpdateRequest req, Long postSeq) {
        postRepository.updatePostByPostSeq(postSeq, req.getCategorySeq(), req.getPostTitle(), req.getPostContent());
        return new PostUpdateResponse(postSeq);
    }
}
