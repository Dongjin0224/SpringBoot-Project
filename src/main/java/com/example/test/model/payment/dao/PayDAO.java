package com.example.test.model.payment.dao;

import com.example.test.mappers.PayMapper;
import com.example.test.model.payment.vo.PayVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PayDAO {

    private final PayMapper payMapper;

    public void insertCustomer(PayVO payVO){
        payMapper.insertCustomer(payVO);
    }

    public void updateCustomer(PayVO payVO){
        payMapper.updateCustomer(payVO);
    }

    public void pay(PayVO payVO){
        payMapper.pay(payVO);
    }

    public PayVO getPayList(Long docNo){
        return payMapper.getPayList(docNo);
    }
}
