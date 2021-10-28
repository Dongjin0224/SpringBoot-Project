package com.example.test.user.dao;

import com.example.test.mappers.MapMapper;
import com.example.test.mappers.UserMapper;
import com.example.test.user.vo.DocVO;
import com.example.test.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserDAO {

    private final UserMapper userMapper;

    public void memberSignUp(UserVO vo){
        userMapper.memberSignUp(vo);
    }

   /* public UserVO memberLogin*/

}
