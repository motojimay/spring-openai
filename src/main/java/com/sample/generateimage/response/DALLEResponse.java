package com.sample.generateimage.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record DALLEResponse(
        @JsonProperty("data") List<Data> data
) {
    public record Data(
            @JsonProperty("url") String url
    ) {}
}