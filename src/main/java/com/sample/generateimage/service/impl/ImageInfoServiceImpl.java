package com.sample.generateimage.service.impl;

import com.sample.generateimage.dto.ImageInfoDto;
import com.sample.generateimage.entity.ImageInfoEntity;
import com.sample.generateimage.repository.ImageInfoRepository;
import com.sample.generateimage.service.ImageInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional(rollbackFor = Exception.class)
public class ImageInfoServiceImpl implements ImageInfoService {

    private final ImageInfoRepository imageInfoRepository;

    public ImageInfoServiceImpl(ImageInfoRepository imageInfoRepository) {
        this.imageInfoRepository = imageInfoRepository;
    }

    private static ImageInfoDto apply(ImageInfoEntity e) {
        return new ImageInfoDto(e.title(), e.prompt(), e.path());
    }

    @Override
    public Mono<ImageInfoDto> insertGeneratedImageInfo(ImageInfoDto dto) {
        return imageInfoRepository.save(new ImageInfoEntity(dto.title(), dto.prompt(), dto.title()))
                .map(ImageInfoServiceImpl::apply)
                .doOnNext(image -> System.out.println("Generate image : " + image))
                .onErrorResume(e -> { throw new RuntimeException(e); });
    }

    @Override
    public Mono<ImageInfoDto> getGeneratedImageById(Long id) {
        return imageInfoRepository.findById(id)
                .map(ImageInfoServiceImpl::apply)
                .doOnNext(image -> System.out.println("Get image : " + image))
                .onErrorResume(e -> { throw new RuntimeException(e); });
    }

    @Override
    public Mono<ImageInfoDto> getGeneratedImageByTitle(String title) {
        return imageInfoRepository.findByTitle(title)
                .map(ImageInfoServiceImpl::apply)
                .doOnNext(image -> System.out.println("Get image : " + image))
                .onErrorResume(e -> { throw new RuntimeException(e); });
    }

    @Override
    public Flux<ImageInfoDto> getAllGeneratedImageInfo() {
        return imageInfoRepository.findAll()
                .map(ImageInfoServiceImpl::apply)
                .doOnNext(image -> System.out.println("Get image : " + image))
                .onErrorResume(e -> { throw new RuntimeException(e); });
    }

    @Override
    public Mono<Void> deleteGeneratedImageById(Long id) {
        return imageInfoRepository.deleteById(id);
    }

    @Override
    public Mono<Void> deleteGeneratedImageByTitle(String title) {
        return imageInfoRepository.deleteByTitle(title);
    }

    @Override
    public Mono<Void> deleteAllGeneratedImageInfo() {
        return imageInfoRepository.deleteAll();
    }
}
