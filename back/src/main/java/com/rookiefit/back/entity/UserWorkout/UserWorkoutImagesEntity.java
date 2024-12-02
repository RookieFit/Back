package com.rookiefit.back.entity.UserWorkout;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_workout_images")
public class UserWorkoutImagesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workoutImageId;

    private String workoutImageUri;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            @JoinColumn(name = "workout_created_date", referencedColumnName = "workout_created_date")
        })
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserWorkoutListDataEntity userWorkoutList;

    public UserWorkoutImagesEntity(String workoutimageuri) {
        this.workoutImageUri = workoutimageuri;
    }
}