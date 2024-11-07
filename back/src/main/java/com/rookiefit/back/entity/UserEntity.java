package com.rookiefit.back.entity;

import com.rookiefit.back.dto.request.SignUpRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "user_auth")
public class UserEntity {

    @Id
    private String userId;

    private String user_password;

    private String user_email;

    private String user_phonenumber;

    private String role;

    private String type;

    public UserEntity(SignUpRequestDto dto) {
        this.userId = dto.getUserId();
        this.user_password = dto.getUser_password();
        this.user_email = dto.getUser_email();
        this.user_phonenumber = dto.getUser_phonenumber();
        this.type = "app";
        this.role = "ROLE_USER";
    }

    public UserEntity(String userId, String email, String user_phonenumber, String type) {
        this.userId = userId;
        this.user_password = "password";
        this.user_email = email;
        this.user_phonenumber = "00000000000";
        this.type = "app";
        this.role = "ROLE_USER";
    }
}
