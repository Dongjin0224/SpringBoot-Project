package com.example.test.model.appointment.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReserveUserVO {

    private String userName;
    private String userPhoneNum;

}
