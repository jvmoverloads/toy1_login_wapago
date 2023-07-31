package com.example.toy_1_wapago.model.user;

import lombok.Data;

@Data
public class LoginRequest {
    private String userId;
    private String userPassword;
}
