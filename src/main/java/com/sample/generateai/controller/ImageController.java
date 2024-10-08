package com.sample.generateai.controller;

import com.sample.generateai.client.DALLEClient;
import com.sample.generateai.model.Image;
import com.sample.generateai.repository.GeneratedImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/images")
public class ImageController {

    private static final Logger log = LoggerFactory.getLogger(ImageController.class);
    private final GeneratedImageRepository generatedImageRepository;
    private final DALLEClient dalleClient;

    public ImageController(GeneratedImageRepository generatedImageRepository,
                           DALLEClient dalleClient) {
        this.generatedImageRepository = generatedImageRepository;
        this.dalleClient = dalleClient;
    }

    @PostMapping
    public void createImage(@RequestBody Image image) {
        dalleClient.generateImage(image);
//        return generatedImageRepository.findByTitle(image)
//                .then(generatedImageRepository.save(user))
//                .map(ResponseEntity::ok)
//                .doOnNext(savedUser -> System.out.println("New user created: " + savedUser))
//                .onErrorResume(e -> {
//                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
//                });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteImage(@PathVariable String id) {
        return generatedImageRepository.deleteById(id);
    }

    @GetMapping("/all")
    public Flux<Image> streamImages() {
        long start = System.currentTimeMillis();
        return null;
//        return generatedImageRepository.findAll()
//                .onBackpressureBuffer()
//                .doOnNext(user -> log.debug("Processed"))
//                .doOnError(error -> log.error("Error {}", error))
//                .doOnComplete(() -> log.info("Finished {} ms", System.currentTimeMillis() - start));
    }
}
