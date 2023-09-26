package com.example.toy_1_wapago.controller.movie;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MovieSearchingController {

    @RequestMapping("/hello")
    public String hello() {
        return "/hello";
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/board/list")
    public String board() {
        return "/board/list";
    }
}
