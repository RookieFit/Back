package com.rookiefit.back.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.rookiefit.back.entity.UserEntity;
import com.rookiefit.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCleanUpService {

    private final UserRepository userRepository;

    // 매월 1일 자정 실행
    @Scheduled(cron = "0 0 0 1 * ?")
    public void cleanupDeletedUsers() {
        System.out.println("Scheduled task triggered.");
        // 3년 이상 경과한 isDeleted = true 사용자 조회
        List<UserEntity> usersToDelete = userRepository.findUsersToBeDeleted();

        if (!usersToDelete.isEmpty()) {
            // 데이터 삭제
            userRepository.deleteAll(usersToDelete);
            System.out.println("Deleted users: " + usersToDelete.size());
        } else {
            System.out.println("No users to delete.");
        }
    }
}
