package com.example.toy_1_wapago.controller.user;

import com.example.toy_1_wapago.model.user.UserVO;
import com.example.toy_1_wapago.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/loginPage")
    public String login() {
        return "member/login";
    }

    @PostMapping("/login")
    public String loginSubmit(UserVO userInfo) {
        UserVO user = userService.login(userInfo);

        if(user != null) {
            System.out.println("로그인성공");
            return "home";
        }

        return null;
    }

    @GetMapping("/joinPage")
    public String joinPage() {
        return "member/join";
    }

    @PostMapping("/join")
    public String join(@RequestParam Map<String, String> joinInfo) {
        String joinId = joinInfo.get("userId");
        String joinPassword = joinInfo.get("userPassword");

        System.out.println("joinId = " + joinId);
        System.out.println("joinPassword = " + joinPassword);

        userService.join(joinInfo);

        return null;
    }
}
