package com.example.test.model.appointment.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AppointmentVO {

    private String userName;
    private String userPhoneNum;
    private String docMajor;
    private String reserveDate;

    private String docName;
    private String docHosPhone;

}
