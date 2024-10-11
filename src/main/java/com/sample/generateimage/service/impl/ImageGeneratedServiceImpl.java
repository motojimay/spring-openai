package com.sample.generateimage.service.impl;

import com.sample.generateimage.client.DALLEClient;
import com.sample.generateimage.request.DALLERequest;
import com.sample.generateimage.service.ImageGeneratedService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImageGeneratedServiceImpl implements ImageGeneratedService {

    private final DALLEClient dalleClient;

    @Value("${openai.api.key}")
    private String openAIKey;
    @Value("${image.generate.number}")
    private int number;
    @Value("${image.generate.size}")
    private String size;

    public ImageGeneratedServiceImpl(DALLEClient dalleClient) {
        this.dalleClient = dalleClient;
    }

    public String getGeneratedImageUrl(String prompt) {
        var token = "Bearer " + openAIKey;
        var dalleRequest = new DALLERequest(prompt, number, size);
        var dalleResponse = dalleClient.getGeneratedImage(dalleRequest, token);
        return dalleResponse.data().get(0).url();
    }
}