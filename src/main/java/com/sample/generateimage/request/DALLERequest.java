package com.sample.generateimage.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DALLERequest {
    @JsonProperty("prompt")
    private String prompt;

    @JsonProperty("n")
    private int n;

    @JsonProperty("size")
    private String size;
}
