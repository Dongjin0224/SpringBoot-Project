package com.example.test.model.payment.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PayVO {
    private Long payNo;
    private Long docNo;
    private int payStatus;
    private String payDate;
    private String card_number;
    private String expiry;
    private String birth;
    private String pwd_2digit;
    private String customer_uid;
    private String name;
    private int amount;
}
