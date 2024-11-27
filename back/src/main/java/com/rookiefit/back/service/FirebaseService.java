/*package com.rookiefit.back.service;

import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.firebase.cloud.StorageClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FirebaseService {
     
    private final Storage storage;
    private final StorageClient storageClient;

     public String uploadFile(MultipartFile file) {
        String bucketName = "rookiefit-edf53.firebasestorage.app";  // Firebase Storage의 버킷 이름
        String fileName = file.getOriginalFilename();

        // Firebase Storage에 파일 업로드
        BucketInfo bucketInfo = BucketInfo.newBuilder(bucketName).build();
        Bucket bucket = storage.create(bucketInfo);

        BlobInfo.newBuilder(bucket, fileName).build();

        // 업로드된 파일의 URL 반환
        return "https://firebasestorage.googleapis.com/v0/b/" + bucketName + "/o/" + fileName + "?alt=media";
    }

    public byte[] downloadFile(String fileName) {
        Blob blob = storageClient.bucket().get(fileName);
        // 파일을 바이트 배열로 다운로드
        try (ReadableByteChannel readChannel = blob.reader()) {
            //return Channels.newInputStream(readChannel).readAllBytes();
        }
    }
}
*/