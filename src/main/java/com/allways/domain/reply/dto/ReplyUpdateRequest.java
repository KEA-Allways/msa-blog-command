package com.allways.domain.reply.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyUpdateRequest {
    @NotBlank(message = "새로운 댓글을 입력해주세요.")
    private String replyContent;
}
