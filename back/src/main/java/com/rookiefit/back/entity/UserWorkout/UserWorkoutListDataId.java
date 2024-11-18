package com.rookiefit.back.entity.UserWorkout;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter; 
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserWorkoutListDataId implements Serializable { //복합키를 사용하기 위해 생성
    private String workoutCreatedDate;
    private String userId;

    public UserWorkoutListDataId(String workoutCreatedDate, String userId) {
        this.workoutCreatedDate = workoutCreatedDate;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) { //두 객체가 논리적으로 같은지 체크
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWorkoutListDataId that = (UserWorkoutListDataId) o;
        return Objects.equals(userId, that.userId) &&
               Objects.equals(workoutCreatedDate, that.workoutCreatedDate);
    }

    @Override
    public int hashCode() { //두 객체의 해시코드가 같은지 체크
        return Objects.hash(userId, workoutCreatedDate);
    }
}
