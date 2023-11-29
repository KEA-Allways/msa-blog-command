package com.allways.domain.post.controller;

import com.allways.common.response.Response;
import com.allways.domain.post.dto.PostCreateRequest;
import com.allways.domain.post.dto.PostUpdateRequest;
import com.allways.domain.post.service.PostCommandService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostCommandController {
    private final PostCommandService postCommandService;

    // 게시글 생성
    // 썸네일도 같이 생성
    @PostMapping("/api/post")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createPost(@RequestHeader(value = "userSeq") Long userSeq,
                               @RequestBody PostCreateRequest req) {
        postCommandService.createPost(req, userSeq);
        return Response.success();
    }

    // 게시글 수정
    @PutMapping("/api/post/{postSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response updatePost(@PathVariable Long postSeq,
                               @RequestBody PostUpdateRequest req){
        postCommandService.updatePost(req, postSeq);
        return Response.success();
    }

    //게시글 삭제
    @DeleteMapping("/api/post/{postSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response deletePost(@PathVariable Long postSeq) {
        postCommandService.deletePost(postSeq);
        return Response.success();
    }
}
