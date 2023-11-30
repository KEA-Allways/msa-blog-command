package com.allways.common.feign.fastApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FastApiClientService {
    private final FastApiFeignClient fastApiFeignClient;

    @Autowired
    public FastApiClientService(FastApiFeignClient fastApiFeignClient) {

        this.fastApiFeignClient = fastApiFeignClient;
    }

    public void sendDataToFastApiTheme(Long themeSeq, String imageUrl) {
        FastApiThemeDataRequest request = new FastApiThemeDataRequest();
        request.setThemeSeq(themeSeq);
        request.setImageUrl(imageUrl);
        fastApiFeignClient.saveThemeToFastApi(request);
    }
    public void sendDataToFastApiThumbnail(Long postSeq,String imageUrl){
        FastApiThumbnailDataRequest request = new FastApiThumbnailDataRequest();
        request.setPostSeq(postSeq);
        request.setImageUrl(imageUrl);
        fastApiFeignClient.saveThumbnailToFastApi(request);

    }
}
