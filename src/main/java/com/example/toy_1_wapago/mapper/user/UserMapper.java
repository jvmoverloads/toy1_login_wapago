package com.example.toy_1_wapago.mapper.user;

import com.example.toy_1_wapago.model.user.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    void join(@Param("joinInfo") JoinRequest joinRequest);
    JoinResponse joinAfter(@Param("joinInfo") JoinRequest joinRequest);
    LoginResponse login(@Param("userInfo") LoginRequest loginRequest);
}
