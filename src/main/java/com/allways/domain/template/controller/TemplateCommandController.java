package com.allways.domain.template.controller;

import com.allways.common.response.Response;
import com.allways.domain.template.dto.TemplateCreateRequest;
import com.allways.domain.template.dto.TemplateUpdateRequest;
import com.allways.domain.template.service.TemplateCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TemplateCommandController {
    private final TemplateCommandService templateCommandService;
    // CUD의 경우 명령의 결과값으로 Front에 전달할 값이 없으므로 단순히 Response.success()를 반환한다.

    // 템플릿(서식) 생성하기(create)
    @PostMapping("/api/templates/new-template")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody TemplateCreateRequest req) {
        templateCommandService.create(req);
        return Response.success();
    }

    // 선택된 템플릿(서식) Seq에 해당하는 template 수정하기(update)
    // TemplateUpdateRequest의 내용물은 templateName과 templateContent다
    @PostMapping("/api/templates/{templateSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateTemplate(@PathVariable Long templateSeq,
                               @RequestBody TemplateUpdateRequest req) {
        templateCommandService.update(templateSeq, req);
        return Response.success();
    }

    // 선택된 템플릿(서식) Seq에 해당하는 template 삭제하기(delete)
    @DeleteMapping("/api/templates/{templateSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response delete(@PathVariable Long templateSeq) {
        templateCommandService.delete(templateSeq);
        return Response.success(templateSeq);
    }
}