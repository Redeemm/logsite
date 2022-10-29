package com.example.domain.ForgotPassword.event.listener;

import com.example.domain.ForgotPassword.event.PasswordCompleteEvent;
import com.example.domain.ForgotPassword.service.ForgotPasswordService;
import com.example.domain.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class PasswordCompleteEventListener implements
        ApplicationListener<PasswordCompleteEvent> {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Override
    public void onApplicationEvent(PasswordCompleteEvent event) {
        //Create the Verification Token for the User with Link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        forgotPasswordService.saveVerificationTokenForUser(token, user);
        //Send Mail to user
        String url =
                event.getApplicationUrl()
                        + "/verifyRegistration?token="
                        + token;

        //sendVerificationEmail()
        log.info("Click the link to verify your account: {}",
                url);
    }
}
