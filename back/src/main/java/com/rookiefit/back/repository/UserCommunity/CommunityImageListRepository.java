package com.rookiefit.back.repository.UserCommunity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.UserCommunity.CommunityImageListEntity;

@Repository
public interface CommunityImageListRepository extends JpaRepository<CommunityImageListEntity,Long>{
    
}
