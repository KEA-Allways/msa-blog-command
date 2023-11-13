package com.allways.domain.template.service;

import com.allways.domain.post.dto.UserDto;
import com.allways.domain.post.feign.UserFeignClient;

public class UserFeignClientService {
    private final UserFeignClient userFeignClient;

    public UserFeignClientService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }
    //인터페이스 구체화
    public UserDto getUserById(Long userId) {
        // 사용자 정보 가져오기
        // 예시로 출력만 하도록 했으며, 실제로는 가져온 정보를 활용하여 원하는 로직을 수행할 수 있습니다.
        System.out.println("User information for userId " + userId + ": " + userFeignClient.getUserById(userId));
        return userFeignClient.getUserById(userId);
    }
}
