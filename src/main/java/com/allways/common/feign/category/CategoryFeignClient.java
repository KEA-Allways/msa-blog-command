package com.allways.common.feign.category;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient(name = "msa-category-query", url = "${env.category-feign-url}")
@FeignClient(name = "blog-query-service")
public interface CategoryFeignClient {

    @GetMapping("api/category/feign/readCategoryOrder/{themeSeq}")
    Long readLastCategoryOrderByThemeSeq(@PathVariable("themeSeq") Long themeSeq);

}
