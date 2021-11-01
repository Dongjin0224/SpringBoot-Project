package com.example.test.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testGet(){
        log.info(appointmentService.get(1L).toString());
    }
}
