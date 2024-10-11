package com.sample.generateimage.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DALLEResponse {

    @JsonProperty("data")
    private List<Data> data;

    @lombok.Data
    public static class Data {
        @JsonProperty("url")
        private String url;
    }
}