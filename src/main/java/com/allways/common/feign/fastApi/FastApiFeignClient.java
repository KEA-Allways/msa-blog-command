package com.allways.common.feign.fastApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@FeignClient(name = "fastApi", url = "http://localhost:8000")
public interface FastApiFeignClient {
    @PostMapping(value = "/receive_theme" )
    @ResponseStatus(HttpStatus.CREATED)
    void sendDataToFastApiTheme(@RequestBody FastApiThemeDataRequest request);

    @PostMapping(value = "/receive_thumbnail")
    @ResponseStatus(HttpStatus.CREATED)
    void sendDateToFastApiThumbnail(@RequestBody FastApiThumbnailDataRequest request);
}
