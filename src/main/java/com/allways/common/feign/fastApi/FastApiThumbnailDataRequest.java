package com.allways.common.feign.fastApi;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class FastApiThumbnailDataRequest {
    private Long postSeq;
    private String imageUrl;

}
