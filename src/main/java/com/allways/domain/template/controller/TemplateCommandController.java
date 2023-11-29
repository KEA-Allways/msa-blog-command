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

	// 템플릿(서식) 생성하기(create)
	@PostMapping("/api/template")
	@ResponseStatus(HttpStatus.CREATED)
	public Response createTemplate(@RequestHeader(value = "userSeq") Long userSeq, @RequestBody TemplateCreateRequest req) {
		templateCommandService.createTemplate(req, userSeq);
		return Response.success();
	}

	// 선택된 템플릿(서식) Seq에 해당하는 template 수정하기(update)
	@PutMapping("/api/template/{templateSeq}")
	@ResponseStatus(HttpStatus.OK)
	public Response updateTemplate(@PathVariable Long templateSeq, @RequestBody TemplateUpdateRequest req) {
		templateCommandService.updateTemplate(req, templateSeq);
		return Response.success();
	}

	// 선택된 템플릿(서식) Seq에 해당하는 template 삭제하기(delete)
	@DeleteMapping("/api/template/{templateSeq}")
	@ResponseStatus(HttpStatus.OK)
	public Response deleteTemplate(@PathVariable Long templateSeq) {
		templateCommandService.deleteTemplate(templateSeq);
		return Response.success(templateSeq);
	}
}