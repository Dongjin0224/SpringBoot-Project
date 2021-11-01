package com.example.test.mappers;

import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MyPageMapperTest {

    @Autowired
    private MyPageMapper myPageMapper;

    @Test
    public void viewUser(){ log.info(myPageMapper.viewUser(1L).toString()); }

    @Test
    public void viewDoc(){ log.info(myPageMapper.viewDoc(1L).toString()); }


    @Test
    public void updateUserTest(){
        if(myPageMapper.viewUser(1L) == null){
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
        if(myPageMapper.viewDoc(1L) == null){
            log.info("***********NO SUCH DOCTOR***********");
        }else{
            UserVO vo = new UserVO();
            vo.setUserPw("shmin777");
            vo.setUserEmail("abc@gmail.com");
            vo.setUserPhoneNum("01011111111");
        }
    }
}
