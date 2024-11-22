package com.rookiefit.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rookiefit.back.entity.TrainerEntity;

public interface TrainerRepository extends JpaRepository<TrainerEntity, Long> {

}