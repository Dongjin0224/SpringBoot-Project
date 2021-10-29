package com.example.test.mappers;

import com.example.test.beans.vo.Criteria;
import com.example.test.notice.vo.NoticeVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
@Slf4j
public class NoticeMapperTest {
    @Autowired
    private NoticeMapper noticeMapper;

    @Test
    public void getListTest(){
        Criteria criteria = new Criteria();
        criteria.setPageNum(2);
        criteria.setAmount(10);
        ArrayList<NoticeVO> vo = (ArrayList<NoticeVO>) noticeMapper.getList(criteria);
        noticeMapper.getList(criteria).forEach(notice ->{
            log.info("----------------------------");
            log.info(notice.getNoticeTitle());
            log.info(notice.getNoticeContent());
            log.info(notice.getNoticeDate());
            log.info("----------------------------");
        });
    }

    @Test
    public void getTest(){
        log.info(noticeMapper.get(1L).toString());
    }

}
