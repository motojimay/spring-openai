package com.sample.generateimage.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImageRequest {
    @JsonProperty("title")
    private String title;
    @JsonProperty("prompt")
    private String prompt;
}
