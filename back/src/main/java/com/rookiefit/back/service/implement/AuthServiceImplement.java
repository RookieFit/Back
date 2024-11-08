package com.rookiefit.back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rookiefit.back.common.CertificationManager;
import com.rookiefit.back.common.CertificationNumber;
import com.rookiefit.back.dto.request.CheckCertificationRequestDto;
import com.rookiefit.back.dto.request.CheckFindUserIdRequestDto;
import com.rookiefit.back.dto.request.FindUserIdRequestDto;
import com.rookiefit.back.dto.request.IdCheckRequestDto;
import com.rookiefit.back.dto.request.SignInRequestDto;
import com.rookiefit.back.dto.request.SignUpRequestDto;
import com.rookiefit.back.dto.request.SmsCertificationRequestDto;
import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.dto.response.auth.CheckCertificationResponseDto;
import com.rookiefit.back.dto.response.auth.CheckFindUserIdResponseDto;
import com.rookiefit.back.dto.response.auth.FindUserIdResponseDto;
import com.rookiefit.back.dto.response.auth.IdCheckResponseDto;
import com.rookiefit.back.dto.response.auth.SignUpResponseDto;
import com.rookiefit.back.dto.response.auth.SmsCertificationResponseDto;
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

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {
        try {

            String userId = dto.getUserId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId)
                return IdCheckResponseDto.duplicatedId();

        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println(dto.getUserId());
            return ResponseDto.databaseError();

        }

        return IdCheckResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SmsCertificationResponseDto> smsCertification(SmsCertificationRequestDto dto) {

        try {

            String userId = dto.getUserId();
            String phoneNumber = dto.getUser_phonenumber();

            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId)
                return IdCheckResponseDto.duplicatedId();

            String certificationNumber = CertificationNumber.getCertificationNumber();
            certificationManager.saveCertificationNumber(userId, certificationNumber);

            boolean isSuccessed = smsCerificationNumberProvider.sendCertificationSms(phoneNumber, certificationNumber);
            if (!isSuccessed)
                return SmsCertificationResponseDto.smsSendFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SmsCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {

        try {

            String userId = dto.getUserId();
            String phoneNumber = dto.getUser_phonenumber();
            String certificationNumber = dto.getCertificationNumber();

            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId)
                return CheckCertificationResponseDto.duplicatedId();

            boolean isMatch = certificationManager.verifyAndDelete(userId, certificationNumber);
            if (!isMatch)
                return CheckCertificationResponseDto.certificationFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return CheckCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

        try {

            String userId = dto.getUserId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId)
                return SignUpResponseDto.duplicatedId();

            String password = dto.getUser_password();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setUser_password(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);

            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        String token = null;
        try {

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

        } catch (Exception exception) {
            exception.printStackTrace();
            ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    }

    @Override
    public ResponseEntity<? super FindUserIdResponseDto> findUserId(FindUserIdRequestDto dto) {

        try {

            String phoneNumber = dto.getUserPhoneNumber();

            boolean isExistId = userRepository.existsByUserPhoneNumber(phoneNumber);
            if (!isExistId)
                return FindUserIdResponseDto.PhoneNumber_NOT_FOUND();

            String certificationNumber = CertificationNumber.getCertificationNumber();
            certificationManager.saveCertificationNumber(phoneNumber, certificationNumber);

            boolean isSuccessed = smsCerificationNumberProvider.sendCertificationSms(phoneNumber,
                    certificationNumber);
            if (!isSuccessed)
                return SmsCertificationResponseDto.smsSendFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            ResponseDto.databaseError();
        }
        return FindUserIdResponseDto.success();
    }

    @Override
    public ResponseEntity<? super CheckFindUserIdResponseDto> checkFindUserId(CheckFindUserIdRequestDto dto) {

        try {

            String phoneNumber = dto.getUserPhoneNumber();
            String certificationNumber = dto.getCertificationNumber();
            System.out.println("sec: " + certificationNumber);
            System.out.println("thr:" + phoneNumber);

            boolean isMatch = certificationManager.verifyAndDelete(phoneNumber, certificationNumber);

            if (!isMatch) {
                return CheckFindUserIdResponseDto.certificationFail();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            ResponseDto.databaseError();
        }
        return CheckFindUserIdResponseDto.success();

    }
}
