package com.example.test.services;

import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MyPageServiceTest {
    @Autowired
    private MyPageService myPageService;

    @Test
    public void viewUser() { log.info(myPageService.viewUser(1L).toString());}

    @Test
    public void viewDoc() { log.info(myPageService.viewDoc(1L).toString());}

    @Test
    public void updateUser(){
            UserVO vo = new UserVO();
            vo.setUserPw("1111");
            vo.setUserPhoneNum("01011111111");
            vo.setUserEmail("abc@gmail.com");
            log.info("UPDATE : " + myPageService.viewUser(1L).toString());
        }

    @Test
    public void updateDoc(){
            DocVO vo = new DocVO();
            vo.setDocPw("1234");
            log.info("UPDATE : " + myPageService.viewDoc(1L).toString());
        }
    }
