package com.rookiefit.back.dto.request.UserDietData;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InputUserDietListRequestDto {

    @NotBlank
    private String token; // 사용자 인증 토큰

    @NotBlank
    private String diet_created_date; // 식단 생성 날짜

    private Double total_calories; // 총 칼로리

    private List<InputUserDietDetailRequestDto> dietDetails; // 세부 식단 리스트

    private Long user_diet_detail_id; // 삭제에 필요한 필드

}
