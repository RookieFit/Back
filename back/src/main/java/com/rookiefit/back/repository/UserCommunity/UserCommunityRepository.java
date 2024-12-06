package com.rookiefit.back.repository.UserCommunity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.UserCommunity.UserCommunityEntity;

@Repository
public interface UserCommunityRepository extends JpaRepository<UserCommunityEntity , Long>{

    Optional<UserCommunityEntity> findByCommunityListId(Long communityListId);

    List<UserCommunityEntity> findByCommunityContentType(String communityContentType);

    List<UserCommunityEntity> findByCommunityTitleContainingOrCommunityContentContainingOrCommunityAuthorContaining(
        String titleKeyword, String contentKeyword, String authorKeyword
        );
    List<UserCommunityEntity> findByCommunityTitleContaining(String keyword);
    List<UserCommunityEntity> findByCommunityContentContaining(String keyword);
    List<UserCommunityEntity> findByCommunityAuthorContaining(String keyword);
}
