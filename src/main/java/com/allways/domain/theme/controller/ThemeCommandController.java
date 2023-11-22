package com.allways.domain.theme.controller;

import com.allways.common.response.Response;
import com.allways.domain.theme.dto.ThemeCreateRequest;
import com.allways.domain.theme.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.allways.common.response.Response.success;

@RestController
@RequiredArgsConstructor
public class ThemeCommandController {

    public final ThemeService themeService;


    @PostMapping("/api/theme")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createTheme(@RequestHeader(value = "userSeq") Long userSeq, @RequestBody ThemeCreateRequest req){
        themeService.createTheme(req, userSeq);
        return success();
    }

    @DeleteMapping("/api/theme/{themeSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteTheme(@PathVariable Long themeSeq){
        themeService.deleteTheme(themeSeq);
        return success();
    }
}
