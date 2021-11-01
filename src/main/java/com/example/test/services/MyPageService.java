package com.example.test.services;

import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public interface MyPageService {

    public UserVO viewUser(Long userNo);
    public DocVO viewDoc(Long docNo);
    public void updateUser(UserVO userVO);
    public void updateDoc(DocVO docVO);

}
