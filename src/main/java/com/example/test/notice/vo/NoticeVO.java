package com.example.test.notice.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class NoticeVO {
    private Long noticeNo;
    private Long userNo;
    private String noticeTitle;
    private String noticeContent;
    private String noticeDate;
    private int noticeViews;

}
