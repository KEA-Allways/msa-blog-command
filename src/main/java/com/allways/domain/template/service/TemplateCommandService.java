package com.allways.domain.template.service;

import com.allways.common.feign.user.UserFeignClientService;
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
    public TemplateCreateResponse create(TemplateCreateRequest req) {
        Template template = templateRepository.save(req.toEntity(req));

        // TemplateCreateResponse에는 생성된 template의 templateSeq가 담긴다
        return new TemplateCreateResponse(template.getTemplateSeq());
    }

    // 선택된 templateSeq에 해당하는 template 수정(update)
    public void update(Long templateSeq, TemplateUpdateRequest req) {
        Template template = templateRepository.findById(templateSeq).orElseThrow(TemplateNotFoundException::new);
        template.update(req);
    }

    // 선택된 templateSeq에 해당하는 template 삭제(delete)
    public void delete(Long templateSeq) {
        Template template = templateRepository.findById(templateSeq)
                .orElseThrow(TemplateNotFoundException::new);
        templateRepository.delete(template);
    }
}
