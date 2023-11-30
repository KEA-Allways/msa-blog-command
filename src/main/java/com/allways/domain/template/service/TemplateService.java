package com.allways.domain.template.service;

import com.allways.domain.template.dto.*;
import com.allways.domain.template.repository.TemplateRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class TemplateService {
    private final TemplateRepository templateRepository;

    // template를 생성(create)
    // TemplateCreateRequest에는 templateName, templateContent, userSeq가 담긴다
    // 헤더로부터 읽은 userSeq 입력 하도록 변경
    public void createTemplate(TemplateCreateRequest req, Long userSeq) {
        templateRepository.save(req.toEntity(req, userSeq));
    }

    // 선택된 templateSeq에 해당하는 template 수정(update)
    public void updateTemplate(TemplateUpdateRequest req, Long templateSeq) {
        templateRepository.updateBySeq(templateSeq,
                req.getTemplateContent(),
                req.getTemplateTitle());
    }

    // 선택된 templateSeq에 해당하는 template 삭제(delete)
    public void deleteTemplate(Long templateSeq) {
        templateRepository.deleteById(templateSeq);
    }
}
