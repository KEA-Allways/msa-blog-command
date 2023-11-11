package com.allways.domain.post.dto;

import com.allways.domain.category.exception.CategoryNotFoundException;
import com.allways.domain.category.repository.CategoryRepository;
import com.allways.domain.post.entity.Image;
import com.allways.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequest {
    @NotBlank(message = "게시글 제목을 입력해주세요")
    private String title;

    @NotBlank(message = "게시글 본문을 입력해주세요")
    private String content;


//    @Null
//    //api 호출
//    private Long memberId;

    @PositiveOrZero(message = "올바른 카테고리 아이디를 입력해주세요")
    private Long categoryId;

    private List<MultipartFile> images = new ArrayList<>();

    //memberRepository 같은 경우 api 호출
    public static Post toEntity(PostCreateRequest req, CategoryRepository categoryRepository){
        return new Post(
                req.title,
                req.content,
                //작성자 받아오기
//                memberRepository.findById(req.getMemberId()).orElseThrow(MemberNotFoundException::new),
                //카테고리 받아오기
                categoryRepository.findById(req.getCategoryId()).orElseThrow(CategoryNotFoundException::new),
                req.images.stream().map(i->new Image(i.getOriginalFilename())).collect(toList())
        );
    }

}
