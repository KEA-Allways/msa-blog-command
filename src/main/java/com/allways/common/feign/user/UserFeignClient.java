package com.allways.common.feign.user;

import com.allways.common.feign.user.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//추후에 msa-user-query 로 변경
//해당 interface 에서 값 불러오기
@FeignClient(name = "msa-user-command", url ="http://api/users" )
public interface UserFeignClient {
    @GetMapping("/{seq}")
    UserDto getUserById(@PathVariable Long seq);
}
