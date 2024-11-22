package com.rookiefit.back.common;

import org.springframework.stereotype.Component;

@Component
public class MaskingUserId {

    // 아이디 마스킹 처리 함수
    public String maskUserId(String userId) {
        if (userId.length() <= 4) {
            return userId.charAt(0) + "*".repeat(userId.length() - 1);
        } else {
            return userId.substring(0, 2) + "*".repeat(userId.length() - 4) + userId.substring(userId.length() - 2);
        }
    }
}
