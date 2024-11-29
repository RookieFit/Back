package com.rookiefit.back.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.firebase.cloud.StorageClient;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class FirebaseService {
     
    private final Storage storage;   // Cloud Storage
    private final StorageClient storageClient;

    public String uploadFile(MultipartFile file) throws IOException {
        String bucketName = "rookiefit-edf53";  // Firebase Storage 버킷 이름
        String fileName = file.getOriginalFilename();

        // Firebase Storage에 파일 업로드
        InputStream inputStream = file.getInputStream();
        Blob blob = storageClient.bucket(bucketName).create(fileName, inputStream);
    
        try {
            // Firebase Storage URL 형식으로 반환 (파일 이름을 URL-safe 형식으로 인코딩)
            String encodedFileName = URLEncoder.encode(fileName, "UTF-8");
            return "https://firebasestorage.googleapis.com/v0/b/" + bucketName + "/o/" + encodedFileName + "?alt=media";
        } catch (UnsupportedEncodingException e) {
            throw new IOException("Error encoding file name", e);
        }
    }

    // 파일 다운로드
    public byte[] downloadFile(String fileName) throws IOException {
        String bucketName = "rookiefit-edf53";  // Firebase Storage 버킷 이름

        // Firebase Storage에서 파일 다운로드
        Bucket bucket = storage.get(bucketName);
        Blob blob = bucket.get(fileName);

        if (blob == null) {
            throw new IOException("File not found in Firebase Storage.");
        }

        // 파일을 byte 배열로 반환
        return blob.getContent();
    }
}
