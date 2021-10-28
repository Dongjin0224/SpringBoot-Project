package com.example.test.dao;

import com.example.test.user.dao.DocDAO;
import com.example.test.user.dao.UserDAO;
import com.example.test.user.vo.DocVO;
import com.example.test.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class DocDAOTest {

    @Autowired
    private DocDAO docDAO;

    @Test
    public void testDoctorSignUp(){
        DocVO doc = new DocVO();
        doc.setDocId("새로 작성하는 글3");
        doc.setDocPw("새로 작성하는 내용3");
        doc.setDocName("전진주");
        doc.setDocMajor("hds1204");
        doc.setDocHospitalName("hds1204");
        doc.setDocLat(1.1);
        doc.setDocLng(2.3);
        doc.setDocAddress("hds1204");
        doc.setDocEmail("hds1204");
        doc.setDocPhoneNum("hds1204");
        doc.setDocPic("hds1204");

        docDAO.doctorSignUp(doc);
        log.info("-------------------------------");
        log.info(doc.getDocNo()+ "");
        log.info("-------------------------------");
    }


}
