package com.example.test.mappers;


import com.example.test.board.vo.Criteria;
import com.example.test.board.vo.ReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {
    //    답변 등록
    public int insert(ReplyVO replyVO);
    //    댓글 1개 조회
    public ReplyVO read(Long rno);
    //    댓글 삭제
    public int delete(Long rno);
    //    댓글 수정
    public int update(ReplyVO replyVO);
    //    댓글 목록
    public int getTotal(Long qnaNo);

    public List<ReplyVO> getListWithPaging(@Param("qnaNo") Long bno, @Param("criteria") Criteria criteria);
}
