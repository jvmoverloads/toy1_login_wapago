package com.example.toy_1_wapago.service.user;

import com.example.toy_1_wapago.mapper.user.UserMapper;
import com.example.toy_1_wapago.model.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVO login(UserVO userInfo) {
        UserVO user = null;

        try {
            user = userMapper.login(userInfo);

            if(user == null) {
                System.out.println("아이디 또는 비밀번호가 맞지 않습니다.");
            }

            System.out.println("user.getUserId() ===> " + user.getUserId());
            System.out.println("user.getUserPassword() ===> " + user.getUserPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void join(Map<String, String> joinInfo) {
        userMapper.join(joinInfo);
    }
}
