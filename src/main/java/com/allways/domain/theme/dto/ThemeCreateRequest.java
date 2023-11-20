package com.allways.domain.theme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemeCreateRequest {
    private String themeName;
    private String imageUrl;
    //private Long themeImgSeq;
}
