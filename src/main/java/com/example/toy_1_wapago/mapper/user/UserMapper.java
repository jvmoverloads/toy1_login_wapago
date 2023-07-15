package com.example.toy_1_wapago.mapper.user;

import com.example.toy_1_wapago.model.user.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface UserMapper {
    UserVO login(@Param("userInfo") UserVO userInfo);
    void join(@Param("joinInfo") Map<String, String> joinInfo);
}
