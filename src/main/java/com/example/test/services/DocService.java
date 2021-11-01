package com.example.test.services;

import com.example.test.model.user.vo.DocAttachFileVO;
import com.example.test.model.user.vo.DocHosAttachFileVO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import com.google.inject.spi.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface DocService {

    /*의사회원가입*/
    public void DocSignUp(DocVO vo);

    public List<DocAttachFileVO> getAttachList(Long docNo);

    public DocVO docLogin(DocVO vo);

    public List<DocHosAttachFileVO> getHosAttachList(Long docNo);

    public int checkId(DocVO vo);

}
