package com.allways.domain.theme.service;

import com.allways.common.feign.theme.ThemeFeignService;
import com.allways.common.feignClient.FastApiDataRequest;
import com.allways.common.feignClient.FastApiFeignClient;
import com.allways.domain.theme.entity.Theme;
import com.allways.domain.theme.dto.ThemeCreateRequest;
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
    private final FastApiFeignClient fastApiFeignClient;

    @Transactional
    public void createTheme(ThemeCreateRequest req, Long userSeq){

        //모달에서 입력한 키워드를 받아서 FastApi Service 실행시켜야 함.
        //이미지 생성한거 여기서 저장


        //테마 생성 시 가장 높은 themeOrder를 조회한 후 + 1
        //Feign으로 수정
        Long nextOrder = themeFeignService.readThemeOrder(userSeq);

        Theme theme = new Theme(req.getThemeName(), nextOrder, userSeq);

        //테마 생성
        Theme newTheme = themeRepository.save(theme);

        FastApiDataRequest fastApiDataRequest = new FastApiDataRequest();
        fastApiDataRequest.setThemeSeq(theme.getThemeSeq());
        // 이미지 URL 생성 또는 가져오는 로직이 있다면 여기에 추가
        fastApiDataRequest.setImageUrl(req.getImageUrl());

        fastApiFeignClient.sendDataToFastApi(fastApiDataRequest);
    }

    @Transactional
    public void deleteTheme(Long themeSeq){
        Theme theme = themeRepository.findById(themeSeq).orElseThrow(ThemeNotFoundException::new);
        themeRepository.delete(theme);
    }
}
