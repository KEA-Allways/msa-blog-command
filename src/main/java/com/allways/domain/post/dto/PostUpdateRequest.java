package com.allways.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateRequest {
    @NotBlank(message = "새로운 게시글 제목을 입력해주세요.")
    private String postTitle;
    @NotBlank(message = "새로운 게시글 본문을 입력해주세요.")
    private String postContent;
    @PositiveOrZero(message = "새로운 올바른 카테고리 아이디를 입력해주세요.")
    private Long categorySeq;
    @NotBlank(message = "새로운 썸네일 이미지를 생성해주세요.")
    private String imageUrl;
}
