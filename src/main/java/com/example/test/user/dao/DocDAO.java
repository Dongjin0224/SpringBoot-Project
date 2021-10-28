package com.example.test.user.dao;

import com.example.test.mappers.DocMapper;
import com.example.test.mappers.UserMapper;
import com.example.test.user.vo.DocVO;
import com.example.test.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.print.Doc;

@Repository
@Slf4j
@RequiredArgsConstructor
public class DocDAO {

    private final DocMapper docMapper;

    public void doctorSignUp(DocVO vo){
        docMapper.insertSelectKey_bno(vo);
    }


}
