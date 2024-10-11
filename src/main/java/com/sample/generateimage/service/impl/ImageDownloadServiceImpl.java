package com.sample.generateimage.service.impl;

import com.sample.generateimage.service.ImageDownloadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;

@Service
public class ImageDownloadServiceImpl implements ImageDownloadService {

    @Value("${image.download.folder}")
    private String downloadFolder;

    public ImageDownloadServiceImpl() {
    }

    @Override
    public String downloadImageFromUrl(String imageUrl, String fileName) {
        try (InputStream inputStream = new URL(imageUrl).openStream()) {
            var outputFile = new File(downloadFolder, fileName + ".png");

            try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
            }
            return "The generated images were raised in the following folders: " + outputFile.getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException("Failed to download image from URL: " + imageUrl, e);
        }
    }
}
