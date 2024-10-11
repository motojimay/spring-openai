package com.sample.generateimage.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DALLERequest(
        @JsonProperty("prompt") String prompt,
        @JsonProperty("n") int n,
        @JsonProperty("size") String size
) {}