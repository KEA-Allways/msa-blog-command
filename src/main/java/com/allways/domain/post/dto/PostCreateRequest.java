package com.allways.domain.post.dto;

import com.allways.domain.post.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequest {
    @NotBlank(message = "게시글 제목을 입력해주세요")
    private String postTitle;
    @NotBlank(message = "게시글 본문을 입력해주세요")
    private String postContent;
    @PositiveOrZero(message = "올바른 카테고리 아이디를 입력해주세요")
    private Long categorySeq;
    @NotBlank(message = "썸네일 이미지를 생성해주세요.")
    private String imageUrl;


    // memberRepository 같은 경우 api 호출
    public static Post toEntity(PostCreateRequest req, Long userSeq){
        return new Post(
                req.postTitle,
                req.postContent,
                //카테고리 받아오기
                userSeq,
                //게시글 생성에서 뽑아오는것
                req.categorySeq
                //req.images.stream().map(i->new Image(i.getOriginalFilename())).collect(toList())
        );
    }
}
