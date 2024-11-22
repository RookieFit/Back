package com.rookiefit.back.repository.UserDietData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.UserDiet.UserDietListDataEntity;

@Repository
public interface UserDietDataRepository extends JpaRepository<UserDietListDataEntity, String> {

    // 특정 유저의 특정 날짜의 전체 음식 데이터 조회
    UserDietListDataEntity findByUserProfile_UserAuthEntity_UserIdAndDietCreatedDate(String userId,
            String diet_created_date);

    void deleteAllByUserProfile_UserAuthEntity_UserIdAndDietCreatedDate(String userId, String dietCreatedDate);
}
