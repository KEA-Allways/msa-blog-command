package com.allways.domain.reply.controller;

import com.allways.domain.reply.dto.ReplyCreateRequest;
import com.allways.domain.reply.dto.ReplyUpdateRequest;
import com.allways.domain.reply.service.ReplyService;
import com.allways.common.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReplyCommandController {
    private final ReplyService replyService;

    @PostMapping("/api/posts/{postSeq}/replies")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createReply(@PathVariable Long postSeq, @RequestBody ReplyCreateRequest req) {
        Long userSeq = 1L;
        replyService.createReply(req, postSeq, userSeq);
        return Response.success();
    }

    @PutMapping("/api/posts/{postSeq}/replies/{replySeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateReply(@PathVariable Long replySeq, @RequestBody ReplyUpdateRequest req){
        replyService.updateReply(replySeq, req);
        return Response.success();
    }

    @DeleteMapping("/api/posts/{postSeq}/replies/{replySeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteReply(@PathVariable Long replySeq) {
        replyService.deleteReply(replySeq);
        return Response.success();
    }
}
