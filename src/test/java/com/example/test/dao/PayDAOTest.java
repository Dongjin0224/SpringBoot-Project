package com.example.test.dao;

import com.example.test.model.payment.dao.PayDAO;
import com.example.test.model.payment.vo.PayVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class PayDAOTest {

    @Autowired
    private PayDAO payDAO;

    @Test
    public void updateCustomer(){
        PayVO payVO = new PayVO();
        payVO.setDocNo(3L);
        payVO.setCard_number("2345-2345-2345-2345");
        payVO.setExpiry("2022-11");
        payVO.setBirth("000101");
        payVO.setPwd_2digit("12");
        payVO.setCustomer_uid("queu_2345");
        log.info("===============================");
        payDAO.updateCustomer(payVO);
        log.info("===============================");
    }

    @Test
    public void payTest(){
        PayVO payVO = new PayVO();
//        UPDATE TABLE_PAYMENT
//        SET PAYSTATUS = 1, AMOUNT = #{amount}, PAYDATE = SYSDATE, "NAME" = #{name}
//        WHERE DOCNO = #{docNo}
        payVO.setAmount(1000);
        payVO.setName("DAOTest");
        payVO.setDocNo(3L);
        log.info("===============================");
        payDAO.pay(payVO);
        log.info("===============================");
    }
}
