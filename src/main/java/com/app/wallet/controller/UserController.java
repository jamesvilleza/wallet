package com.app.wallet.controller;

import com.app.wallet.dto.SignInDto;
import com.app.wallet.dto.SignInResponseDto;
import com.app.wallet.dto.SignUpResponseDto;
import com.app.wallet.dto.SignupDto;
import com.app.wallet.exceptions.AuthenticationFailException;
import com.app.wallet.exceptions.CustomException;
import com.app.wallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("signup")
    public SignUpResponseDto Signup(@RequestBody SignupDto signupDto) throws CustomException {
        return userService.signUp(signupDto);
    }

    @PostMapping("/signIn")
    public SignInResponseDto Signup(@RequestBody SignInDto signInDto) throws CustomException {
        try {
            return userService.signIn(signInDto);
        } catch (AuthenticationFailException e) {
            throw new RuntimeException(e);
        }
    }

}
