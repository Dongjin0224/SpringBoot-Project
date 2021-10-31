package com.example.test.model.user.dao;

import com.example.test.mappers.UserMapper;
import com.example.test.model.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserDAO {

    private final UserMapper userMapper;

    public void memberSignUp(UserVO vo){
        userMapper.memberSignUp(vo);
    }

   public UserVO userLogin(UserVO vo){return userMapper.userLogin(vo);}

   public boolean checkId(String userId){
        return userMapper.checkId(userId) == 0;
   }

   public void userLogout(HttpSession session) { userMapper.userLogout(session); };
}
