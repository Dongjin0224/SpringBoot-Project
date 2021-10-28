package com.example.test.mappers;

import com.example.test.beans.vo.Criteria;
import com.example.test.volunteer.vo.ApplicantsVO;
import com.example.test.volunteer.vo.VolunteerBoardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
@Slf4j
public class VolunteerMapperTest {
    @Autowired
    private VolunteerMapper volunteerMapper;

    @Test
    public void getListTest(){
        Criteria criteria = new Criteria();
        criteria.setPageNum(2);
        criteria.setAmount(10);
        ArrayList<VolunteerBoardVO> vo = (ArrayList<VolunteerBoardVO>) volunteerMapper.getList(criteria);
        volunteerMapper.getList(criteria).forEach(volunteer -> {
            log.info("----------------------------");
            log.info(volunteer.getVolunteerBoardTitle());
            log.info(volunteer.getVolunteerBoardContent());
            log.info(volunteer.getVolunteerDocMajor());
            log.info("----------------------------");
        });
    }


    @Test
    public void getTest(){
        log.info(volunteerMapper.get(1L).toString());
    }

    @Test
    public void insertTest(){
        ApplicantsVO app = new ApplicantsVO();
        app.setDocNo(1L);
        app.setDocName("민석홍");
        app.setDocPhoneNum("01011112222");
        app.setApplicants(4);
        app.setApplicationContent("신청합니다.");
        volunteerMapper.insert(app);
    }
}
