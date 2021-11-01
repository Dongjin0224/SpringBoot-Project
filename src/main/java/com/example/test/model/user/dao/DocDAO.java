package com.example.test.model.user.dao;

import com.example.test.mappers.DocMapper;
import com.example.test.model.user.vo.DocVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;

@Repository
@Slf4j
@RequiredArgsConstructor
public class DocDAO {

    private final DocMapper docMapper;
    /*의사 회원가입*/
    public void doctorSignUp(DocVO vo){
        docMapper.insertSelectKey_bno(vo);
    }

    /*의사 로그인*/
    public DocVO docLogin(DocVO vo){ return docMapper.docLogin(vo); }

    /*회원중복체크*/
    public int checkId(DocVO vo){
        return docMapper.checkId(vo);
    }

    public void docLogout(HttpSession session) { docMapper.docLogout(session); }
}
