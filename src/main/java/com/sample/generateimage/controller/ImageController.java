package com.sample.generateimage.controller;

import com.sample.generateimage.dto.ImageInfoDto;
import com.sample.generateimage.request.ImageRequest;
import com.sample.generateimage.service.ImageDownloadService;
import com.sample.generateimage.service.ImageGeneratedService;
import com.sample.generateimage.service.ImageInfoService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Mono<ImageInfoDto>> generateImage(@RequestBody ImageRequest image) {
        var downloadUrl = imageGeneratedService.getGeneratedImageUrl(image.getPrompt());
        var path = imageDownloadService.downloadImageFromUrl(downloadUrl, image.getTitle());
        var dto = new ImageInfoDto(image.getTitle(), image.getPrompt(), path);
        return ResponseEntity.ok(imageInfoService.insertGeneratedImageInfo(dto));
    }

    @GetMapping("/get_all")
    public ResponseEntity<Flux<?>> getImages() {
        return ResponseEntity.ok(imageInfoService.getAllGeneratedImageInfo());
    }

    @GetMapping("/get_id/{id}")
    public ResponseEntity<Mono<ImageInfoDto>> getImageInfoByImageId(@PathVariable Long id) {
        return ResponseEntity.ok(imageInfoService.getGeneratedImageById(id));
    }

    @GetMapping("/get_title/{title}")
    public ResponseEntity<Mono<ImageInfoDto>> getImageInfoByImageTitle(@PathVariable String title) {
        return ResponseEntity.ok(imageInfoService.getGeneratedImageByTitle(title));
    }

    @DeleteMapping("/delete_id/{id}")
    public ResponseEntity<Mono<Void>> deleteImageById(@PathVariable Long id) {
        return ResponseEntity.ok(imageInfoService.deleteGeneratedImageById(id));
    }

    @DeleteMapping("/delete_title/{title}")
    public ResponseEntity<Mono<Void>> deleteImageByTitle(@PathVariable String title) {
        return ResponseEntity.ok(imageInfoService.deleteGeneratedImageByTitle(title));
    }

    @DeleteMapping("/delete_all")
    public ResponseEntity<Mono<Void>> deleteImages() {
        return ResponseEntity.ok(imageInfoService.deleteAllGeneratedImageInfo());
    }
}
