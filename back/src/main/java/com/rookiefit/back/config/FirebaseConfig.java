package com.rookiefit.back.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        // 클래스패스에서 firebase.json 파일을 로드
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase.json");

        if (serviceAccount == null) {
            throw new IOException("can't find firebase.json ");
        }

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setStorageBucket("rookiefit-edf53") // Firebase Storage 버킷 이름
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

                
        // FirebaseApp을 "DEFAULT"라는 이름으로 초기화
        return FirebaseApp.initializeApp(options);
    }

    @Bean
    public StorageClient storageClient(FirebaseApp firebaseApp) {
        return StorageClient.getInstance(firebaseApp);
    }

    @Bean
    public Storage storage() {
        return StorageOptions.getDefaultInstance().getService();  // Google Cloud Storage 서비스 객체
    }
}
