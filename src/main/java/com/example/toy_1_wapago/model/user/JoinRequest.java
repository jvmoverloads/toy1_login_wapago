package com.example.toy_1_wapago.model.user;

import lombok.Data;

@Data
public class JoinRequest {
    private String userId;
    private String userPassword;
    private String userName;
}
