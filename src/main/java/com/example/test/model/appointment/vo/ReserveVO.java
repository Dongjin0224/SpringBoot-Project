package com.example.test.model.appointment.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReserveVO {

    private Long reserveNo;
    private Long userNo;
    private Long docNo;
    private String reserveDate;

}
