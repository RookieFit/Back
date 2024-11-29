package com.rookiefit.back.repository.UserWorkout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.UserWorkout.UserWorkoutImagesEntity;

@Repository
public interface UserWorkoutImagesRepository extends JpaRepository<UserWorkoutImagesEntity, Long>{
    
}
