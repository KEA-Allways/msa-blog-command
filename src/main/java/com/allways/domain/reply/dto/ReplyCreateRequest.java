package com.allways.domain.reply.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyCreateRequest {
    @NotBlank(message = "댓글 내용을 작성해주세요.")
    private String replyContent;
}
