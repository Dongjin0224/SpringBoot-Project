package com.example.test.mappers;

import com.example.test.model.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    public void testInsert(){
        UserVO user = new UserVO();
        user.setUserId("새로 작성한 글 제목");
        user.setUserPw("새로 작성한 글 내용");
        user.setUserName("user01");
        user.setUserPhoneNum("user01");
        user.setUserEmail("user01");
        mapper.memberSignUp(user);
    }

}
