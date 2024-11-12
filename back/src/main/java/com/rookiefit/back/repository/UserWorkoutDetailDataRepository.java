package com.rookiefit.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.UserWorkoutDetailDataEntity;

@Repository
public interface UserWorkoutDetailDataRepository extends JpaRepository<UserWorkoutDetailDataEntity , Long>{

}
