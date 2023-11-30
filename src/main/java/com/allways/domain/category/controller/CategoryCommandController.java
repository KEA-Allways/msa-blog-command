package com.allways.domain.category.controller;

import com.allways.domain.category.dto.CategoryUpdateRequest;
import com.allways.common.response.Response;
import com.allways.domain.category.dto.CategoryCreateRequest;
import com.allways.domain.category.service.CategoryService;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.allways.common.response.Response.success;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoryCommandController {
    public final CategoryService categoryService;

    @PostMapping("/api/theme/{themeSeq}/category")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createCategory(@PathVariable Long themeSeq,
            @RequestBody CategoryCreateRequest req) {
        categoryService.createCategory(themeSeq, req);
        return success();
    }

    @PutMapping("/api/category/{categorySeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateCategory(@PathVariable Long categorySeq,
                                   @RequestBody CategoryUpdateRequest req){
        categoryService.updateCategory(req, categorySeq);
        return success();
    }

    @DeleteMapping("/api/category/{categorySeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteCategory(@PathVariable Long categorySeq){
        categoryService.deleteCategory(categorySeq);
        return success();
    }
}
