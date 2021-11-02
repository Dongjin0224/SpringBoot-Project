package com.example.test.mappers;

import com.example.test.model.mainBoard.vo.AnswerVO;
import com.example.test.model.user.vo.DocVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnswerMapper {
    public void insert(AnswerVO answerVO);

    public List<AnswerVO> answerList(Long qnaNo);

    public AnswerVO selectList(AnswerVO answerVO);
}
