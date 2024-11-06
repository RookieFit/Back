package com.rookiefit.back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rookiefit.back.common.CertificationNumber;
import com.rookiefit.back.dto.request.IdCheckRequestDto;
import com.rookiefit.back.dto.request.SignInRequestDto;
import com.rookiefit.back.dto.request.SignUpRequestDto;
import com.rookiefit.back.dto.request.SmsCertificationRequestDto;
import com.rookiefit.back.dto.response.ResponseDto;
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
            String phoneNumbaer = dto.getUser_phonenumber();

            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId)
                return IdCheckResponseDto.duplicatedId();

            String certificationNumber = CertificationNumber.getCertificationNumber();

            boolean isSuccessed = smsCerificationNumberProvider.sendCertificationSms(phoneNumbaer , certificationNumber);
            if( !isSuccessed ) return SmsCertificationResponseDto.smsSendFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SmsCertificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

        try {

            String userId = dto.getUserId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if (isExistId)
                return SignUpResponseDto.duplicateId();

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
            if( userEntity == null ) return SignInResponseDto.signInFail();

            String password = dto.getUser_password();
            String encodedPassword = userEntity.getUser_password();
            boolean isMatch = passwordEncoder.matches(password, encodedPassword);
            if( !isMatch ) return SignInResponseDto.signInFail();
            token = jwtProvider.create(userId);

        } catch (Exception exception) {
            exception.printStackTrace();
            ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    }
}
