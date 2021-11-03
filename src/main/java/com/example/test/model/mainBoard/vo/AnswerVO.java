package com.example.test.model.mainBoard.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AnswerVO {
    private Long reQnaNo;
    private Long qnaNo;
    private Long docNo;
    private String reQnaContent;
    private String reQnaDate;
    private int reQnaLike;
    private int reQnaReport;
    private String docName;
    private String docAddress;
    private String docMajor;
    private String fileName;
    private String qnaTitle;
}