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
@CrossOrigin(origins = "http://localhost:3000")
public class ReplyCommandController {
    private final ReplyCommandService replyCommandService;

    @PostMapping("/api/posts/{postSeq}/replies")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@PathVariable Long postSeq, @Valid @RequestBody ReplyCreateRequest req) {
        //path로 받은 postSeq를 req에 세팅
        req.setPostSeq(postSeq);
        replyCommandService.createReply(req);
        return Response.success();
    }

    @DeleteMapping("/api/posts/{postSeq}/replies/{replySeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response delete(@PathVariable Long replySeq) {
        replyCommandService.deleteReply(replySeq);
        return Response.success();
    }
}
