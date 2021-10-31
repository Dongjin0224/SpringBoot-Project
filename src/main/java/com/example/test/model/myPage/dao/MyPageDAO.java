package com.example.test.model.myPage.dao;

import com.example.test.mappers.MyPageMapper;
import com.example.test.model.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MyPageDAO {

    private final MyPageMapper myPageMapper;

    public void updateUser(UserVO vo){ myPageMapper.updateUser(vo); }
//    public void updateDoc(DocVO vo){}
}
