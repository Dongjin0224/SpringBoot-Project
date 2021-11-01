package com.example.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class DocControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp(){
//        가짜 MVC에 WebApplicationContext를 전달한 후 환경을 생성한다.
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testDoctorSignUp() throws Exception {
        String docNo = mockMvc.perform(
                MockMvcRequestBuilders.post("/user/doctorSignUp")
                        .param("docId", "jjj")
                        .param("docPw", "1111")
                        .param("docName", "hds")
                        .param("docMajor", "hds")
                        .param("docHospitalName", "hds")
                        .param("docLat", "1")
                        .param("docLng", "2")
                        .param("docAddress", "hds")
                        .param("docEmail", "hds")
                        .param("docPhoneNum", "hds1234")
                        .param("docHosPhone","1234")
        /*
        docAttachFileVO.setFileName("jjjja");
        docAttachFileVO.setImage(true);
        docAttachFileVO.setUploadPath("C:/upload");
        docAttachFileVO.setUuid("j_j_jJ_");*/

        ).andReturn().getFlashMap().toString();

        log.info(docNo);
    }
}
