package com.example.test.services;

import com.example.test.beans.vo.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class NoticeServiceTest {
    @Autowired
    private NoticeService noticeService;

    @Test
    public void testGetList() {
        Criteria criteria = new Criteria();
        noticeService.getList(criteria).forEach(notice -> log.info(notice.toString()));
    }

    @Test
    public void testGet() {
        log.info(noticeService.get(1L).toString());
    }
}
