package com.sample.generateimage.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ImageResponse(
        @JsonProperty("title") String title,
        @JsonProperty("prompt") String prompt,
        @JsonProperty("path") String path
) {}
