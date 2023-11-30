package com.allways.domain.theme.service;

import com.allways.common.factory.theme.ThemeFactory;
import com.allways.common.feign.fastApi.FastApiClientService;
import com.allways.common.feign.theme.ThemeFeignService;
import com.allways.domain.theme.dto.ThemeUpdateRequest;
import com.allways.domain.theme.entity.Theme;
import com.allways.domain.theme.dto.ThemeCreateRequest;
import com.allways.domain.theme.repository.ThemeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ThemeService {

    private final ThemeRepository themeRepository;
    private final ThemeFeignService themeFeignService;
    private final FastApiClientService fastApiClientService;

    @Transactional
    public void createTheme(ThemeCreateRequest createRequest, Long userSeq){

        //모달에서 입력한 키워드를 받아서 FastApi Service 실행시켜야 함.
        //이미지 생성한거 여기서 저장

        //테마 생성 시 가장 높은 themeOrder를 조회한 후 + 1
        //Feign으로 수정
        Long nextOrder = themeFeignService.readThemeOrder(userSeq);

        Theme theme = ThemeFactory.createTheme(createRequest.getThemeName(), nextOrder, userSeq);

        //테마 생성
        Theme savedTheme = themeRepository.save(theme);

        //테마 seq 생김
        Long themeSeq = savedTheme.getThemeSeq();
        String imageUrl = createRequest.getImageUrl();

        fastApiClientService.sendDataToFastApiTheme(themeSeq, imageUrl);
    }

    @Transactional
    public void updateTheme(ThemeUpdateRequest updateRequest, Long userSeq, Long themeSeq) {
        // 작성 필요
        // 테마 업데이트 기능을 넣을 거면 테마 이미지가 새로운게 들어왔는지
        Long nextOrder = themeFeignService.readThemeOrder(userSeq);

        themeRepository.updateByThemeSeq(
                themeSeq,
                updateRequest.getThemeName(),
                nextOrder);
        
        // feign으로 날려주는 내용 필요
    }

    // feign으로 인해 따로 만든 test용 함수인데 차라리 테스트 안하는게 좋으면 삭제하면 됨
    @Transactional
    public void createThemeForTest(ThemeCreateRequest req, Long userSeq) {
        Long nextOrder = 10L;

        Theme theme = ThemeFactory.createTheme(req.getThemeName(), nextOrder, userSeq);

        themeRepository.save(theme);
    }

    @Transactional
    public void updateThemeForTest(
            ThemeUpdateRequest updateRequest,
            Long themeSeq) {
        Long nextOrder = 10L;
        themeRepository.updateByThemeSeq(
                themeSeq,
                updateRequest.getThemeName(),
                nextOrder);
    }

    @Transactional
    public void deleteTheme(Long themeSeq) {
        themeRepository.deleteById(themeSeq);
    }
}
