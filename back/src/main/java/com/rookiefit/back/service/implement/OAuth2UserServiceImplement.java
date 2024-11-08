package com.rookiefit.back.service.implement;

import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.rookiefit.back.entity.UserEntity;
import com.rookiefit.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImplement extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(request);
        String oauthClientName = request.getClientRegistration().getClientName();

        UserEntity userEntity = null;
        String userId = null;
        String email = "email@email.com";
        String userPhoneNumber = "00000000000";

        if (oauthClientName.equals("kakao")) {
            // Kakao에서 id Long으로 받아오기
            Long kakaoId = (Long) oAuth2User.getAttributes().get("id");
            userId = "kakao_" + String.valueOf(kakaoId).substring(0, 4);  // Long을 String으로 변환 후 처리
            userEntity = new UserEntity(userId, email, userPhoneNumber, "kakao");
        }

        if (oauthClientName.equals("naver")) {
            Map<String, String> responseMap = (Map<String, String>) oAuth2User.getAttributes().get("response");
            userId = "naver_" + responseMap.get("id").substring(0, 4);
            email = responseMap.get("email");
            userEntity = new UserEntity(userId, email, userPhoneNumber, "naver");
        }

        if (oauthClientName.equals("google")) {
            Long googleId = (Long) oAuth2User.getAttributes().get("id");
            userId = "google" + String.valueOf(googleId).substring(0, 4);  // Long을 String으로 변환 후 처리
            System.out.println(userId);
            userEntity = new UserEntity(userId, email, userPhoneNumber, "google");
        }

        userRepository.save(userEntity);

        return oAuth2User;

    }

}
