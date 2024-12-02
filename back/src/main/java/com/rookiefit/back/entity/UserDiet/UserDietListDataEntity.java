package com.rookiefit.back.entity.UserDiet;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.rookiefit.back.dto.request.UserDietData.InputUserDietListRequestDto;
import com.rookiefit.back.entity.UserProfileEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_diet")
public class UserDietListDataEntity {

    @Id
    @Column(name = "diet_created_date", nullable = false)
    private String dietCreatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserProfileEntity userProfile;

    @Column(name = "total_calories")
    private Double total_calories;

    @OneToMany(mappedBy = "userDietListData", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<UserDietDetailDataEntity> userDietDetailData;

    public UserDietListDataEntity(InputUserDietListRequestDto dto, UserProfileEntity userProfileEntity) {
        this.dietCreatedDate = dto.getDiet_created_date();
        this.total_calories = dto.getTotal_calories();
        this.userProfile = userProfileEntity;
    }
}