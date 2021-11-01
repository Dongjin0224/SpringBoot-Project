package com.example.test.mappers;

import com.example.test.model.payment.vo.PayVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayMapper {

    //처음 회원가입 때 정보
    public void insertCustomer(PayVO payVO);

    //customer_uid 등록(결제 정보 등록)
    public void updateCustomer(PayVO payVO);

    //결제 확인
    public void pay(PayVO payVO);

    //회원 한 명의 결제 정보
    public PayVO getPayList(Long docNo);
}
