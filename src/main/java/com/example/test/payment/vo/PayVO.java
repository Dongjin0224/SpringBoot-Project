package com.example.test.payment.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PayVO {
    private String card_number;
    private String expiry;
    private String birth;
    private String pwd_2digit;
    private String customer_uid;
    private String name;
}
