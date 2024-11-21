package com.rookiefit.back.repository.UserCommunity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.UserCommunity.UserCommunity_Answer_ListEntity;

@Repository
public interface UserCommunityAnswerRepository extends JpaRepository<UserCommunity_Answer_ListEntity , Long>{
    
}
