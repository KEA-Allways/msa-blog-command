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

    // userSeq 헤더에서 가져오는 방식으로 수정함
    @PostMapping("/api/posts/{postSeq}/replies")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createReply(@PathVariable Long postSeq,
                                @RequestHeader(value = "userSeq") Long userSeq,
                                @RequestBody ReplyCreateRequest req) {
        replyService.createReply(req, postSeq, userSeq);
        return Response.success();
    }

    // 여기서 postSeq가 왜 필요한지 잘 모르겠네
    // 필요하면 PathVariable Long postSeq도 사용해야 하지 않을까
    @PutMapping("/api/posts/{postSeq}/replies/{replySeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateReply(@PathVariable Long replySeq,
                                @RequestBody ReplyUpdateRequest req){
        replyService.updateReply(replySeq, req);
        return Response.success();
    }

    // 여기도 마찬가지
    // 필요하면 PathVariable Long postSeq도 사용해야 하지 않을까
    @DeleteMapping("/api/posts/{postSeq}/replies/{replySeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteReply(@PathVariable Long replySeq) {
        replyService.deleteReply(replySeq);
        return Response.success();
    }
}
