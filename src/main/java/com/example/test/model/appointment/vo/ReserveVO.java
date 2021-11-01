package com.example.test.model.appointment.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReserveVO {

    Long reserveNo;
    Long userNo;
    Long docNo;
    String reserveDate;
}
