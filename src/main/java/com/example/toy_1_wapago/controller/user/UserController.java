package com.example.toy_1_wapago.controller.user;

import com.example.toy_1_wapago.AES;
import com.example.toy_1_wapago.model.user.*;
import com.example.toy_1_wapago.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public LoginResponse loginSubmit(LoginRequest loginRequest, HttpServletRequest httpServletRequest) throws Exception {
        String loginId = loginRequest.getUserId();
        String loginPassword = loginRequest.getUserPassword();

        AES aes = new AES();

        String encodedId = aes.encrypt(loginId);
        String encodedPassword = aes.encrypt(loginPassword);

        loginRequest.setUserId(encodedId);
        loginRequest.setUserPassword(encodedPassword);

        if (!StringUtils.hasText(loginId)) {
            throw new RuntimeException("아이디가 비어있습니다.");
        }

        if (!StringUtils.hasText(loginPassword)) {
            throw new RuntimeException("패스워드가 비어있습니다.");
        }

        LoginResponse loginResponse = userService.login(loginRequest);
        System.out.println("======= 로그인 이후 =======");
        System.out.println(aes.decrypt(loginResponse.getUserId()));

        httpServletRequest.getSession().invalidate();

        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("userId", loginResponse.getUserId());
        session.setMaxInactiveInterval(1800);
        System.out.println("right after Login : " + httpServletRequest.getSession(false));

        return loginResponse;
    }

    @PostMapping("/join")
    public JoinResponse join(JoinRequest joinRequest) throws Exception {
        String joinId = joinRequest.getUserId();
        String joinPassword = joinRequest.getUserPassword();
        String joinName = joinRequest.getUserName();

        AES aes = new AES();

        String encodedId = aes.encrypt(joinId);
        String encodedPassword = aes.encrypt(joinPassword);
        String encodedName = aes.encrypt(joinName);

        System.out.println("encodedId = " + encodedId);
        System.out.println("encodedPassword = " + encodedPassword);
        System.out.println("encodedName = " + encodedName);

        joinRequest.setUserId(encodedId);
        joinRequest.setUserPassword(encodedPassword);
        joinRequest.setUserName(encodedName);

        if (!StringUtils.hasText(joinId))
            throw new RuntimeException("아이디가 비어있습니다.");

        if (!StringUtils.hasText(joinPassword))
            throw new RuntimeException("패스워드가 비어있습니다.");

        if (!StringUtils.hasText(joinName))
            throw new RuntimeException("이름이 비어있습니다.");

        userService.join(joinRequest);
        JoinResponse joinResponse = userService.joinAfter(joinRequest);

        return joinResponse;
    }

    @GetMapping("/getSession")
    public ResponseEntity<String> getSession(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);

        if (session != null) {
            String userId = (String) session.getAttribute("userId");
            return ResponseEntity.ok("user Id: " + userId);
        } else {
            return ResponseEntity.ok("session not found");
        }
    }

}
