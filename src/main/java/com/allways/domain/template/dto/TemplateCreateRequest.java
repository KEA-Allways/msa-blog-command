package com.allways.domain.template.dto;

import com.allways.common.feign.user.User;
import com.allways.common.feign.user.UserDto;
import com.allways.common.feign.user.UserFeignClientService;
import com.allways.domain.template.entity.Template;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateCreateRequest {

    private UserFeignClientService userFeignClientService;

    @NotBlank(message = "템플릿 제목을 입력해주세요.")
    private String templateTitle;

    @NotBlank(message = "템플릿 내용을 입력해주세요.")
    private String templateContent;

    public Template toEntity(TemplateCreateRequest req) {
        // JWT 토큰에서 extractSubject로 User Seq 읽어내서 new Template의 변수로 입력
//        UserDto userDto = userFeignClientService.getUserById(3L);
        return new Template(req.templateTitle, req.templateContent, 1L);
    }
}