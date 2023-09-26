package com.example.toy_1_wapago.service.user;

import com.example.toy_1_wapago.model.user.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public interface UserService {
    void join(JoinRequest joinRequest);
    JoinResponse joinAfter(JoinRequest joinRequest);
    LoginResponse login(LoginRequest loginRequest);
}
