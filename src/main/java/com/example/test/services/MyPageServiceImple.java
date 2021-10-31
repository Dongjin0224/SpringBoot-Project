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
    public void updateUser(UserVO vo) { myPageDAO.updateUser(vo); }

    @Override
    public void updateDoc(DocVO vo) { }
}
