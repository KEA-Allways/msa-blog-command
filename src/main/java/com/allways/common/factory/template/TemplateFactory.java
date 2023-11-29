package com.allways.common.factory.template;

import com.allways.domain.template.entity.Template;

public class TemplateFactory {
    public static Template createTemplate() {
        return new Template(
                "templateTitle",
                "templateContent",
                1L);
    }

    public static Template createTemplate(
            String templateTitle,
            String templateContent,
            Long userSeq) {
        return new Template(
                templateTitle,
                templateContent,
                userSeq);
    }
}
