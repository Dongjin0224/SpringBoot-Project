package com.example.test.mappers;

import com.example.test.user.vo.DocVO;
import com.example.test.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DocMapper {

    /*의사회원가입*/
    public void DocSignUp(DocVO vo);
    /*docNo가져오기*/
    public void insertSelectKey_bno(DocVO vo);
}
