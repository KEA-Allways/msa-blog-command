package com.allways.common.feign.theme;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "msa-blog-query", url = "http://localhost:8086/api/themes/feign" )
public interface ThemeFeignClient {
    @GetMapping("/readThemeOrder/{userSeq}")
    Long readLastThemeOrderByUserSeq(@PathVariable("userSeq") Long userSeq);
}
