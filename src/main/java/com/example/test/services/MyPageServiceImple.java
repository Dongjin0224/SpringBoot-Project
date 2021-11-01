package com.example.test.services;

import com.example.test.model.myPage.dao.MyPageDAO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageServiceImple implements MyPageService {

    private final MyPageDAO myPageDAO;

    @Override
    public UserVO viewUser(Long userNo) { return myPageDAO.viewUser(userNo); }

    @Override
    public DocVO viewDoc(Long docNo) { return myPageDAO.viewDoc(docNo); }

    @Override
    public void updateUser(UserVO userVO) { myPageDAO.updateUser(userVO); }

    @Override
    public void updateDoc(DocVO docVO) { myPageDAO.updateDoc(docVO); }

}
