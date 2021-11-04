package com.example.test.model.myPage.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class QnaReplyVO {
    private Long reQnaNo;
    private Long qnaNo;
    private Long docNo;
    private String reQnaDate;
    private int reQnaLike;
    private String qnaMajor;
    private String qnaTitle;
    private String qnaViewCnt;

}
