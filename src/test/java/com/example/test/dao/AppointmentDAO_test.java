package com.example.test.dao;

import com.example.test.appointment.dao.AppointmentDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AppointmentDAO_test {

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Test
    public void testGet(){ log.info(appointmentDAO.get(1L).toString()); }


}
