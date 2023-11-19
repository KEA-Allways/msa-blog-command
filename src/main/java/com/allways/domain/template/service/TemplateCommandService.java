package com.allways.domain.template.service;

import com.allways.domain.template.entity.Template;
import com.allways.domain.template.dto.*;
import com.allways.domain.template.exception.TemplateNotFoundException;
import com.allways.domain.template.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class TemplateCommandService {
    private final TemplateRepository templateRepository;

    // template를 생성(create)
    // TemplateCreateRequest에는 templateName, templateContent, userSeq가 담긴다
    // 헤더로부터 읽은 userSeq 입력 하도록 변경
    public TemplateCreateResponse createTemplate(TemplateCreateRequest req, Long userSeq) {
        Template template = templateRepository.save(req.toEntity(req, userSeq));

        // TemplateCreateResponse에는 생성된 template의 templateSeq가 담긴다
        return new TemplateCreateResponse(template.getTemplateSeq());
    }

    // 선택된 templateSeq에 해당하는 template 수정(update)
    public void updateTemplate(TemplateUpdateRequest req, Long templateSeq) {
        templateRepository.updateById(templateSeq,
                req.getTemplateContent(),
                req.getTemplateTitle());
    }

    // 선택된 templateSeq에 해당하는 template 삭제(delete)
    public void deleteTemplate(Long templateSeq) {
        templateRepository.deleteById(templateSeq);
    }
}
