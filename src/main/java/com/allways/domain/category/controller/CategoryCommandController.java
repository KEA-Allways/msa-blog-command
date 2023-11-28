package com.allways.domain.category.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.allways.common.response.Response;
import com.allways.domain.category.dto.CategoryCreateRequest;
import com.allways.domain.category.service.CategoryCommandService;

import lombok.RequiredArgsConstructor;

import static com.allways.common.response.Response.success;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoryCommandController {
    public final CategoryCommandService categoryCommandService;

    @PostMapping("/api/theme/{themeSeq}/category")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createCategory(@PathVariable Long themeSeq,
            @RequestBody CategoryCreateRequest req) {
        categoryCommandService.createCategory(themeSeq, req);
        return success();
    }

    @DeleteMapping("/api/category/{categorySeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteCategory(@PathVariable Long categorySeq){
        categoryCommandService.deleteCategory(categorySeq);
        return success();
    }
}
