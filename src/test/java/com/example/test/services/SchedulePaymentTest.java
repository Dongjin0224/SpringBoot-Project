package com.example.test.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class SchedulePaymentTest {
    @Autowired
    private ImportPay pay;

    @Autowired
    private SchedulePayment schedulePayment;

    @Autowired
    ReqPaymentScheduler reqPaymentScheduler;

    @Test
    public void schedulePayTest(){
        log.info("---------------------------");
        log.info(schedulePayment.schedulePay(2234, 1000));
        log.info("---------------------------");
    }

    @Test
    public void getRunnableTest(){
        log.info("-----------------------------");
        reqPaymentScheduler.startScheduler(2234, 1000);
        log.info("-----------------------------");
    }

}
