package com.rookiefit.back.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.firebase.cloud.StorageClient;

import lombok.RequiredArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

@Service
@RequiredArgsConstructor
public class FirebaseService {
    private final Storage storage;  // Google Cloud Storage
    private final StorageClient storageClient;

    public String uploadFile(MultipartFile file) throws IOException {
        String bucketName = "rookiefit-edf53";  // Firebase Storage 버킷 이름
        String originalFileName = file.getOriginalFilename();

        // 원본 파일 이름을 그대로 사용, 확장자는 .jpg로 고정
        String fileName = (originalFileName != null ? originalFileName : "uploaded_file").replaceAll("\\.\\w+$", "") + ".jpg";

        // 이미지 파일을 BufferedImage로 읽기
        InputStream inputStream = file.getInputStream();
        BufferedImage image = ImageIO.read(inputStream);
        if (image == null) {
            throw new IOException("Uploaded file is not a valid image");
        }

        // JPEG 형식으로 이미지 변환
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", byteArrayOutputStream);  // 이미지 데이터를 JPEG 형식으로 변환
        byte[] imageData = byteArrayOutputStream.toByteArray();  // 변환된 JPEG 데이터를 byte 배열로

        // Firebase Storage에 JPEG 이미지 업로드
        Blob blob = storageClient.bucket(bucketName).create(fileName, imageData,"image/jpeg");

        // Firebase Storage에서 URL을 직접 렌더링할 수 있도록 반환
        String downloadUrl = "https://firebasestorage.googleapis.com/v0/b/" + bucketName + "/o/" + URLEncoder.encode(fileName, "UTF-8") + "?alt=media";

        return downloadUrl;
    }

    public List<String> uploadFiles(@RequestParam("files") List<MultipartFile> files) throws IOException {
        return files.stream()
                .map(file -> {
                    try {
                        return uploadFile(file);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to upload file: " + file.getOriginalFilename(), e);
                    }
                })
                .collect(Collectors.toList());
    }
}