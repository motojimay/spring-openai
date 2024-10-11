package com.sample.generateimage.service;

import com.sample.generateimage.dto.ImageInfoDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ImageInfoService {
    Mono<ImageInfoDto> insertGeneratedImageInfo(ImageInfoDto dto);
    Mono<ImageInfoDto> getGeneratedImageById(Long id);
    Mono<ImageInfoDto> getGeneratedImageByTitle(String title);
    Flux<ImageInfoDto> getAllGeneratedImageInfo();
    Mono<Void> deleteGeneratedImageById(Long id);
    Mono<Void> deleteGeneratedImageByTitle(String title);
    Mono<Void> deleteAllGeneratedImageInfo();
}
