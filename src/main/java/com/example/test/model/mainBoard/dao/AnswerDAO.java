package com.example.test.model.mainBoard.dao;

import com.example.test.mappers.AnswerMapper;
import com.example.test.mappers.BoardMapper;
import com.example.test.mappers.ReplyMapper;
import com.example.test.model.mainBoard.vo.AnswerVO;
import com.example.test.model.user.vo.DocVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnswerDAO {
    private final AnswerMapper answerMapper;

    public void insert(AnswerVO vo){
        answerMapper.insert(vo);
    }

   public List<AnswerVO> answerList(Long qnaNo){return answerMapper.answerList(qnaNo);}

   public void delete(Long reQnaNo){answerMapper.delete(reQnaNo);}
}
