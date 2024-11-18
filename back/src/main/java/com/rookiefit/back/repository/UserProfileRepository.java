package com.rookiefit.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.UserProfileEntity;

import jakarta.transaction.Transactional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long>{

    @Transactional
    void deleteAllByUserAuthEntity_UserId(String userId);

    boolean existsByUserAuthEntity_UserId(String userId);

    UserProfileEntity findByUserAuthEntity_UserId(String userId);
    
}