package com.allways.domain.theme.controller;

import com.allways.common.response.Response;
import com.allways.domain.theme.entity.ThemeCreateRequest;
import com.allways.domain.theme.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.allways.common.response.Response.success;

@RestController
@RequiredArgsConstructor
public class ThemeCommandController {

    public final ThemeService themeService;

    @PostMapping("/api/themes/new-theme")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody ThemeCreateRequest req){
        themeService.createTheme(req);
        return success();
    }

    @DeleteMapping("/api/themes/{themeSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response delete(@PathVariable Long themeSeq){
        themeService.deleteTheme(themeSeq);
        return success();
    }
}
