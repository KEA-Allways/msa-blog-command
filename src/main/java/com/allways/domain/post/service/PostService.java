package com.allways.domain.post.service;

import com.allways.common.response.Response;
import com.allways.domain.category.repository.CategoryRepository;
import com.allways.domain.post.dto.*;
import com.allways.domain.post.entity.Post;
import com.allways.domain.post.feign.UserFeignClient;
import com.allways.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.allways.domain.post.exception.PostNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserFeignClientService userFeignClientService;





//    member server 에서 api 호출
//    private final memberRepository
    //file server 에서 api 호출
//    private final FileService

    @Transactional
    public PostCreateResponse create(PostCreateRequest req){
        Post post=postRepository.save(
                PostCreateRequest.toEntity(
                        req,
                        categoryRepository
                )
        );

        return new PostCreateResponse(post.getPostSeq());

    }

    //읽기
    public PostDto read(Long id) {
        return PostDto.toDto(postRepository.findById(id).orElseThrow(PostNotFoundException::new));
    }

    //삭제
    @Transactional
    public void delete(Long id){
        Post post =postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        //image 들 삭제해애함 파일에서 불러와야 하나?
//        deleteImages(post.get)
        postRepository.delete(post);
    }



//    @Transactional
//    public PostUpdateResponse update(Long id, PostUpdateRequest req){
//        Post post=postRepository.findById(id).orElseThrow(PostNotFoundException::new);
//        Post.ImageUpdatedResult result =post.update(req);
//        uploadImages(result.getAddedImages(),result.getAddedImageFiles());
//        deleteImages(result.getDeletedImages());
//        return new PostUpdateResponse(id);
//    }
//    private void uploadImages(List<Image> images, List<MultipartFile> fileImages){
//        IntStream.range(0,images.size()).forEach(i->fileService.upload(fileImages.get(i),images.get(i).getUniqueName()));
//    }
//
//    private void deleteImages(List<Image> images){
//        images.stream().forEach(i->fileService.delete(i.getUniqueName()));
//    }
//
//    public PostListDto readAll(PostReadCondition cond){
//        return PostListDto.toDto(
//                postRepository.findAllByCondition(cond)
//        );
//    }

}
