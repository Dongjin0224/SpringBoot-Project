package com.example.test.services;

import com.example.test.model.user.vo.DocAttachFileVO;
import com.example.test.model.user.vo.DocHosAttachFileVO;
import com.example.test.model.user.vo.DocVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class DocServiceTest {


    @Autowired
    private DocService docService;

    @Test
    public void DocSignUpTest(){
        DocVO doc = new DocVO();
        DocAttachFileVO avo = new DocAttachFileVO();
        DocHosAttachFileVO hvo = new DocHosAttachFileVO();

        doc.setDocId("새로 작성하는 글3");
        doc.setDocPw("새로 작성하는 내용3");
        doc.setDocName("전진주");
        doc.setDocMajor("hds1204");
        doc.setDocHospitalName("hds1204");
        doc.setDocLat(1.1);
        doc.setDocLng(2.3);
        doc.setDocAddress("hds1204");
        doc.setDocHosPhone("1234");
        doc.setDocEmail("hds1204");
        doc.setDocPhoneNum("hds1204");
        doc.setDocPic("hds1204");

        avo.setDocNo(6L);
        avo.setFileName("jjjja");
        avo.setImage(true);
        avo.setUploadPath("C:/upload");
        avo.setUuid("dddd_");

        hvo.setDocNo(6L);
        hvo.setHosFileName("jjj");
        hvo.setHosImage(true);
        hvo.setHosUploadPath("C:\\hosupload");
        hvo.setHosUuid("aaaa_");

        docService.DocSignUp(doc);
        log.info("------------------------------");
        log.info(doc.getDocNo() + "");
        log.info("------------------------------");
    }

    @Test
    public void getAttachListTest(){
        docService.getAttachList(6L).forEach(attach ->{
            attach.getDocNo();
            attach.getFileName();
            attach.getUploadPath();
            attach.getUuid();
        });
    }

    @Test
    public void getHosAttachListTest(){
        docService.getHosAttachList(6L).forEach(attach ->{
            attach.getDocNo();
            attach.getHosFileName();
            attach.getHosUploadPath();
            attach.getHosUuid();
        });
    }

   /* @Test
    public void checkId(){
        log.info("--------------------");
        log.info(docService.checkId("재훈"));
        log.info("--------------------");
    }*/


}
