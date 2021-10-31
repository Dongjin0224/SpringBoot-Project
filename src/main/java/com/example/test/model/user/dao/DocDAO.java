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

    public void doctorSignUp(DocVO vo){
        docMapper.insertSelectKey_bno(vo);
    }

    public DocVO docLogin(DocVO vo){ return docMapper.docLogin(vo); }

    public void docLogout(HttpSession session) { docMapper.docLogout(session); }
}
