package com.rookiefit.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rookiefit.back.entity.UserProfileEntity;

import jakarta.transaction.Transactional;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long>{

    @Transactional
    void deleteAllByUserId(String userId);

    boolean existsByUserId(String userId);

    UserProfileEntity findByUserId(String userId);
    
}