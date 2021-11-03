package com.example.test.model.myPage.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class VolunteerContentVO {

    private String volunteerBoardTitle;
    private String docName;
    private String docPhoneNum;
    private String applicants;
    private String applicationContent;
    private Long applicantsNO;

}
