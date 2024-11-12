package com.rookiefit.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.UserBodyDataEntity;

import jakarta.transaction.Transactional;

@Repository
public interface UserBodyDataRepository extends JpaRepository<UserBodyDataEntity ,Long> {
    
    boolean existsByUserId(String userId);

    boolean existsByDate(String date);

    List<UserBodyDataEntity> findByUserId(String userId);

    @Transactional
    void deleteAllByDate(String date);

}
