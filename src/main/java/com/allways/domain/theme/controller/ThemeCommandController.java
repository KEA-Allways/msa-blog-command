package com.allways.domain.theme.controller;

import com.allways.common.response.Response;
import com.allways.domain.theme.dto.ThemeCreateRequest;
import com.allways.domain.theme.dto.ThemeUpdateRequest;
import com.allways.domain.theme.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.allways.common.response.Response.success;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ThemeCommandController {
    public final ThemeService themeService;

    @PostMapping("/api/themes/new-theme")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createTheme(@RequestHeader(value = "userSeq") Long userSeq,
                                @RequestBody ThemeCreateRequest req) {
        themeService.createTheme(req, userSeq);
        return success();
    }

    // theme update 추가 아직 상세 구현 필요
    @PutMapping("/api/themes/{themeSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateTheme(@PathVariable Long themeSeq,
                                @RequestBody ThemeUpdateRequest req) {
        themeService.updateTheme(req, themeSeq);
        return success();
    }

    @DeleteMapping("/api/themes/{themeSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteTheme(@PathVariable Long themeSeq) {
        themeService.deleteTheme(themeSeq);
        return success();
    }
}
