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
@CrossOrigin(origins = "http://localhost:3000")
public class ReplyCommandController {
    private final ReplyService replyService;

    @PostMapping("/api/post/{postSeq}/reply")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createReply(@PathVariable Long postSeq,
                                @RequestHeader(value = "userSeq") Long userSeq,
                                @RequestBody ReplyCreateRequest req) {
        replyService.createReply(req, postSeq, userSeq);
        return Response.success();
    }

    @PutMapping("/api/post/reply/{replySeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateReply(@PathVariable Long replySeq, @RequestBody ReplyUpdateRequest req){
        replyService.updateReply(replySeq, req);
        return Response.success();
    }

    @DeleteMapping("/api/post/reply/{replySeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteReply(@PathVariable Long replySeq) {
        replyService.deleteReply(replySeq);
        return Response.success();
    }
}
