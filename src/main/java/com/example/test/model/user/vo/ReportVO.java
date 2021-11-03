package com.example.test.model.user.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReportVO {
    /* 신고하기 뷰에 뿌려줄 내용 */
    private Long reportNo;
    private Long reQnaNo;
    private Long docNo;
    private Long userNo;
    private String reportType;
    private String reportContent;
}
