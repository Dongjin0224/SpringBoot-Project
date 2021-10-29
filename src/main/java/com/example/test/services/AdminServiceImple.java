package com.example.test.services;

import com.example.test.model.admin.dao.AdminDAO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImple implements AdminService {

    private final AdminDAO adminDAO;

    @Override
    public List<UserVO> readUser() {
        return adminDAO.readUser();
    }

    @Override
    public List<DocVO> readDoc() {
        return adminDAO.readDoc();
    }

    @Override
    public int totalUser() {
        return adminDAO.totalUser();
    }

    @Override
    public int totalDoc() {
        return adminDAO.totalDoc();
    }

    @Override
    public boolean updateUser(UserVO user) {
        return adminDAO.updateUser(user);
    }

    @Override
    public boolean updateDoc(DocVO doc) {
        return adminDAO.updateDoc(doc);
    }

}