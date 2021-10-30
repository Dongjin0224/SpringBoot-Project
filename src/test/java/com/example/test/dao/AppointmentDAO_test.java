package com.example.test.dao;

import com.example.test.model.appointment.dao.AppointmentDAO;
import com.example.test.model.appointment.vo.ReserveVO;
import lombok.extern.slf4j.Slf4j;
import oracle.sql.DATE;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AppointmentDAO_test {

    @Autowired
    private AppointmentDAO appointmentDAO;
    @Autowired
    private ReserveVO vo;

    @Test
    public void testGet(){ log.info(appointmentDAO.get(2L).toString()); }

    @Test void testReserve(){
        ReserveVO reserveVO = new ReserveVO();

        reserveVO.setUserNo(1L);
        reserveVO.setDocNo(3L);
        reserveVO.setReserveDate("DATE");

        appointmentDAO.reserve(reserveVO);

        log.info("-------------------------------");
        log.info(appointmentDAO.getUserPhone(reserveVO.getUserNo()) + "");
        log.info(appointmentDAO.getDocPhone(reserveVO.getDocNo()) + "");
        log.info("-------------------------------");
    }
}
