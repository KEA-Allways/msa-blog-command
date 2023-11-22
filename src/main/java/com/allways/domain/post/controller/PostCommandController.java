package com.allways.domain.post.controller;

import com.allways.common.response.Response;
import com.allways.domain.post.dto.PostCreateRequest;
import com.allways.domain.post.dto.PostUpdateRequest;
import com.allways.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostCommandController {
    private final PostService postService;

    // 게시글 생성
    @PostMapping("/api/post")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createPost(@RequestHeader(value = "userSeq") Long userSeq, @RequestBody PostCreateRequest req) {
        return Response.success(postService.createPost(req, userSeq));
    }

    //게시글 삭제
    @DeleteMapping("/api/post/{postSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response deletePost(@PathVariable Long postSeq){
        postService.deletePost(postSeq);
        return Response.success();
    }

    // 게시글 수정
    @PutMapping("/api/post/{postSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response updatePost(@PathVariable Long postSeq, @RequestBody PostUpdateRequest req){
        return Response.success(postService.updatePost(req, postSeq));
    }

}
