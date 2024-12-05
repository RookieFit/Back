package com.rookiefit.back.repository.UserWorkout;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.UserWorkout.UserWorkoutDetailDataEntity;
import com.rookiefit.back.entity.UserWorkout.UserWorkoutListDataEntity;

@Repository
public interface UserWorkoutDetailDataRepository extends JpaRepository<UserWorkoutDetailDataEntity , Long>{
    List<UserWorkoutDetailDataEntity> findByUserWorkoutList_UserIdAndWorkoutDetailCreatedDate(String currentUserId, String workoutDetailCreatedDate);
    void deleteByUserWorkoutList(UserWorkoutListDataEntity userWorkoutListDataEntity);
}
