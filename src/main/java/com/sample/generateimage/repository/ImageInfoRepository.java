package com.sample.generateimage.repository;

import com.sample.generateimage.entity.ImageInfoEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface ImageInfoRepository extends R2dbcRepository<ImageInfoEntity, Long> {
    Mono<ImageInfoEntity> findByTitle(String title);
    Mono<Void> deleteByTitle(String title);
}

