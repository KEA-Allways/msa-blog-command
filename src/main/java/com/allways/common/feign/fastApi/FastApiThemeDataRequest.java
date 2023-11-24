package com.allways.common.feign.fastApi;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
//theme 전용
public class FastApiThemeDataRequest {
    private Long themeSeq;
    private String imageUrl;
}
