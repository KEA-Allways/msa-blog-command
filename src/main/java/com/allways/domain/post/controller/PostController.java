package com.allways.domain.post.controller;

import com.allways.common.response.Response;
import com.allways.domain.post.dto.PostCreateRequest;
import com.allways.domain.post.dto.PostReadCondition;
import com.allways.domain.post.dto.PostUpdateRequest;
import com.allways.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

    //게시글 생성
    @PostMapping("/api/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(PostCreateRequest req){

        return Response.success (postService.create(req));
    }

    //게시글 조회
    @GetMapping("/api/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response read(@PathVariable Long id){
        return Response.success(postService.read(id));
    }


//    게시글 수정
//    @PutMapping("/api/posts/{seq}")
//    @ResponseStatus(HttpStatus.OK)
//    public Response update(
//            @PathVariable Long seq, @ModelAttribute PostUpdateRequest req
//            ){
//        return Response.success(postService.update(seq,req));
//    }

    //게시글 목록 조회
//    @GetMapping("/api/posts")
//    @ResponseStatus(HttpStatus.OK)
//    public Response readAll(PostReadCondition cond ){
//        return Response.success(postService.readAll(cond));
//    }

}
