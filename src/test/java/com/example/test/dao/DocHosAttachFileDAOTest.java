package com.example.test.dao;


import com.example.test.model.user.dao.DocHosAttachFileDAO;
import com.example.test.model.user.vo.DocHosAttachFileVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class DocHosAttachFileDAOTest {

    @Autowired
    DocHosAttachFileDAO docHosAttachFileDAO;

    @Test
    public void insertTest(){
        DocHosAttachFileVO docHosAttachFileVO = new DocHosAttachFileVO();
        docHosAttachFileVO.setDocNo(6L);
        docHosAttachFileVO.setHosFileName("jjj");
        docHosAttachFileVO.setHosImage(true);
        docHosAttachFileVO.setHosUploadPath("C:\\hosupload");
        docHosAttachFileVO.setHosUuid("j_j_hj_");
        log.info("---------------------------");
        docHosAttachFileDAO.insert(docHosAttachFileVO);
        log.info("---------------------------");
    }
}
