package com.example.test.mappers;

import com.example.test.model.user.vo.DocVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DocMapper {

    /*의사회원가입*/
    public void DocSignUp(DocVO vo);
    /*docNo가져오기*/
    public void insertSelectKey_bno(DocVO vo);

    public DocVO docLogin(DocVO vo);
}
