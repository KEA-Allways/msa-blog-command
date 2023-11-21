package com.allways.common.feignClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FastApiClientService {
    private final FastApiFeignClient fastApiFeignClient;

    @Autowired
    public FastApiClientService(FastApiFeignClient fastApiFeignClient) {

        this.fastApiFeignClient = fastApiFeignClient;
    }

    public void sendDataToFastApi(Long themeSeq, String imageUrl) {
        FastApiDataRequest request = new FastApiDataRequest();
        request.setThemeSeq(themeSeq);
        request.setImageUrl(imageUrl);


    }
}
