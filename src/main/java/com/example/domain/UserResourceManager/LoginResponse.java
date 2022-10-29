package com.example.domain.UserResourceManager;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginResponse implements Serializable {

    private final String accessToken;
    private final String refreshToken;
    private final UserDetails userDetails;

}
