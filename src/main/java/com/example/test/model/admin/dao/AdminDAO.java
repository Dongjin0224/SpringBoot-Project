package com.example.test.model.admin.dao;

import com.example.test.mappers.AdminMapper;
import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class AdminDAO {

    public final AdminMapper adminMapper;

    public List<UserVO> readUser(){ return adminMapper.readUser(); }
    public List<DocVO> readDoc(){ return adminMapper.readDoc(); }
    public int totalUser(){ return adminMapper.totalUser(); }
    public int totalDoc(){ return adminMapper.totalDoc(); }
    public boolean updateUser(UserVO user){ return adminMapper.updateUser(user) == 1; }
    public boolean updateDoc(DocVO doc){ return adminMapper.updateDoc(doc) == 1; }
}
