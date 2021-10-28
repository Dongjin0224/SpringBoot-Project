package com.example.test.dao;

import com.example.test.user.dao.UserDAO;
import com.example.test.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void testRegister(){
        UserVO user = new UserVO();
        user.setUserId("새로 작성하는 글3");
        user.setUserPw("새로 작성하는 내용3");
        user.setUserName("hds1204");
        user.setUserName("hds1204");
        user.setUserPhoneNum("hds1204");
        user.setUserEmail("hds1204");

        userDAO.memberSignUp(user);
        log.info("-------------------------------");
        log.info(user.getUserNo() + "");
        log.info("-------------------------------");
    }
}
