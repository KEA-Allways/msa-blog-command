package com.allways.common.factory.reply;

import com.allways.domain.reply.dto.ReplyCreateRequest;

public class ReplyCreateRequestFactory {
    public static ReplyCreateRequest createReplyCreateRequest() {
        return new ReplyCreateRequest("replyContent");
    }

    public static ReplyCreateRequest createReplyCreateRequest(String replyContent) {
        return new ReplyCreateRequest(replyContent);
    }
}
