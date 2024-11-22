package com.rookiefit.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.UserBodyDataEntity;

import jakarta.transaction.Transactional;

@Repository
public interface UserBodyDataRepository extends JpaRepository<UserBodyDataEntity ,String> {
    
    boolean existsByUserProfile_UserAuthEntity_UserId(String userId);

    boolean existsByInbodydate(String inbody_date);

    List<UserBodyDataEntity> findByUserProfile_UserAuthEntity_UserId(String userId);

    @Transactional
    void deleteAllByInbodydate(String inbody_date);

    @Query("SELECT u FROM UserBodyDataEntity u WHERE u.userProfile.userAuthEntity.userId = :userId")
    List<UserBodyDataEntity> findUserBodyDataByUserId(@Param("userId") String userId);
}

