<<<<<<<< HEAD:src/main/java/com/allways/domain/theme/entity/ThemeCreateRequest.java
package com.allways.domain.theme.entity;
========
package com.allways.domain.theme.dto;
>>>>>>>> origin/dev:src/main/java/com/allways/domain/theme/dto/ThemeCreateRequest.java

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemeCreateRequest {
    private String themeName;
    //private Long themeImgSeq;
}
