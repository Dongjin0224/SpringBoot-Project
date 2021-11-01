package com.example.test.dao;

import com.example.test.model.myPage.dao.MyPageDAO;
import com.example.test.model.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MyPageDAO_test {
    @Autowired
    private MyPageDAO myPageDAO;

    @Test
    public void viewUser(){ log.info(myPageDAO.viewUser(1L).toString()); }

    @Test
    public void viewDoc(){ log.info(myPageDAO.viewDoc(1L).toString()); }

    @Test
    public void updateUserTest(){
        if(myPageDAO.viewUser(1L) == null){
            log.info("***********NO SUCH USER***********");
        }else{
            UserVO vo = new UserVO();
            vo.setUserPw("shmin777");
            vo.setUserEmail("abc@gmail.com");
            vo.setUserPhoneNum("01011111111");
        }
    }

    @Test
    public void updateDocTest(){
        if(myPageDAO.viewDoc(1L) == null){
            log.info("***********NO SUCH DOCTOR***********");
        }else{
            UserVO vo = new UserVO();
            vo.setUserPw("shmin777");
            vo.setUserEmail("abc@gmail.com");
            vo.setUserPhoneNum("01011111111");
        }
    }
}
