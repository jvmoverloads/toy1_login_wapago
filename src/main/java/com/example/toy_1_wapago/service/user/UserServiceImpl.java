package com.example.toy_1_wapago.service.user;

import com.example.toy_1_wapago.mapper.user.UserMapper;
import com.example.toy_1_wapago.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        LoginResponse loginResponse = userMapper.login(loginRequest);

        if (loginResponse == null) {
            throw new RuntimeException("loginResponse is null");
        }

        return loginResponse;
    }

    @Override
    public void join(JoinRequest joinRequest) {
        userMapper.join(joinRequest);
    }

    @Override
    public JoinResponse joinAfter(JoinRequest joinRequest) {
        JoinResponse joinResponse = userMapper.joinAfter(joinRequest);

        return joinResponse;
    }
}
