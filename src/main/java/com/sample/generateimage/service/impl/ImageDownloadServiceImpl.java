package com.sample.generateimage.service.impl;

import com.sample.generateimage.service.ImageDownloadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageDownloadServiceImpl implements ImageDownloadService {

    private final ResourceLoader resourceLoader;

    @Value("${image.download.folder}")
    private String downloadFolder;

    public ImageDownloadServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public String downloadImageFromUrl(String imageUrl, String fileName) {
        try (InputStream inputStream = new URL(imageUrl).openStream()) {
            var resourcePath = Paths.get(resourceLoader.getResource(downloadFolder).getURI());
            if (!Files.exists(resourcePath)) {
                Files.createDirectories(resourcePath);
            }
            var outputFile = new File(resourcePath.toFile(), fileName + ".png");
            try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
            }
            return outputFile.getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException("Failed to download image from URL: " + imageUrl, e);
        }
    }
}
