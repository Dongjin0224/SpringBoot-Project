package com.example.test.mappers;


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
public class ReplyMapperTest {
    private Long[] arBno = {8388648L, 8388647L, 8388646L, 8388645L, 8388644L};

    @Setter(onMethod_ = @Autowired)
    private ReplyMapper replyMapper;

    @Test
    public void testMapper(){
        log.info(replyMapper.toString());
    }

    @Test
    public void testInsert(){
        IntStream.rangeClosed(1, 10).forEach(i -> {
            ReplyVO reply = new ReplyVO();
            reply.setQnaNo(arBno[i % 5]);
            reply.setReQnaLike("댓글 테스트" + i);
            reply.setReQnaDate("replier" + i);
            replyMapper.insert(reply);
        });
    }

    @Test
    public void testRead(){
        log.info(replyMapper.read(63L).toString());
    }

    @Test
    public void testDelete(){
        log.info("DELETE COUNT : " + replyMapper.delete(15L));
    }

    @Test
    public void testUpdate(){
        ReplyVO replyVO = replyMapper.read(16L);
        replyVO.setReQnaReport("UPDATE TEST");
        log.info("UPDATE COUNT : " + replyMapper.update(replyVO));
    }

    @Test
    public void testGetListWithPaging(){
        Criteria criteria = new Criteria();
        replyMapper.getListWithPaging(arBno[0], criteria).forEach(reply -> log.info(reply.toString()));
    }

    @Test
    public void getTotal(){
        replyMapper.getTotal(1L);
    }
}
