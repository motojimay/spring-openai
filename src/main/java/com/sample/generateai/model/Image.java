package com.sample.generateai.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {
    @JsonProperty("title")
    private String title;

    @JsonProperty("prompt")
    private String prompt;

    @JsonProperty("numberOfImage")
    private Integer numbers;

    @JsonProperty("size")
    private String size;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
