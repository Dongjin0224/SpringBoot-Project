package com.example.test.model.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class BoardVO {
    private Long qnaNo;
    private Long userNo;
    private String qnaTitle;
    private String qnaAddress;
    private String qnaAge;
    private String qnaGender;
    private String qnaDisease;
    private String qnaMajor;
    private String qnaContent;
    private String qnaDate;
    private Long qnaViewCnt;
    private List<AttachFileVO> attachList;
}
