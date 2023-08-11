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
    public static final String LOGIN_MEMBER = "loginMember";

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public LoginResponse login(HttpServletRequest httpServletRequest, HttpSession httpSession, @Valid LoginRequest loginRequest) throws Exception {
        // TODO : controller에서 받는 LoginResponse를 통해서 session에 정보를 저장하기(service layer로 http관련객체 넘기지 말 것)
        LoginResponse loginResponse = userService.login(loginRequest);
        String loginId = loginResponse.getUserId();

        /*
            세션 : 서버 측에서 유지되는 상태정보, 클라이언트의 요청을 서버가 처리하는 동안에만 유지.
            쿠키 : 클라이언트 측에 저장되는 작은 데이터조각. 서버가 클라이언트에게 전달, 클라이언트가 이를 저장하고 필요에 따라 요청과 함께 다시 서버에 전송.
                  쿠키는 클라이언트의 웹브라우저에 저장되기 때문에, 만료기간이 지나거나 클라이언트가 쿠키를 삭제할 때까지 지속.
            관계 : 쿠키를 사용하여 세션ID를 클라이언트에 저장하고, 이를 통해 세션을 식별
        */

        httpSession.setAttribute(LOGIN_MEMBER, loginId);    // 세션에 사용자 정보 저장
        log.info(httpServletRequest.getHeader("Cookie"));

        return loginResponse;
    }

    @PostMapping("/join")
    // TODO : @Valid적용하기
    public JoinResponse join(@Valid JoinRequest joinRequest) throws Exception {
        userService.join(joinRequest);

        JoinResponse joinResponse = userService.joinAfter(joinRequest);

        return joinResponse;
    }
}
