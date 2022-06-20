package com.online.auto.parts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileWorker {
    @Value("${picture.path}")
    private String picturesPath;


    public boolean save(MultipartFile file, String fileName) {
        try {
            Path path = Paths.get(picturesPath + "/" + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String absoluteFilePath(String fileName) {
        return Paths.get(picturesPath + "/" + fileName).toString();
    }

}
