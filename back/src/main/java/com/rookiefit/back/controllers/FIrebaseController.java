package com.rookiefit.back.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rookiefit.back.service.FirebaseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class FIrebaseController {
    private final FirebaseService firebaseService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException{
        System.out.println("File upload initiated");
        try {
            String fileUrl = firebaseService.uploadFile(file);
            System.out.println("File uploaded successfully: " + fileUrl);
            return ResponseEntity.ok(fileUrl);  // 업로드된 파일의 URL 반환
        } catch (IOException e) {
            System.out.println("Error during file upload: " + e.getMessage());
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }

    @PostMapping("/uploadMultiple")
    public ResponseEntity<?> uploadFiles(@RequestParam("files") List<MultipartFile> files) throws IOException{
        System.out.println("File upload initiated");
        try {
            List<String> fileUrl = firebaseService.uploadFiles(files);
            System.out.println("File uploaded successfully: " + fileUrl);
            return ResponseEntity.ok(fileUrl);  // 업로드된 파일의 URL 반환
        } catch (IOException e) {
            System.out.println("Error during file upload: " + e.getMessage());
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }
}
