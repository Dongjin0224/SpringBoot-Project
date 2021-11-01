package com.example.test.mappers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AppointmentMapperTest {

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Test
    public void testRead(){ log.info(appointmentMapper.read(1L).toString());}
}
