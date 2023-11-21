package com.allways.common.feign.theme;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThemeFeignService {

    private final ThemeFeignClient themeFeignClient;

    public Long readThemeOrder(Long userSeq){
        return themeFeignClient.readLastThemeOrderByUserSeq(userSeq);
    }


}
