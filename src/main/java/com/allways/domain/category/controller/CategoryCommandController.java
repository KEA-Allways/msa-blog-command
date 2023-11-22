
package com.allways.domain.category.controller;
import org.springframework.http.HttpStatus;

import com.allways.common.response.Response;
import com.allways.domain.category.dto.CategoryCreateRequest;
import com.allways.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import static com.allways.common.response.Response.success;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryCommandController {

    public final CategoryService categoryService;

    @PostMapping("/api/themes/{themeSeq}/categories/new-category")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createCategory(@PathVariable Long themeSeq, @RequestBody CategoryCreateRequest req){
        categoryService.createCategory(req, themeSeq);
        return success();
    }

    @DeleteMapping("/api/categories/{categorySeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteCategory(@PathVariable Long categorySeq){
        categoryService.deleteCategory(categorySeq);
        return success();
    }
}
