package com.example.test.mappers;

import com.example.test.model.mainBoard.vo.AnswerVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AnswerMapperTest {
    @Autowired
    AnswerMapper mapper;

    @Test
    public void insertTest(){
        AnswerVO answerVO =new AnswerVO();
        answerVO.setReQnaNo(4L);
        answerVO.setQnaNo(1L);
        answerVO.setDocNo(82L);
        answerVO.setReQnaContent("안녕하세요");

        mapper.insert(answerVO);
    }


    @Test
    public void answerListTest(){
        mapper.answerList(160L).forEach(answerVO -> log.info(answerVO.toString()));
    }
}
