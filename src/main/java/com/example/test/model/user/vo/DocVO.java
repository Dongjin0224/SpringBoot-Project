package com.example.test.model.user.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class DocVO {
    private Long docNo;
    private String docId;
    private String docPw;
    private String docName;
    private String docMajor;
    private String docHospitalName;
    private String docHosPhone;
    private String docHistory;
    private double docLat;
    private double docLng;
    private String docAddress;
    private String docEmail;
    private String docPhoneNum;
    private int docStatus;
    private int docPayStatus;
    private int docServiceCnt;
    private int docReportCnt;
    private int docLikeCnt;


    private String uuid;
    private String uploadPath;
    private String fileName;
    private boolean image;

    private List<DocAttachFileVO> attachList;
    private List<DocHosAttachFileVO> hosattachList;

    private String docPic;
    private String hosPic;

}
