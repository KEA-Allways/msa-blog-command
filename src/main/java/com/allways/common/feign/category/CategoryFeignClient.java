package com.allways.common.feign.category;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msa-category-query", url = "http://localhost:8082/api/category/feign")
public interface CategoryFeignClient {

    @GetMapping("/readCategoryOrder/{themeSeq}")
    Long readLastCategoryOrderByThemeSeq(@PathVariable("themeSeq") Long themeSeq);

}
