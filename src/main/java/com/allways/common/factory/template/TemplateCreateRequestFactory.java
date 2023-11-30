package com.allways.common.factory.template;

import com.allways.domain.template.dto.TemplateCreateRequest;

public class TemplateCreateRequestFactory {
    public static TemplateCreateRequest createTemplateCreateRequest() {
        return new TemplateCreateRequest("templateTitle", "templateContent");
    }

    public static TemplateCreateRequest createTemplateCreateRequest(String templateTitle, String templateContent) {
        return new TemplateCreateRequest(templateTitle, templateContent);
    }
}
