package com.rookiefit.back.entity;

import java.time.LocalDate;
import java.util.List;

import com.rookiefit.back.dto.request.auth.SignUpRequestDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "user_auth")
public class UserEntity {

    @Id
    private String userId;

    private String user_password;

    private String user_email;

    private String userPhoneNumber;

    private String role;

    private String type;

    private Boolean isDeleted;

    private String subscriptedDate;

    public UserEntity(SignUpRequestDto dto) {

        String currentDate = LocalDate.now().toString();

        this.userId = dto.getUserId();
        this.user_password = dto.getUser_password();
        this.user_email = dto.getUser_email();
        this.userPhoneNumber = dto.getUser_phonenumber();
        this.type = "app";
        this.role = "ROLE_USER";
        this.isDeleted = false;
        this.subscriptedDate = currentDate;
        
    }

    public UserEntity(String userId, String email, String user_phonenumber, String type) {

        String currentDate = LocalDate.now().toString();

        this.userId = userId;
        this.user_password = "password";
        this.user_email = email;
        this.userPhoneNumber = "00000000000";
        this.type = "app";
        this.role = "ROLE_USER";
        this.isDeleted = false;
        this.subscriptedDate = currentDate;
    }

    @OneToMany(mappedBy = "userAuthEntity", cascade = CascadeType.REMOVE )
    private List<UserProfileEntity> userProfiles;
}
   
