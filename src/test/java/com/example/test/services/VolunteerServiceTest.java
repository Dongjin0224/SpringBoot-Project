package com.example.test.services;

import com.example.test.beans.vo.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class VolunteerServiceTest {
    @Autowired
    private VolunteerService volunteerService;

    @Test
    public void testGet(){
        log.info(volunteerService.get(1L).toString());
    }

    @Test
    public void testGetList(){
        Criteria criteria = new Criteria();
        volunteerService.getList(criteria).forEach(volunteer -> log.info(volunteer.toString()));
    }
}
