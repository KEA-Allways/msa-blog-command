package com.allways.common.factory.reply;

import com.allways.domain.reply.entity.Reply;

public class ReplyFactory {
    public static Reply createReply() {
        return new Reply(
                "replyContent",
                1L,
                1L);
    }

    public static Reply createReply(
            String replyContent,
            Long postSeq,
            Long userSeq) {
        return new Reply(replyContent, postSeq, userSeq);
    }
}
