package com.sample.generateai.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("images")
public record ImageEntity(@Id Long id, String title, String prompt, int numbers , String size) {
}
