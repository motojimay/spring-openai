package com.sample.generateimage.controller;

import com.sample.generateimage.dto.ImageInfoDto;
import com.sample.generateimage.request.ImageRequest;
import com.sample.generateimage.response.ImageResponse;
import com.sample.generateimage.service.ImageDownloadService;
import com.sample.generateimage.service.ImageGeneratedService;
import com.sample.generateimage.service.ImageInfoService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageGeneratedService imageGeneratedService;
    private final ImageDownloadService imageDownloadService;
    private final ImageInfoService imageInfoService;

    public ImageController(ImageGeneratedService imageGeneratedService,
                           ImageDownloadService imageDownloadService,
                           ImageInfoService imageInfoService) {
        this.imageGeneratedService = imageGeneratedService;
        this.imageDownloadService = imageDownloadService;
        this.imageInfoService = imageInfoService;
    }

    @PostMapping("/generate")
    public Mono<ImageResponse> generateImage(@RequestBody ImageRequest request) {
        var downloadUrl = imageGeneratedService.getGeneratedImageUrl(request.prompt());
        var path = imageDownloadService.downloadImageFromUrl(downloadUrl, request.title());
        return imageInfoService.insertGeneratedImageInfo(new ImageInfoDto(request.title(), request.prompt(), path))
                .map(dto -> new ImageResponse(dto.title(), dto.prompt(), dto.path()));
    }

    @GetMapping("/get_all")
    public Flux<ImageResponse> getImages() {
        return imageInfoService.getAllGeneratedImageInfo()
                .map(dto -> new ImageResponse(dto.title(), dto.prompt(), dto.path()));
    }

    @GetMapping("/get_id/{id}")
    public Mono<ImageResponse> getImageInfoByImageId(@PathVariable Long id) {
        return imageInfoService.getGeneratedImageById(id)
                .map(dto -> new ImageResponse(dto.title(), dto.prompt(), dto.path()));
    }

    @GetMapping("/get_title/{title}")
    public Mono<ImageResponse> getImageInfoByImageTitle(@PathVariable String title) {
        return imageInfoService.getGeneratedImageByTitle(title)
                .map(dto -> new ImageResponse(dto.title(), dto.prompt(), dto.path()));
    }

    @DeleteMapping("/delete_id/{id}")
    public Mono<Void> deleteImageById(@PathVariable Long id) {
        return imageInfoService.deleteGeneratedImageById(id);
    }

    @DeleteMapping("/delete_title/{title}")
    public Mono<Void> deleteImageByTitle(@PathVariable String title) {
        return imageInfoService.deleteGeneratedImageByTitle(title);
    }

    @DeleteMapping("/delete_all")
    public Mono<Void> deleteImages() {
        return imageInfoService.deleteAllGeneratedImageInfo();
    }
}
