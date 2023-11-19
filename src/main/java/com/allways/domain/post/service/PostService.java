package com.allways.domain.post.service;

import com.allways.domain.category.repository.CategoryRepository;
import com.allways.domain.post.dto.*;
import com.allways.domain.post.entity.Post;
import com.allways.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.allways.domain.post.exception.PostNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public PostCreateResponse createPost(PostCreateRequest req,Long userSeq){
        Post post=postRepository.save(
                PostCreateRequest.toEntity(
                        req,
                        userSeq
                )
        );
        return new PostCreateResponse(post.getPostSeq());
    }

    //삭제
    @Transactional
    public void deletePost(Long postSeq){
        postRepository.deleteById(postSeq);
    }



//    @Transactional
//    public PostUpdateResponse updatePost(Long postSeq, PostUpdateRequest req){
//        Post post=postRepository.findById(postSeq).orElseThrow(PostNotFoundException::new);
//        Post.ImageUpdatedResult result =post.update(req);
//        uploadImages(result.getAddedImages(),result.getAddedImageFiles());
//        deleteImages(result.getDeletedImages());
//        return new PostUpdateResponse(postSeq);
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
