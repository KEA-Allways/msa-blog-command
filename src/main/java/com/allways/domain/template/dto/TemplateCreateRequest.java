package com.allways.domain.template.dto;

import com.allways.domain.template.entity.Template;
import com.allways.domain.template.entity.User;
import com.allways.domain.template.feign.TemplateFeignClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateCreateRequest {

    private static TemplateFeignClient templateFeignClient;

    @NotBlank(message = "템플릿 제목을 입력해주세요.")
    private String templateTitle;

    @NotBlank(message = "템플릿 내용을 입력해주세요.")
    private String templateContent;

    public static Template toEntity(TemplateCreateRequest req) {
        User user = templateFeignClient.getUserById(1L);
        return new Template(req.templateTitle, req.templateContent, user);
    }
}
