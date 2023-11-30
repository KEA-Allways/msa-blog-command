package com.allways.common.feign.test;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserFeignService {

    private final UserFeignClient userFeignClient;

    public UserFeignResponse queryUser(Long userSeq) {
        return userFeignClient.queryUser(userSeq);
    }

}
