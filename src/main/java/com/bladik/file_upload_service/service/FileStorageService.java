package com.bladik.file_upload_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FileStorageService {
    @Value("${storage.photos}") private String photoPath;
    @Value("${storage.videos}") private String videoPath;
    @Value("${storage.others}") private String otherPath;

    public String saveFiles(String type, String folderName, MultipartFile[] files) throws IOException {
        Path targetDir = resolveTargetDir(type, folderName);

        // Проверяем существование директории
        if (Files.exists(targetDir) || folderName.isEmpty()) {
            folderName = "Backup_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            targetDir = resolveTargetDir(type, folderName);
        }

        Files.createDirectories(targetDir);

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                Path filePath = targetDir.resolve(file.getOriginalFilename());
                file.transferTo(filePath);
            }
        }

        return "Files saved successfully to: " + targetDir;
    }

    private Path resolveTargetDir(String type, String folderName) {
        String dirName =
                (folderName == null) ? "Backup_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) :
                folderName;

        return Paths.get(
                switch (type) {
                    case "photo" -> photoPath;
                    case "video" -> videoPath;
                    default -> otherPath;
                },
                dirName
        );
    }
}
