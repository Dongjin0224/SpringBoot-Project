package com.example.test.mappers;

import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {

    public UserVO viewUser(Long userNo);
    public DocVO viewDoc(Long docNo);

    public void updateUser(UserVO userVO);
    public void updateDoc(DocVO docVO);

    public void deleteUser(Long userNo);
    public void deleteDoc(Long docNo);

}
