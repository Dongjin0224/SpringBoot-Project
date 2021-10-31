package com.example.test.services;

import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public interface MyPageService {

    public void updateUser(UserVO vo);
    public void updateDoc(DocVO vo);

}
