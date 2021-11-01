package com.example.test.dao;

import com.example.test.model.user.dao.DocAttachFileDAO;
import com.example.test.model.user.vo.DocAttachFileVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class DocAttachFileDAOTest {

    @Autowired
    DocAttachFileDAO docAttachFileDAO;

    @Test
    public void insertTest(){
        DocAttachFileVO docAttachFileVO = new DocAttachFileVO();
        docAttachFileVO.setDocNo(6L);
        docAttachFileVO.setFileName("jjjja");
        docAttachFileVO.setImage(true);
        docAttachFileVO.setUploadPath("C:/upload");
        docAttachFileVO.setUuid("j_j_jJ_");
        log.info("-----------------");
        docAttachFileDAO.insert(docAttachFileVO);
        log.info("-----------------");
    }
}
