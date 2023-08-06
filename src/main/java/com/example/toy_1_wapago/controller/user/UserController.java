package com.example.toy_1_wapago.controller.user;

import com.example.toy_1_wapago.model.user.*;
import com.example.toy_1_wapago.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    // TODO : 세션 생성시 컨트롤러에서 HttpServletRequest로 생성하지 말고 파라미터로 받은 후 서비스에서 구현할 것.
    // 1. 유연성과 테스트 용이성
    //    HttpSession을 파라미터로 받는 방식은 의존성 주입(Dependency Injection)을 사용하여 세션을 컨트롤러에 주입할 수 있습니다.
    //    이를 통해 세션 관리를 더 유연하게 구현할 수 있으며, 특히 테스트 시에 가짜 세션(Mock 세션)을 주입하여 테스트하기 쉽습니다.
    //    반면 HttpServletRequest를 이용하면, 테스트하기 어려운 환경이 될 수 있습니다.
    // 2. 컨트롤러의 역할 명확화
    //    컨트롤러가 HttpSession을 직접 다루지 않고 파라미터로 받아서 전달하면, 컨트롤러의 역할이 좀 더 명확해집니다.
    //    컨트롤러는 단순히 요청을 처리하고 응답을 반환하는 역할에만 집중할 수 있으며, 세션 관리 등의 복잡한 작업은 서비스 레이어로 분리하여 처리할 수 있습니다.
    // 3. 의존성 제거
    //    HttpServletRequest를 이용하는 방식은 서블릿 API에 종속적이므로, 이를 컨트롤러에 직접 의존하는 것은 바람직하지 않습니다.
    //    또한, 서블릿 API에 의존하지 않으면 서블릿을 다른 컨트롤러 기술로 쉽게 교체할 수 있습니다.
    @PostMapping("/login")
    public LoginResponse loginSubmit(HttpServletRequest httpServletRequest, HttpSession httpSession, LoginRequest loginRequest) throws Exception {
        return userService.login(httpServletRequest, httpSession, loginRequest);
    }

    @PostMapping("/join")
    public JoinResponse join(JoinRequest joinRequest) throws Exception {
        userService.join(joinRequest);

        JoinResponse joinResponse = userService.joinAfter(joinRequest);

        return joinResponse;
    }
}
