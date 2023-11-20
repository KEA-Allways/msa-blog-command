package com.allways.domain.template.dto;

import com.allways.domain.template.entity.Template;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateCreateRequest {

    @NotBlank(message = "템플릿 제목을 입력해주세요.")
    private String templateTitle;

    @NotBlank(message = "템플릿 내용을 입력해주세요.")
    private String templateContent;

    public Template toEntity(TemplateCreateRequest req, Long userSeq) {
        return new Template(req.templateTitle, req.templateContent, userSeq);
    }
}