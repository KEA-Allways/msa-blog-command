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
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequest {
    @NotBlank(message = "게시글 제목을 입력해주세요")
    private String title;

    @NotBlank(message = "게시글 본문을 입력해주세요")
    private String content;

    @PositiveOrZero(message = "올바른 카테고리 아이디를 입력해주세요")
    private Long categoryId;

    private List<MultipartFile> images = new ArrayList<>();

    private Long postView;

    //memberRepository 같은 경우 api 호출
    public static Post toEntity(PostCreateRequest req, CategoryRepository categoryRepository){
        //feignClinet 값 넣기
//        UserDto user = userFeignClientService.getUserById(3L);
        //JWT 로 유저 넣기

        return new Post(
                req.title,
                req.content,
                //jwt 받아오기
                3L,
                //카테고리 받아오기
                //post service에서 받아오기
                // 카테고리 뭐시기 하고
                categoryRepository.findById(req.getCategoryId()).orElseThrow(CategoryNotFoundException::new),
                req.images.stream().map(i->new Image(i.getOriginalFilename())).collect(toList())
        );
    }
}
