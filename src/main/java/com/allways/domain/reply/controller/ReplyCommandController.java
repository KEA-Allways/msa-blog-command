package com.allways.domain.reply.controller;

import com.allways.domain.reply.dto.ReplyCreateRequest;
import com.allways.domain.reply.service.ReplyCommandService;
import com.allways.common.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ReplyCommandController {
    private final ReplyCommandService replyCommandService;

    @GetMapping("/api/posts/{postId}/replies/{replyId}")
    public Response readAll() {
        return Response.success(null);
    }

    @PostMapping("/api/posts/1/replies")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody ReplyCreateRequest req) {
        System.out.println("!!!!!!!!");
        System.out.println(req.getReplyContent());
        System.out.println(req.getPostSeq());
        System.out.println(req.getUserSeq());
        replyCommandService.create(req);
        return Response.success();
    }
}
