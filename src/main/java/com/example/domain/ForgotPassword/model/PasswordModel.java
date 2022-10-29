package com.example.domain.ForgotPassword.model;

import lombok.Data;

@Data
public class PasswordModel {

    private String email;
    private String phoneNumber;
    private String oldPassword;
    private String newPassword;
}
