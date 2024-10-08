package com.sample.generateai.repository;

import com.sample.generateai.entity.ImageEntity;
import com.sample.generateai.model.Image;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface GeneratedImageRepository extends R2dbcRepository<ImageEntity, String> {
    Mono<ImageEntity> findByTitle(String title);
}

