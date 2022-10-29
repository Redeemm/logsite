package com.example.domain.ForgotPassword.event;

import com.example.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class PasswordCompleteEvent extends ApplicationEvent {

    private final User user;
    private final String applicationUrl;

    public PasswordCompleteEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;

    }
}
