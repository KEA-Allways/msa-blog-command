package com.allways.common.feign.theme;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


// @FeignClient(name = "msa-blog-query", url = "${env.category-feign-url}" )
@FeignClient(name = "blog-query-service")
public interface ThemeFeignClient {
    @GetMapping("api/theme/feign/readThemeOrder/{userSeq}")
    Long readLastThemeOrderByUserSeq(@PathVariable("userSeq") Long userSeq);
}
