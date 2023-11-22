package com.allways.common.feign.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryFeignService {

    private final CategoryFeignClient categoryFeignClient;

    public Long readCategoryOrder(Long themeSeq){
        return categoryFeignClient.readLastCategoryOrderByThemeSeq(themeSeq);
    }
}
