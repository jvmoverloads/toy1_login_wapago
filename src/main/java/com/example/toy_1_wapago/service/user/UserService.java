package com.example.toy_1_wapago.service.user;

import com.example.toy_1_wapago.model.user.*;
import org.springframework.data.relational.core.sql.Join;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void join(JoinRequest joinRequest);
    JoinResponse joinAfter(JoinRequest joinRequest);
    LoginResponse login(LoginRequest loginRequest);
}
