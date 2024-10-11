package com.sample.generateimage.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "images_info")
public record ImageInfoEntity(@Id Long id, String title, String prompt, String path) {
    public ImageInfoEntity(String name, String type, String path) {
        this(null, name, type, path);
    }
}
