package com.example.toy_1_wapago.controller.user;

import com.example.toy_1_wapago.model.user.*;
import com.example.toy_1_wapago.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private static final String LOGIN_MEMBER = "loginMember";

    @Autowired
    UserService userService;

    @PostMapping("/join")
    public JoinResponse join(@Valid JoinRequest joinRequest) throws Exception {
        userService.join(joinRequest);

        JoinResponse joinResponse = userService.joinAfter(joinRequest);

        return joinResponse;
    }

    @PostMapping("/login")
    public LoginResponse login(HttpServletRequest httpServletRequest, HttpSession httpSession, @Valid LoginRequest loginRequest) throws Exception {
        LoginResponse loginResponse = userService.login(loginRequest);
        String loginId = loginResponse.getUserId();

        httpSession.setAttribute(LOGIN_MEMBER, loginId);    // 세션에 사용자 정보 저장
        log.info(httpServletRequest.getHeader("Cookie"));

        return loginResponse;
    }

    @PostMapping("/logout")
    public void logout(HttpSession httpSession) {
        httpSession.invalidate();
    }

}
