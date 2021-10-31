package com.example.test.services;

import com.example.test.model.user.dao.UserDAO;
import com.example.test.model.user.vo.UserVO;
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

    @Override
    public UserVO userLogin(UserVO vo) {
        return userDAO.userLogin(vo);
    }

    @Override
    public int checkId(UserVO vo) {
        int result =userDAO.checkId(vo);
        return result;
    }


}
