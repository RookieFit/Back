package com.rookiefit.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.UserWorkoutDetailDataEntity;
import com.rookiefit.back.entity.UserWorkoutListDataEntity;

@Repository
public interface UserWorkoutDetailDataRepository extends JpaRepository<UserWorkoutDetailDataEntity , Long>{
    List<UserWorkoutDetailDataEntity> findByWorkoutDetailCreatedDate(String workoutDetailCreatedDate);
    void deleteByUserWorkoutList(UserWorkoutListDataEntity userWorkoutListDataEntity);
}
