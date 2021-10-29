package com.example.test.dao;

import com.example.test.beans.vo.Criteria;
import com.example.test.notice.dao.NoticeDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class NoticeDAO_test {
    @Autowired
    private NoticeDAO noticeDAO;

    @Test
    public void getListTest(){
        log.info("----------------------------");
        Criteria criteria = new Criteria();
        noticeDAO.getList(criteria).forEach(notice -> {
            log.info(String.valueOf(notice.getNoticeNo()));
            log.info(String.valueOf(notice.getUserNo()));
            log.info(notice.getNoticeTitle());
            log.info(notice.getNoticeContent());
            log.info(notice.getNoticeDate());
        });
        log.info("----------------------------");
    }

    @Test
    public void getTest() {
        log.info(noticeDAO.get(1L).toString());
    }

}
