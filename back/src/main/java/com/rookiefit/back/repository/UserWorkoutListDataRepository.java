package com.rookiefit.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.UserWorkoutListDataEntity;

@Repository
public interface UserWorkoutListDataRepository extends JpaRepository<UserWorkoutListDataEntity , Long>{
    
}
