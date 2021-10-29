package com.example.test.mappers;

import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    public List<UserVO> readUser();
    public List<DocVO> readDoc();
    public int totalUser();
    public int totalDoc();
    public int updateUser(UserVO user);
    public int updateDoc(DocVO doc);

}
