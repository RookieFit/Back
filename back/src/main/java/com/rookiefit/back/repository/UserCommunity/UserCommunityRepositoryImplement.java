/*package com.rookiefit.back.repository.UserCommunity;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rookiefit.back.entity.UserCommunity.UserCommunityEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCommunityRepositoryImplement implements UserCommunityRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<UserCommunityEntity> searchByFieldAndKeyword(String field, String keyword) {
        QUserCommunityEntity community = QUserCommunityEntity.userCommunityEntity;

        // 검색 필드에 따라 조건 생성
        BooleanExpression condition;
        switch (field) {
            case "title":
                condition = community.communityTitle.containsIgnoreCase(keyword);
                break;
            case "content":
                condition = community.communityContent.containsIgnoreCase(keyword);
                break;
            case "author":
                condition = community.communityAuthor.containsIgnoreCase(keyword);
                break;
            default:
                throw new IllegalArgumentException("Invalid search field: " + field);
        }

        // QueryDSL로 데이터 검색
        return jpaQueryFactory.selectFrom(community)
                              .where(condition)
                              .fetch();
    }
}
}*/
