package com.example.test.services;

import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    public List<UserVO> readUser();
    public List<DocVO> readDoc();
    public int totalUser();
    public int totalDoc();
    public boolean updateUser(UserVO user);
    public boolean updateDoc(DocVO doc);
}
