package com.allways.common.feign.post;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allways.domain.post.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostFeignService {

	private final PostRepository postRepository;

	@Transactional
	public void increasePostView(Long postSeq) {
		postRepository.increasePostView(postSeq);
	}
}
