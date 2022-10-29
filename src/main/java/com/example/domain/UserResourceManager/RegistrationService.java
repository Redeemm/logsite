package com.example.domain.UserResourceManager;

import com.example.domain.exception.HttpHandler.NotAcceptableException;
import com.example.domain.security.config.util.JwtUtil;
import com.example.domain.user.User;
import com.example.domain.user.UserRole;
import com.example.domain.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    private AuthenticationManager authenticationManager;

    private JwtUtil jwtTokenUtil;


    public ResponseEntity<?> register(RegistrationRequest request) throws NotAcceptableException {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new NotAcceptableException("Email not valid");
        }

          userService.signUpUser(
                new User(
                        request.getUserName(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.USER
                )
        );
        final UserDetails userDetails = userService
                .loadUserByUsername(request.getEmail());

        final String refreshToken = jwtTokenUtil.generateToken(userDetails);


        return new ResponseEntity<>(new LoginResponse(refreshToken, refreshToken, userDetails), HttpStatus.CREATED);
    }


    @Transactional
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws NotAcceptableException {


        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));
        } catch (Exception e) {
            throw new NotAcceptableException("The email or password you provided is incorrect");
        }

        userService.enableUser(loginRequest.email());


        final UserDetails userDetails = userService
                .loadUserByUsername(loginRequest.email());

        final String refreshToken = jwtTokenUtil.generateToken(userDetails);

        return new ResponseEntity<>(new LoginResponse(refreshToken, refreshToken, userDetails), HttpStatus.ACCEPTED);
    }
}
