package com.allways.domain.theme.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemeCreateRequest {
    private String themeName;
    //private Long themeImgSeq;
    private Long userSeq;
}
