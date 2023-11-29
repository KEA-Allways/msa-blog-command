package com.allways.common.factory.reply;

import com.allways.domain.reply.dto.ReplyUpdateRequest;

public class ReplyUpdateRequestFactory {
    public static ReplyUpdateRequest createReplyUpdateRequest() {
        return new ReplyUpdateRequest("newReplyContent");
    }

    public static ReplyUpdateRequest createReplyUpdateRequest(String newReplyContent) {
        return new ReplyUpdateRequest(newReplyContent);
    }
}
