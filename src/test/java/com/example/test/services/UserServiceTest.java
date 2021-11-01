package com.example.test.services;

import com.example.test.model.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testRegister(){
        UserVO user = new UserVO();
        user.setUserId("새로 작성한 글 제목");
        user.setUserPw("새로 작성한 글 내용");
        user.setUserName("user01");
        user.setUserPhoneNum("user01");
        user.setUserEmail("user01");

        userService.memberSignUp(user);
        log.info("------------------------------");
        log.info(user.getUserNo() + "");
        log.info("------------------------------");
    }
}
