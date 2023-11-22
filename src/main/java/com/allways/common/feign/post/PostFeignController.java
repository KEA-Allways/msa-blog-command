package com.allways.common.feign.post;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostFeignController {

	private final PostFeignService postFeignService;

	@PostMapping("/api/feign/post/post-view/{postSeq}")
	public void increasePostView(@PathVariable Long postSeq) {
		postFeignService.increasePostView(postSeq);
	}
}
