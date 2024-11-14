package com.rookiefit.back.dto.response.userWorkoutData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class DeleteUserWorkoutListResponseDto extends ResponseDto{
    private DeleteUserWorkoutListResponseDto() {
        super();
    }

    public static ResponseEntity<DeleteUserWorkoutListResponseDto> success() {
        DeleteUserWorkoutListResponseDto reponseBody = new DeleteUserWorkoutListResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(reponseBody);
    }

    //undefined workoutlist 오류 코드 만들것_2024_11_13_19:43_김민준
    public static ResponseEntity<? super DeleteUserWorkoutListResponseDto> failure(String string) {
        throw new UnsupportedOperationException("Unimplemented method 'failure'");
    }
}
