package com.example.toy_1_wapago.model.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class JoinRequest {
    @NotBlank(message = "아이디를 입력하세요")
    private String userId;

    @NotBlank(message = "비밀번호를 입력하세요")
    private String userPassword;

    @NotBlank(message = "이름을 입력하세요")
    private String userName;
}
