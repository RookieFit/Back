package com.rookiefit.back.service.implement.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rookiefit.back.common.MaskingUserId;
import com.rookiefit.back.dto.request.auth.CheckCertificationRequestDto;
import com.rookiefit.back.dto.request.auth.CheckFindUserIdRequestDto;
import com.rookiefit.back.dto.request.auth.CheckFindUserPasswordRequestDto;
import com.rookiefit.back.dto.request.auth.FindUserIdRequestDto;
import com.rookiefit.back.dto.request.auth.FindUserPasswordRequestDto;
import com.rookiefit.back.dto.request.auth.IdCheckRequestDto;
import com.rookiefit.back.dto.request.auth.SignInRequestDto;
import com.rookiefit.back.dto.request.auth.SignUpRequestDto;
import com.rookiefit.back.dto.request.auth.SmsCertificationRequestDto;
import com.rookiefit.back.dto.request.auth.UserDeleteRequestDto;
import com.rookiefit.back.dto.response.auth.CheckCertificationResponseDto;
import com.rookiefit.back.dto.response.auth.CheckFindUserIdResponseDto;
import com.rookiefit.back.dto.response.auth.FindUserIdResponseDto;
import com.rookiefit.back.dto.response.auth.CheckFindUserPasswordResponseDto;
import com.rookiefit.back.dto.response.auth.FindUserPasswordResponseDto;
import com.rookiefit.back.dto.response.auth.IdCheckResponseDto;
import com.rookiefit.back.dto.response.auth.SignUpResponseDto;
import com.rookiefit.back.dto.response.auth.SmsCertificationResponseDto;
import com.rookiefit.back.dto.response.auth.UserDeleteResponseDto;
import com.rookiefit.back.dto.response.auth.SignInResponseDto;
import com.rookiefit.back.entity.UserEntity;
import com.rookiefit.back.provider.JwtProvider;
import com.rookiefit.back.provider.SmsCerificationNumberProvider;
import com.rookiefit.back.repository.UserRepository;
import com.rookiefit.back.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final SmsCerificationNumberProvider smsCerificationNumberProvider;
    private final CertificationManager certificationManager;
    private final MaskingUserId maskingUserId;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {

        String userId = dto.getUserId();
        boolean isExistId = userRepository.existsByUserId(userId);
        if (isExistId)
            return IdCheckResponseDto.duplicatedId();

        return IdCheckResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SmsCertificationResponseDto> smsCertification(SmsCertificationRequestDto dto) {

        String userId = dto.getUserId();// 필요없음
        String phoneNumber = dto.getUser_phonenumber();// 키값으로 사용

        boolean isExistId = userRepository.existsByUserId(userId);
        if (isExistId)
            return IdCheckResponseDto.duplicatedId();

        String certificationNumber = CertificationNumber.getCertificationNumber();
        certificationManager.saveCertificationNumber(userId, certificationNumber);

        boolean isSuccessed = smsCerificationNumberProvider.sendCertificationKakao(phoneNumber,
                certificationNumber);
        if (!isSuccessed)
            return SmsCertificationResponseDto.smsSendFail();

        return SmsCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {

        String userId = dto.getUserId();
        String certificationNumber = dto.getCertificationNumber();

        boolean isExistId = userRepository.existsByUserId(userId);
        if (isExistId)
            return CheckCertificationResponseDto.duplicatedId();

        boolean isMatch = certificationManager.verifyAndDelete(userId, certificationNumber);

        if (!isMatch)
            return CheckCertificationResponseDto.certificationFail();

        return CheckCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        String userId = dto.getUserId();
        boolean isExistId = userRepository.existsByUserId(userId);
        if (isExistId)
            return SignUpResponseDto.duplicatedId();

        String password = dto.getUser_password();
        String encodedPassword = passwordEncoder.encode(password);
        dto.setUser_password(encodedPassword);

        String phoneNumber = dto.getUser_phonenumber();
        boolean isExistPhoneNumber = userRepository.existsByUserPhoneNumber(phoneNumber);
        if (isExistPhoneNumber) {
            return SignUpResponseDto.duplicatedphonenumber();
        }

        UserEntity userEntity = new UserEntity(dto);
        userRepository.save(userEntity);

        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        String token = null;
        String userId = dto.getUserId();
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null)
            return SignInResponseDto.signInFail();

        String password = dto.getUser_password();
        String encodedPassword = userEntity.getUser_password();
        boolean isMatch = passwordEncoder.matches(password, encodedPassword);
        if (!isMatch)
            return SignInResponseDto.signInFail();
        token = jwtProvider.create(userId);

        return SignInResponseDto.success(token);
    }

    @Override
    public ResponseEntity<? super FindUserIdResponseDto> findUserId(FindUserIdRequestDto dto) {

        String phoneNumber = dto.getUserPhoneNumber();
        boolean isExistId = userRepository.existsByUserPhoneNumber(phoneNumber);
        if (!isExistId)
            return FindUserIdResponseDto.PhoneNumber_NOT_FOUND();

        String certificationNumber = CertificationNumber.getCertificationNumber();
        certificationManager.saveCertificationNumber(phoneNumber, certificationNumber);

        boolean isSuccessed = smsCerificationNumberProvider.sendCertificationKakao(phoneNumber,
                certificationNumber);
        if (!isSuccessed)
            return SmsCertificationResponseDto.smsSendFail();
        return FindUserIdResponseDto.success();
    }

    // 유저의 아이디와 전화번호를 입력받아 인증번호 메세지발송_김민준_2024_11_07_17:06
    @Override
    public ResponseEntity<? super FindUserPasswordResponseDto> findUserPassword(FindUserPasswordRequestDto dto) {
        String userId = dto.getUserId();// 입력받은 아이디
        String phoneNumber = dto.getUser_phonenumber();// 입력받은 전화번호

        boolean isExistId = userRepository.existsByUserId(userId);// DB에 아이디가 있는지 검사
        if (!isExistId)
            return FindUserPasswordResponseDto.idNotFound();// 아이디가 없을 경우 BAD_REQUEST 에러

        String certificationNumber = CertificationNumber.getCertificationNumber();// 인증번호 6자리 랜덤생성
        certificationManager.saveCertificationNumber(userId, certificationNumber);// hashmap에 인증번호 임시저장

        boolean isSuccessed = smsCerificationNumberProvider.sendCertificationKakao(phoneNumber, certificationNumber); // 유저전화번호로
                                                                                                                      // 인증번호
                                                                                                                      // 발송
        if (!isSuccessed)
            return SmsCertificationResponseDto.smsSendFail();

        return FindUserPasswordResponseDto.success();
    }

    @Override
    public ResponseEntity<? super CheckFindUserIdResponseDto> checkFindUserId(CheckFindUserIdRequestDto dto) {

        String phoneNumber = dto.getUserPhoneNumber();
        String certificationNumber = dto.getCertificationNumber();

        boolean isMatch = certificationManager.verifyAndDelete(phoneNumber, certificationNumber); // 인증번호 확인
        if (!isMatch) {
            return CheckFindUserIdResponseDto.certificationFail();
        } // 인증 실패 시 에러페이지로 리턴

        // 전화번호로 사용자 조회
        UserEntity userEntity = userRepository.findByUserPhoneNumber(phoneNumber);

        // 사용자 존재하지 않으면 에러 응답
        boolean isExistPhoneNumber = userRepository.existsByUserPhoneNumber(phoneNumber);
        if (!isExistPhoneNumber) {
            return CheckFindUserIdResponseDto.idNotFound();
        }

        // 아이디 마스킹 처리
        String userId = userEntity.getUserId();
        String maskedUserId = maskingUserId.maskUserId(userId);

        // 마스킹된 아이디 반환
        return CheckFindUserIdResponseDto.success(maskedUserId);
    }

    @Override
    public ResponseEntity<? super CheckFindUserPasswordResponseDto> checkFindUserPasswordResponseDto(
            CheckFindUserPasswordRequestDto dto) {

        String userId = dto.getUserId();
        String phoneNumber = dto.getUser_phonenumber();
        String certificationNumber = dto.getCertificationNumber();

        boolean isMatch = certificationManager.verifyAndDelete(userId, certificationNumber);
        if (!isMatch)
            return CheckFindUserPasswordResponseDto.certificationFail();

        return CheckFindUserPasswordResponseDto.success();
    }

    @Override
    public ResponseEntity<? super UserDeleteResponseDto> userDelete(UserDeleteRequestDto dto) {
        String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());
        UserEntity userEntity = userRepository.findByUserId(currentUserId);

        String inputPassword = dto.getUser_password();
        String encodedPassword = userEntity.getUser_password();

        if (!passwordEncoder.matches(inputPassword, encodedPassword)) {
            return UserDeleteResponseDto.passwordMismatch();
        }
        // isDeleted 상태를 true로 변경
        userEntity.setIsDeleted(true);

        // 업데이트된 엔티티 저장
        userRepository.save(userEntity);

        return UserDeleteResponseDto.success();
    }
}
