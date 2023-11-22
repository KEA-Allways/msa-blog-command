package com.allways.common.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@FeignClient(name = "fastApi", url = "http://localhost:8000")
public interface FastApiFeignClient {
    @PostMapping(value = "/receive_data" )
    @ResponseStatus(HttpStatus.CREATED)
    void sendDataToFastApi(@RequestBody FastApiDataRequest request);
}
