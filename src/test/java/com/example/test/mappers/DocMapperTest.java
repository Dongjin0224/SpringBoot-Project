package com.example.test.mappers;

import com.example.test.model.user.vo.DocVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class DocMapperTest {

    @Autowired
    private DocMapper mapper;

    @Test
    public void testDoctorSignUp(){
        DocVO doc = new DocVO();
        doc.setDocId("새로 작성하는 글3");
        doc.setDocPw("새로 작성하는 내용3");
        doc.setDocName("전진주");
        doc.setDocMajor("hds1204");
        doc.setDocHosPhone("1234");
        doc.setDocHospitalName("hds1204");
        doc.setDocLat(1.1);
        doc.setDocLng(2.3);
        doc.setDocAddress("hds1204");
        doc.setDocEmail("hds1204");
        doc.setDocPhoneNum("hds1204");
        mapper.insertSelectKey_bno(doc);
    }

//    @Test
//    public void getLikeTest(){
//        mapper.getlike()
//    }
}
