package com.bladik.file_upload_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import java.io.IOException;

@Controller
public class UploadController {

    private final FileStorageService storageService;

    @Autowired
    public UploadController(FileStorageService storageService) {
        this.storageService = storageService;
    }


    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> handleUpload(
            @RequestParam String type,
            @RequestParam(required = false) String folderName,
            @RequestParam("files") MultipartFile[] files) {

        System.out.println("Получен запрос. Тип: " + type + ", папка: " + folderName);
        System.out.println("Количество файлов: " + files.length);

        if (files.length == 0) {
            return ResponseEntity.badRequest().body("Ошибка: Не выбраны файлы для загрузки");
        }

        try {
            String result = storageService.saveFiles(type, folderName, files);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Ошибка загрузки: " + e.getMessage());
        }
    }
}