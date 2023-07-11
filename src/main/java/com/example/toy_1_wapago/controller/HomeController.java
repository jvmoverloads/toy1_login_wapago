package com.example.toy_1_wapago.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/login")
    public String login() {
        System.out.println("===== 로그인 =====");

        return "login";
    }
}
