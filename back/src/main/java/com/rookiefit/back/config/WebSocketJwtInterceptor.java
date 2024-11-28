package com.rookiefit.back.config;

import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.rookiefit.back.entity.UserEntity;
import com.rookiefit.back.provider.JwtProvider;
import com.rookiefit.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebSocketJwtInterceptor implements HandshakeInterceptor {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                    WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        // 쿼리 파라미터에서 token 추출
        String token = request.getURI().getQuery();

        if (token != null && token.startsWith("token=")) {
            token = token.substring(6);  // token= 제거하고 실제 JWT 토큰만 추출

            // JWT 토큰 검증
            String userId = jwtProvider.validate(token);

            if (userId != null) {
                UserEntity user = userRepository.findByUserId(userId);
                if (user != null) {
                    // 사용자 정보를 attributes에 저장 (선택적)
                    attributes.put("user", user);
                    return true;  // WebSocket 연결 허용
                } else {
                    System.out.println("User not found.");
                }
            } else {
                System.out.println("Invalid or expired token.");
            }
        }
        // 토큰이 없거나 유효하지 않으면 WebSocket 연결을 거부
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                WebSocketHandler wsHandler, Exception ex) {
        System.out.println("Handshake completed");
    }
}
