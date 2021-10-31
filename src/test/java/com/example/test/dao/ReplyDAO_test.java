package com.example.test.dao;


import com.example.test.model.appointment.dao.ReplyDAO;
import com.example.test.model.beans.vo.Criteria;
import com.example.test.model.vo.ReplyVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class ReplyDAO_test {
    private Long[] arBno = {8388648L, 8388647L, 8388646L, 8388645L, 8388644L};

    @Setter(onMethod_ =@Autowired)
    private ReplyDAO replyDAO;

    @Test
    public void testDAO(){
        log.info(replyDAO.toString());
    }

    @Test
    public void testInsert(){
        IntStream.rangeClosed(1, 10).forEach(i -> {
            ReplyVO reply = new ReplyVO();
            reply.setQnaNo(arBno[i % 5]);
            reply.setReQnaDate("댓글 테스트" + i);
            reply.setReQnaLike("replier" + i);
            replyDAO.register(reply);
        });
    }

    @Test
    public void testRead(){
        log.info(replyDAO.get(16L).toString());
    }

    @Test
    public void testDelete(){
        log.info("DELETE COUNT : " + replyDAO.remove(15L));
    }

    @Test
    public void testUpdate(){
        ReplyVO replyVO = replyDAO.get(16L);
        replyVO.setReQnaDate("UPDATE TEST");
        log.info("UPDATE COUNT : " + replyDAO.modify(replyVO));
    }

    @Test
    public void testGetListWithPaging(){
        Criteria criteria = new Criteria();
        replyDAO.getList(arBno[0], criteria).forEach(reply -> log.info(reply.toString()));
    }
}
