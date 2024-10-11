package com.sample.generateimage.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ImageRequest(
    @JsonProperty("title") String title,
    @JsonProperty("prompt") String prompt
) {}
