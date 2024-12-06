package com.rookiefit.back.service.implement;

import java.io.IOException;

import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rookiefit.back.dto.request.trainer.InputTrainerRequestDto;
import com.rookiefit.back.dto.response.trainer.InputTrainerResponseDto;
import com.rookiefit.back.entity.TrainerEntity;
import com.rookiefit.back.entity.UserEntity;
import com.rookiefit.back.repository.TrainerRepository;
import com.rookiefit.back.repository.UserRepository;
import com.rookiefit.back.service.FirebaseService;
import com.rookiefit.back.service.TrainerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainerServiceImplement implements TrainerService {

        private final TrainerRepository trainerRepository;
        private final UserRepository userRepository;
        private final FirebaseService firebaseService;

        @Override
        public ResponseEntity<? super InputTrainerResponseDto> createTrainer(InputTrainerRequestDto dto,
                        String currentUserId) {

                // 유저 존재 여부 확인
                UserEntity userEntity = userRepository.findByUserId(currentUserId);
                if (userEntity == null) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .body(new InputTrainerResponseDto("존재하지 않는 사용자입니다."));
                }

                // 이미지 파일 업로드
                String licenseImageUrl = null;
                String businessRegisterImageUrl = null;
                try {
                        licenseImageUrl = firebaseService.uploadFile(dto.getLicenseImageUrl());
                        businessRegisterImageUrl = firebaseService.uploadFile(dto.getBusinessRegisterImageUrl());
                } catch (IOException exception) {
                        exception.printStackTrace();
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                        .body(new InputTrainerResponseDto("파일 업로드 중 오류가 발생했습니다."));
                }

                if (licenseImageUrl == null && businessRegisterImageUrl == null) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                }
                // TrainerEntity 생성
                TrainerEntity trainerEntity = new TrainerEntity(dto, userEntity, licenseImageUrl,
                                businessRegisterImageUrl);

                // 트레이너 정보 저장
                trainerRepository.save(trainerEntity);

                // 응답 반환
                return InputTrainerResponseDto.success("트레이너 인증 신청이 완료되었습니다.");
        }

        // TODO ADMIN 파트 다시 하기..
        // 관리자가 트레이너 요청 승인
        /*
         * @Override
         * public ResponseEntity<? super InputTrainerResponseDto> approveTrainer(String
         * userId) {
         * System.out.println("1");
         * 
         * // 유저가 존재하는지 확인
         * UserEntity userEntity = userRepository.findById(userId)
         * .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
         * System.out.println("2");
         * 
         * // 라이센스 승인 처리
         * userEntity.setIsLicensed(true);
         * userRepository.save(userEntity);
         * System.out.println("3");
         * 
         * // 응답 생성 및 반환
         * InputTrainerResponseDto responseDto = new InputTrainerResponseDto(userId,
         * true);
         * System.out.println("4");
         * return ResponseEntity.ok(responseDto);
         * }
         */
}
