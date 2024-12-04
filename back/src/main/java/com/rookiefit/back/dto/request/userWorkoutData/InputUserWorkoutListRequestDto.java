package com.rookiefit.back.dto.request.userWorkoutData;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InputUserWorkoutListRequestDto {

    @NotBlank
    private String token;

    private String comment;

    @NotBlank
    private String workout_title;

    @NotBlank
    private String workoutCreatedData;

    private List<InputUserWorkoutDetailRequestDto> workoutDetails;

    private MultipartFile[] workoutImageFiles;
}
