package com.example.test.model.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReplyVO {
    private Long reQnaNo;
    private Long qnaNo;
    private Long docNo;
    private String reQnaContent;
    private String reQnaDate;
    private String reQnaLike;
    private String reQnaReport;
}
