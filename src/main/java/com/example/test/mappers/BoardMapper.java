package com.example.test.mappers;


import com.example.test.model.mainBoard.vo.BoardVO;
import com.example.test.model.beans.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    //    게시글 목록
    public List<BoardVO> getList(Criteria criteria);
    //    게시글 추가
    public void insert(BoardVO board);
    //    게시글 추가(PK가져오기)
    public void insertSelectKey_bno(BoardVO board);
    //    게시글 상세보기(특정 게시글 정보)
    public BoardVO read(Long qnaNo);
    //    게시글 수정
    public int update(BoardVO board);
    //    게시글 삭제
    public int delete(Long qnaNo);
    //    게시글 전체 개수
    public int getTotal(Criteria criteria);
//    검색한 게시글 목록
    public List<BoardVO> getSearchList(Criteria criteria);
//    조회수 검색
    public Long viewCnt(Long qnaNo);
//    조회수 업데이트
    public void updateViewCnt(Long qnaNo);
}
