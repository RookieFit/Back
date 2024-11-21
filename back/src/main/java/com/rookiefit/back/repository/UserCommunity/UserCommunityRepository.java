package com.rookiefit.back.repository.UserCommunity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.UserCommunity.UserCommunityEntity;


@Repository
public interface UserCommunityRepository extends JpaRepository<UserCommunityEntity , Long>{
    Optional<UserCommunityEntity> findByCommunityListId(Long communityListId);
}
