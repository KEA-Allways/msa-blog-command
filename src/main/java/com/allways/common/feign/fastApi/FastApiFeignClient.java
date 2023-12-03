package com.allways.common.feign.fastApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
// @FeignClient(name = "fastApi", url = "${env.file-command-feign-url}")
@FeignClient(name = "file-command-service")
public interface FastApiFeignClient {

    @PostMapping(value = "api/feign/theme" )
    @ResponseStatus(HttpStatus.CREATED)
    void saveThemeToFastApi(@RequestBody FastApiThemeDataRequest request);

    @PostMapping(value = "api/feign/thumbnail")
    @ResponseStatus(HttpStatus.CREATED)
    void saveThumbnailToFastApi(@RequestBody FastApiThumbnailDataRequest request);
}
