package com.allways.domain.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateRequest {
    @NotBlank(message = "카테고리 이름을 입력해주세요.")
    private String categoryName;

    @NotNull(message = "카테고리 순서를 입력하세요.")
    private Long categoryOrder;

    @NotNull(message = "어느 테마에 속하는지 정해주세요.")
    private Long themeSeq;
}
