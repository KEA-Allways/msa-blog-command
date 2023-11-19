package com.allways.domain.theme.service;

import com.allways.common.feign.theme.ThemeFeignService;
import com.allways.domain.theme.dto.Theme;
import com.allways.domain.theme.entity.ThemeCreateRequest;
import com.allways.domain.theme.exception.ThemeNotFoundException;
import com.allways.domain.theme.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository themeRepository;

    private final ThemeFeignService themeFeignService;

    @Transactional
    public void createTheme(ThemeCreateRequest req, Long userSeq){

        //모달에서 입력한 키워드를 받아서 FastApi Service 실행시켜야 함.
        //이미지 생성한거 여기서 저장

        Long nextOrder = themeFeignService.readThemeOrder(userSeq);

        //테마 생성
        themeRepository.save(new Theme(req.getThemeName(), nextOrder, userSeq));

    }

    @Transactional
    public void deleteTheme(Long themeSeq){
        Theme theme = themeRepository.findById(themeSeq).orElseThrow(ThemeNotFoundException::new);
        themeRepository.delete(theme);
    }
}
