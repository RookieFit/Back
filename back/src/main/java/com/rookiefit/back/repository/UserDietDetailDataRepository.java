package com.rookiefit.back.repository;

import com.rookiefit.back.entity.UserDietDetailDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDietDetailDataRepository extends JpaRepository<UserDietDetailDataEntity, Long> {

        // 특정 유저의 특정 날짜에 해당하는 모든 DietDetail 데이터 조회
        List<UserDietDetailDataEntity> findByUserDietDataUserProfileUserIdAndUserDietDataDietCreateDate(String userId,
                        String dietCreateDate);

        // 특정 유저의 특정 날짜에 특정 음식 코드에 해당하는 DietDetail 데이터 조회
        UserDietDetailDataEntity findByUserDietDataUserProfileUserIdAndUserDietDataDietCreateDateAndFoodCode(
                        String userId, String dietCreateDate, String foodCode);

        // 특정 유저의 특정 날짜에 특정 음식 코드에 해당하는 DietDetail 데이터 삭제
        void deleteByUserDietDataUserProfileUserIdAndUserDietDataDietCreateDateAndFoodCode(
                        String userId, String dietCreateDate, String foodCode);

        // 특정 유저의 특정 날짜에 해당하는 모든 DietDetail 데이터 삭제
        void deleteByUserDietDataUserProfileUserIdAndUserDietDataDietCreateDate(String userId, String dietCreateDate);
}
