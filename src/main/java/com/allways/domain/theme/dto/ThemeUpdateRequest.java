package com.allways.domain.theme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemeUpdateRequest {
    private String themeName;
    //private Long themeImgSeq;
}