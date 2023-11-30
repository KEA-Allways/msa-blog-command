package com.allways.domain.theme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemeCreateRequest {
    @NotBlank(message = "테마의 이름을 입력해주세요.")
    private String themeName;
    @NotBlank(message = "테마 이미지를 등록해주세요.")
    private String imageUrl;
}
