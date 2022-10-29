package com.example.domain.ForgotPassword.service;

import com.example.domain.ForgotPassword.entity.VerificationToken;
import com.example.domain.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface ForgotPasswordService extends UserDetailsService {

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

//    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    String validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user, String newPassword);

    boolean checkIfValidOldPassword(User user, String oldPassword);
}
