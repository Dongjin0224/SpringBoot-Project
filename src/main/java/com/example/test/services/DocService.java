package com.example.test.services;


import com.example.test.model.user.vo.DocAttachFileVO;
import com.example.test.model.user.vo.DocVO;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface DocService {

    /*의사회원가입*/
    public void DocSignUp(DocVO vo);
    public List<DocAttachFileVO> getAttachList(Long docNo);

}
