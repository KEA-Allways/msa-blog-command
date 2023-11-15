package com.allways.domain.reply.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyCreateRequest {
    private String replyContent;
    private Long postSeq;
    private Long userSeq;
}
