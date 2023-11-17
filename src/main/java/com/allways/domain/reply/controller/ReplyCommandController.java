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

    @PostMapping("/api/posts/{postSeq}/replies")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createReply(@PathVariable Long postSeq, @RequestBody ReplyCreateRequest req) {
        Long userSeq = 1L;
        replyCommandService.createReply(req, postSeq, userSeq);
        return Response.success();
    }

    @DeleteMapping("/api/posts/{postSeq}/replies/{replySeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteReply(@PathVariable Long replySeq) {
        replyCommandService.deleteReply(replySeq);
        return Response.success();
    }
}
