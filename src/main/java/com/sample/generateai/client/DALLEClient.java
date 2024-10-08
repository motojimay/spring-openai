package com.sample.generateai.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.generateai.model.Image;
import lombok.NonNull;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DALLEClient {

    public void generateImage(@NonNull Image image) {
        // TODO Secret
        String openAIKey = null;
        String endpoint = "https://api.openai.com/v1/images/generations";
        String contentType = "application/json";

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.get(contentType);
        CreateImage createImage = new CreateImage(image.getPrompt(), image.getNumbers(), image.getSize());

        String json;
        try {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(createImage);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        RequestBody body = RequestBody.Companion.create(json, mediaType);
        Request request = new Request.Builder()
                .url(endpoint)
                .method("POST", body)
                .addHeader("Content-Type", contentType)
                .addHeader("Authorization", "Bearer " + openAIKey)
                .build();


        try (Response response = client.newCall(request).execute()) {
          if (!response.isSuccessful()) {
              System.out.println("Error response: " + response.body().string());
              throw new IOException("Unexpected code " + response);
          }
//          return new ImageEntity();
            System.out.println(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class CreateImage {

        @JsonProperty("model")
        private String model;

        @JsonProperty("prompt")
        private String prompt;

        @JsonProperty("n")
        private int n;

        @JsonProperty("size")
        private String size;

        public CreateImage(String prompt, int n, String size) {
            this.model = "dall-e-3";
            this.prompt = prompt;
            this.n = n;
            this.size = size;
        }

    }}
