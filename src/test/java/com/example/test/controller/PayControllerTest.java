package com.example.test.controller;

import com.example.test.model.payment.vo.PayVO;
import com.example.test.services.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

@Slf4j
@SpringBootTest
public class PayControllerTest {

    @Autowired
    private PayService pay;


    @Autowired
    PayController payController;

    //결제 테스트
    @Test
    public void payTest() throws UnsupportedEncodingException{
        PayVO payVO = new PayVO();
        payVO.setCustomer_uid("asd1234");
        payVO.setCard_number("4854-8003-0494-8872");
        payVO.setAmount(1000);
        payVO.setExpiry("2024-12");
        payVO.setBirth("960224");
        payVO.setPwd_2digit("51");
        payVO.setName("ControllerTest");
        payVO.setDocNo(1L);
        log.info("-----------------------------");
        log.info(payController.pay(payVO));
        log.info("-----------------------------");
    }

    //customerUid 등록
//    @Test
//    public void getCustomerTest() throws UnsupportedEncodingException {
//        PayVO payVO = new PayVO();
//        payVO.setCustomer_uid("asd1234");
//        payVO.setCard_number("4854-8003-0494-8872");
//        payVO.setExpiry("2024-12");
//        payVO.setBirth("960224");
//        payVO.setPwd_2digit("51");
//        payVO.setDocNo(4L);
//        log.info("-----------------------------");
//        payController.getCustomer(payVO);
//        log.info("-----------------------------");
//    }
//
//    @Test
//    public void test() throws UnsupportedEncodingException {
//        PayVO payVO = new PayVO();
//        payVO.setCustomer_uid("asd1234");
//        payVO.setCard_number("1234-1234-1234-1234");
//        payVO.setExpiry("2024-12");
//        payVO.setBirth("960224");
//        payVO.setPwd_2digit("51");
//        payVO.setAmount(1000);
//        payVO.setDocNo(1L);
//
//        log.info("code : " + payController.getCustomer(payVO).split(":")[1].split(",")[0]);
//    }


}
