package com.example.test.services;


import com.example.test.model.payment.vo.PayVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class PayServiceTest {

    @Autowired
    private PayService pay;

    @Test
    public void getToken(){
        log.info("---------------------");
        log.info("Token : " + pay.getToken());
        log.info("---------------------");
    }

    @Test
    public void updateCustomer(){
        PayVO payVO = new PayVO();
        payVO.setDocNo(1L);
        payVO.setCard_number("2245-2345-2345-2345");
        payVO.setExpiry("2022-11");
        payVO.setBirth("000101");
        payVO.setPwd_2digit("12");
        payVO.setCustomer_uid("queu_2345");
        log.info("===============================");
        pay.updateCustomer(payVO);
        log.info("===============================");
    }

    @Test
    public void payTest(){
        PayVO payVO = new PayVO();
//        UPDATE TABLE_PAYMENT
//        SET PAYSTATUS = 1, AMOUNT = #{amount}, PAYDATE = SYSDATE, "NAME" = #{name}
//        WHERE DOCNO = #{docNo}
        payVO.setAmount(1000);
        payVO.setName("ServiceTest");
        payVO.setDocNo(4L);
        log.info("===============================");
        pay.pay(payVO);
        log.info("===============================");
    }

}
