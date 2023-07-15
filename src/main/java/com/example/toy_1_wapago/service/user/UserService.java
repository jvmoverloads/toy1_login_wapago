package com.example.toy_1_wapago.service.user;

import com.example.toy_1_wapago.model.user.UserVO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {
    UserVO login(UserVO userInfo);
    void join(Map<String, String> joinInfo);
}
