package com.example.test.model.user.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class DocVO {
    private int docNo;
    private String docId;
    private String docPw;
    private String docName;
    private String docMajor;
    private String docHospitalName;
    private double docLat;
    private double docLng;
    private String docAddress;
    private String docEmail;
    private String docPhoneNum;
    private String docPic;
    private int docStatus;
    private int docPayStatus;
    private int docServiceCnt;
    private int docReportCnt;
    private int docLikeCnt;
    private String card_number;
    private String expiry;
    private String birth;
    private String pwd_2digit;
}
