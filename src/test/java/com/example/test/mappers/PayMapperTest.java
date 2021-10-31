package com.example.test.mappers;

import com.example.test.model.payment.vo.PayVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PayMapperTest {

    @Autowired
    private PayMapper payMapper;
//    <insert id="insertCustomer">
//    INSERT INTO TABLE_PAYMENT
//            (PAYNO, DOCNO, AMOUNT, CARD_NUMBER, EXPIRY, BIRTH, PWD_2DIGIT, CUSTOMER_UID, "NAME")
//    VALUES(SEQ_PAYMENT.NEXTVAL, #{docNo}, 0, '', '', '', '', '', '');
//    </insert>

    @Test
    public void insertCustomerTest(){
        PayVO payVO = new PayVO();
        payVO.setDocNo(1L);
        payMapper.insertCustomer(payVO);
    }

    @Test
    public void updateCustomerTest(){
//        (PAYNO, DOCNO, CARD_NUMBER, EXPIRY, BIRTH, PWD_2DIGIT, CUSTOMER_UID)
        PayVO payVO = new PayVO();
        payVO.setDocNo(1L);
        payVO.setCard_number("1245-2345-2345-2345");
        payVO.setExpiry("2022-11");
        payVO.setBirth("000101");
        payVO.setPwd_2digit("12");
        payVO.setCustomer_uid("queu_2345");
        payMapper.updateCustomer(payVO);
    }

    @Test
    public void payTest(){
        PayVO payVO = new PayVO();
//        UPDATE TABLE_PAYMENT
//        SET PAYSTATUS = 1, AMOUNT = #{amount}, PAYDATE = SYSDATE, "NAME" = #{name}
//        WHERE DOCNO = #{docNo}
        payVO.setAmount(1000);
        payVO.setName("dongtest");
        payVO.setDocNo(2L);
        payMapper.pay(payVO);
    }
}
