package com.example.test.model.user.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserVO {

    private long userNo;
    private String userId;
    private String userPw;
    private String userName;
    private String userPhoneNum;
    private String userEmail;
    private int userStatus;
}
