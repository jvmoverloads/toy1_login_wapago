package com.example.toy_1_wapago.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/login")
    public String loginGET(String error, String logout) {
        log.info("login get.............");
        log.info("logout : " + logout);

        return "/member/login";
    }
}
