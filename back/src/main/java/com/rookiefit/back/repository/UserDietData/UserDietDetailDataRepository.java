package com.rookiefit.back.repository.UserDietData;

import com.rookiefit.back.entity.UserDiet.UserDietDetailDataEntity;
import com.rookiefit.back.entity.UserDiet.UserDietListDataEntity;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDietDetailDataRepository extends JpaRepository<UserDietDetailDataEntity, Long> {
    List<UserDietDetailDataEntity> findAllByUserDietListData_DietCreatedDate(String dietCreatedDate);

    void deleteAllByUserDietListData(UserDietListDataEntity userDietListDataEntity);
}
