package com.example.toy_1_wapago.service.user;

import com.example.toy_1_wapago.mapper.user.UserMapper;
import com.example.toy_1_wapago.model.user.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@Log4j2
public class UserServiceImpl implements UserService {
    // HttpSession에 데이터를 보관하고 조회할 때, 같은 이름이 중복되어 사용되므로 아래와 같이 상수를 정의하면 좋다.
    public static final String LOGIN_MEMBER = "loginMember";

    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginResponse login(HttpServletRequest httpServletRequest, HttpSession httpSession, LoginRequest loginRequest) {
        String loginId = loginRequest.getUserId();
        String loginPassword = loginRequest.getUserPassword();

        log.info(loginId);

        if (!StringUtils.hasText(loginId)) {
            throw new RuntimeException("아이디가 비어있습니다.");
        }
        if (!StringUtils.hasText(loginPassword)) {
            throw new RuntimeException("패스워드가 비어있습니다.");
        }

        // 세션 : 서버 측에서 유지되는 상태정보, 클라이언트의 요청을 서버가 처리하는 동안에만 유지.
        // 쿠키 : 클라이언트 측에 저장되는 작은 데이터조각. 서버가 클라이언트에게 전달, 클라이언트가 이를 저장하고 필요에 따라 요청과 함께 다시 서버에 전송.
        //       쿠키는 클라이언트의 웹브라우저에 저장되기 때문에, 만료기간이 지나거나 클라이언트가 쿠키를 삭제할 때까지 지속.
        // 관계 : 쿠키를 사용하여 세션ID를 클라이언트에 저장하고, 이를 통해 세션을 식별
        httpSession.setAttribute(LOGIN_MEMBER, loginId);    // 세션에 사용자 정보 저장
        log.info(httpServletRequest.getHeader("Cookie"));

        LoginResponse loginResponse = userMapper.login(loginRequest);

        if (loginResponse == null) {
            throw new RuntimeException("loginResponse is null");
        }

        return loginResponse;
    }

    @Override
    public void join(JoinRequest joinRequest) {
        String joinId = joinRequest.getUserId();
        String joinPassword = joinRequest.getUserPassword();
        String joinName = joinRequest.getUserName();

        if (!StringUtils.hasText(joinId))
            throw new RuntimeException("아이디가 비어있습니다.");

        if (!StringUtils.hasText(joinPassword))
            throw new RuntimeException("패스워드가 비어있습니다.");

        if (!StringUtils.hasText(joinName))
            throw new RuntimeException("이름이 비어있습니다.");

        userMapper.join(joinRequest);
    }

    @Override
    public JoinResponse joinAfter(JoinRequest joinRequest) {
        JoinResponse joinResponse = userMapper.joinAfter(joinRequest);

        return joinResponse;
    }
}
