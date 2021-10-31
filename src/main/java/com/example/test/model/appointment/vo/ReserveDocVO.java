package com.example.test.model.appointment.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReserveDocVO {

    private String docName;
    private String docPhoneNum;
}
