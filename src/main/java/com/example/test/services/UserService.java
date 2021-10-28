package com.example.test.services;

import com.example.test.user.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /*회원가입*/
    public void memberSignUp(UserVO vo);
}
