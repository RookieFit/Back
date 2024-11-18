package com.rookiefit.back.repository;

import com.rookiefit.back.entity.UserDietListDataEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDietDataRepository extends JpaRepository<UserDietListDataEntity, Long> {

    // 특정 유저의 특정 날짜의 전체 음식 데이터 조회
    UserDietListDataEntity findByUserProfileUserIdAndDietCreateDate(String userId, String dietCreateDate);

    // 특정 유저의 특정 날짜의 모든 데이터 삭제
    void deleteByUserProfileUserIdAndDietCreateDate(String userId, String dietCreateDate);
}
