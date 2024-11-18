package com.rookiefit.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserEntity findByUserId(String userId);

    boolean existsByUserId(String userId);

    boolean existsByUserPhoneNumber(String phoneNumber);

    UserEntity findByUserPhoneNumber(String userUserPhoneNumberId);

    @Query(value = "SELECT * FROM user_auth u WHERE u.is_deleted = true AND STR_TO_DATE(u.subscripted_date, '%Y-%m-%d') <= DATE_SUB(CURRENT_DATE, INTERVAL 3 YEAR)", 
           nativeQuery = true)
    List<UserEntity> findUsersToBeDeleted();
}
