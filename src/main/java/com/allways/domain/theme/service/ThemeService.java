package com.allways.domain.theme.service;

import com.allways.domain.theme.entity.Theme;
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

    @Transactional
    public void createTheme(ThemeCreateRequest req, Long userSeq){

        //모달에서 입력한 키워드를 받아서 FastApi Service 실행시켜야 함.
        //이미지 생성한거 여기서 저장


        //테마 생성 시 가장 높은 themeOrder를 조회한 후 + 1
        //Feign으로 수정
        Long nextOrder = themeRepository.findLastThemeOrderByUserSeq(userSeq);
        nextOrder += 1;

        //테마 생성
        themeRepository.save(new Theme(req.getThemeName(), nextOrder, userSeq));

    }

    @Transactional
    public void deleteTheme(Long themeSeq){
        Theme theme = themeRepository.findById(themeSeq).orElseThrow(ThemeNotFoundException::new);
        themeRepository.delete(theme);
    }
}
