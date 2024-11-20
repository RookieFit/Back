package com.rookiefit.back.config;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;

@Configuration
public class FirebaseConfig {
    
        @Bean
        public void initializeFirebase() throws IOException {
            // Firebase 서비스 계정 키 JSON 파일 경로
            FileInputStream serviceAccount =
                    new FileInputStream("/src/main/resources/firebase.json");

            // Firebase SDK 초기화
           FirebaseOptions options = new FirebaseOptions.Builder()
                            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                            .build();

            // FirebaseApp 초기화
            FirebaseApp.initializeApp(options);
        }

        @Bean
        public StorageClient storageClient() {
            // Firebase Storage Client 초기화
            return StorageClient.getInstance();
        }
}
