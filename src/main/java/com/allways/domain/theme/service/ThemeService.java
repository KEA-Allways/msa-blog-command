package com.allways.domain.theme.service;

import com.allways.domain.theme.domain.Theme;
import com.allways.domain.theme.domain.ThemeCreateRequest;
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

    @Transactional
    public void createTheme(ThemeCreateRequest req){

        //테마 생성 시 가장 높은 themeOrder를 조회한 후 + 1
        Long nextOrder = themeRepository.findLastThemeOrderByUserSeq(req.getUserSeq());
        nextOrder += 1;

        //테마 생성
        Theme theme = themeRepository.save(new Theme(req.getThemeName(), nextOrder, req.getUserSeq()));

    }

    @Transactional
    public void deleteTheme(Long themeSeq){
        Theme theme = themeRepository.findById(themeSeq).orElseThrow(ThemeNotFoundException::new);
        themeRepository.delete(theme);
    }
}
