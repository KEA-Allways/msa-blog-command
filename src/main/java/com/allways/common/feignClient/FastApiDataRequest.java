package com.allways.common.feignClient;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class FastApiDataRequest {
    private Long themeSeq;
    private String imageUrl;
}
