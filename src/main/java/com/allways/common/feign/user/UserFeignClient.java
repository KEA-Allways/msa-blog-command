package com.allways.common.feign.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user-query-service" , url="${env.user-feign-url}")
public interface UserFeignClient {

    @GetMapping("/{userSeq}")
    UserFeignResponse queryUser(@PathVariable Long userSeq);

}
