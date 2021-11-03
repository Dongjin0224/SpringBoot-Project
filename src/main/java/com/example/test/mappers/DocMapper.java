package com.example.test.mappers;

import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import javax.servlet.http.HttpSession;

@Mapper
public interface DocMapper {

    /*의사회원가입*/
    public void DocSignUp(DocVO vo);
    /*docNo가져오기*/
    public void insertSelectKey_bno(DocVO vo);

    public DocVO docLogin(DocVO vo);

    public int checkId(DocVO vo);

    public void docLogout(HttpSession session);

    public DocVO findId(DocVO vo);

    public DocVO findPw(DocVO vo);

    public void updatePassword(DocVO vo);
}
