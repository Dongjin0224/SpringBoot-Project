package com.example.test.dao;

import com.example.test.model.beans.vo.Criteria;
import com.example.test.model.volunteer.dao.VolunteerDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class VolunteerDAO_test {
    @Autowired
    private VolunteerDAO volunteerDAO;

    @Test
    public void getListTest() {
        /*boardDAO.getList(criteria).forEach(board -> log.info(board.toString()));*/
        log.info("----------------------------");
        Criteria criteria = new Criteria();
        volunteerDAO.getList(criteria).forEach(volunteer -> {
            log.info(String.valueOf(volunteer.getVolunteerBoardNo()));
            log.info(volunteer.getVolunteerBoardTitle());
            log.info(volunteer.getVolunteerBoardContent());
            log.info(volunteer.getVolunteerDocMajor());
            log.info(String.valueOf(volunteer.getVolunteers()));
            log.info(volunteer.getVolunteerArea());
            log.info(volunteer.getVolunteerLocation());
            log.info(volunteer.getVolunteerPeriod());
            log.info(volunteer.getVolunteerRecruitmentPeriod());
        });
        log.info("----------------------------");
    }

    @Test
    public void getTest() {
        log.info(volunteerDAO.get(1L).toString());

    }
}
