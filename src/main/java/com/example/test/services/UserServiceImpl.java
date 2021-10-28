package com.example.test.services;

import com.example.test.mappers.UserMapper;
import com.example.test.user.dao.MapDAO;
import com.example.test.user.dao.UserDAO;
import com.example.test.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

     private final UserDAO userDAO;

    @Override
    public void memberSignUp(UserVO vo) {
          userDAO.memberSignUp(vo);
    }

}
