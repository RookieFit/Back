package com.rookiefit.back.controllers;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("fileName") String fileName) throws IOException {
        try {
            byte[] fileContent = firebaseService.downloadFile(fileName);

            // 파일의 MIME 타입을 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName);  // 파일 다운로드로 설정

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(fileContent);
        } catch (IOException e) {
            System.out.println("Error during file download: " + e.getMessage());
            return ResponseEntity.status(500).body(("File download failed: " + e.getMessage()).getBytes());
        }
    }
}
