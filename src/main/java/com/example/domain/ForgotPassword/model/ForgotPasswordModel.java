package com.example.domain.ForgotPassword.model;

import lombok.Data;

@Data
public class ForgotPasswordModel {

    private String email;
    private String phoneNumber;
    private String newPassword;
}
