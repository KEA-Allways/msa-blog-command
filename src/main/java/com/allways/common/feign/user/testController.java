package com.allways.common.feign.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
@Slf4j
public class testController {
	private final UserFeignClient userFeignClient;

	@PostMapping("/api/post/test")
	@ResponseStatus(HttpStatus.CREATED)
	public UserFeignResponse createPost() {
		return userFeignClient.queryUser(1L);
	}

}
