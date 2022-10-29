package com.example.domain.UserResourceManager;

import com.example.domain.ForgotPassword.model.PasswordModel;
import com.example.domain.ForgotPassword.service.ForgotPasswordService;
import com.example.domain.exception.ApiRequestException;
import com.example.domain.exception.HttpHandler.NotAcceptableException;
import com.example.domain.user.User;
import com.example.domain.user.UserRepository;
import com.example.domain.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@CrossOrigin(origins = "http://localhost:8080/")
@RestController
@RequestMapping(path = "api/users/auth")
@AllArgsConstructor
@Slf4j
public class RegistrationController {

    private final RegistrationService registrationService;

    private final ForgotPasswordService forgotPasswordService;

    private final UserService userService;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("reg")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest) throws NotAcceptableException {
        return  registrationService.register(registrationRequest);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        return registrationService.createAuthenticationToken(loginRequest);
    }


}
