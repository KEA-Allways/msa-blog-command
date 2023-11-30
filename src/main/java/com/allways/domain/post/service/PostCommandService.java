package com.allways.domain.post.service;

import com.allways.common.feign.fastApi.FastApiClientService;

import com.allways.domain.post.dto.*;
import com.allways.domain.post.entity.Post;
import com.allways.domain.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostCommandService {

    private final PostRepository postRepository;
    private final FastApiClientService fastApiClientService;

    @Transactional
    public void createPost(PostCreateRequest req, Long userSeq) {
        Post post = postRepository.save(PostCreateRequest.toEntity(req, userSeq));
        fastApiClientService.sendDataToFastApiThumbnail(post.getPostSeq(), req.getImageUrl());
    }

    @Transactional
    public void createPostForTest(PostCreateRequest req, Long userSeq) {
        postRepository.save(PostCreateRequest.toEntity(req, userSeq));
    }

    //삭제
    @Transactional
    public void deletePost(Long postSeq){
        postRepository.deleteById(postSeq);
    }

    @Transactional
    public void updatePost(PostUpdateRequest req, Long postSeq) {
        postRepository.updatePostByPostSeq(
                postSeq,
                req.getCategorySeq(),
                req.getPostTitle(),
                req.getPostContent());
    }
}
