package com.allways.common.factory.template;

import com.allways.domain.template.dto.TemplateUpdateRequest;

public class TemplateUpdateRequestFactory {
    public static TemplateUpdateRequest createTemplateUpdateRequest() {
        return new TemplateUpdateRequest(
                "updateTemplateTitle",
                "updateTemplateContent");
    }

    public static TemplateUpdateRequest createTemplateUpdateRequest(
            String updateTemplateTitle, String updateTemplateContent) {
        return new TemplateUpdateRequest(
                updateTemplateTitle,
                updateTemplateContent
        );
    }
}
