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
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {
    private final PostService postService;

    //게시글 생성
    @PostMapping("/api/posts/{userSeq}")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createPost(@PathVariable Long userSeq,@ModelAttribute PostCreateRequest req ){
        System.out.println(req.getPostTitle());
        System.out.println(req.getPostContent());
        return Response.success(postService.createPost(req, userSeq));
    }

    //게시글 삭제
    @DeleteMapping("/api/posts/{postSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response deletePost(@PathVariable Long postSeq){
        postService.deletePost(postSeq);
        return Response.success();
    }

    //게시글 수정
//    @PutMapping("/api/posts/{postSeq}")
//    @ResponseStatus(HttpStatus.OK)
//    public Response update(
//            @PathVariable Long postSeq, @ModelAttribute PostUpdateRequest req
//            ){
//        return Response.success(postService.update(postSeq,req));
//    }

}
