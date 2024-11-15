package com.rookiefit.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.UserWorkoutListDataEntity;
import com.rookiefit.back.entity.UserWorkoutListDataId;

@Repository
public interface UserWorkoutListDataRepository extends JpaRepository<UserWorkoutListDataEntity , UserWorkoutListDataId>{
    boolean existsByUserId(String userId);
    
    @Query("SELECT u FROM UserWorkoutListDataEntity u WHERE u.userId = :userId")
    List<UserWorkoutListDataEntity> findWorkoutListByUserId(@Param("userId") String userId);

    UserWorkoutListDataEntity findByUserIdAndWorkoutCreatedDate(String userId , String workoutCreatedDate);
}
