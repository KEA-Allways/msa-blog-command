package com.allways.domain.post.dto;

import com.allways.domain.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class PostDto {
    private Long postSeq;
    private String postTitle;
    private String postContent;
//    private Long postView;
//    member 받아오기
//    image 도 받아오기
//    private List<ImageDto> images;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;

    public static PostDto toDto(Post post){
        return new PostDto(
                post.getPostSeq(),
                post.getPostTitle(),
                post.getPostContent(),
                post.getCreatedAt(),
                post.getModifiedAt()
//                post.getView()
        );
    }
}
