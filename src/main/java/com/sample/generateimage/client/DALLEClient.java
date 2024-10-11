package com.sample.generateimage.client;

import com.sample.generateimage.request.DALLERequest;
import com.sample.generateimage.response.DALLEResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "DALL-E", url = "https://api.openai.com")
public interface DALLEClient {
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/v1/images/generations",
            consumes = "application/json"
    )
    DALLEResponse getGeneratedImage(
            @RequestBody DALLERequest dalleRequest,
            @RequestHeader("Authorization") String authorization
    );
}
