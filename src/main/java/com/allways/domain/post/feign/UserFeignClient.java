package com.allways.domain.post.feign;

import com.allways.common.response.Response;
import com.allways.domain.post.dto.UserDto;
import com.allways.domain.post.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//추후에 msa-user-query 로 변경
//해당 interface 에서 값 불러오기
@FeignClient(name = "msa-user-command", url ="http://api/users" )
public interface UserFeignClient {
    @GetMapping("/{id}")
    UserDto getUserById(@PathVariable Long id);
}
