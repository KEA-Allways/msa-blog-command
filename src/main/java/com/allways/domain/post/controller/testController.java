package com.allways.domain.post.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.allways.common.feign.test.UserFeignClient;
import com.allways.common.feign.test.UserFeignResponse;
import com.allways.common.feign.test.UserFeignService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class testController {

	private final UserFeignService userFeignService;

	@PostMapping("/api/post/test/{user_seq}")
	@ResponseStatus(HttpStatus.OK)
	public UserFeignResponse test (@PathVariable(value = "user_seq") Long userSeq) {
		return userFeignService.queryUser(userSeq);
	}
}
