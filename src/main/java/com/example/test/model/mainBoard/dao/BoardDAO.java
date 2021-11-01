package com.example.test.model.mainBoard.dao;


import com.example.test.model.mainBoard.vo.BoardVO;
import com.example.test.model.beans.vo.Criteria;
import com.example.test.mappers.BoardMapper;
import com.example.test.model.user.vo.DocVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDAO {
    private final BoardMapper mapper;

    public void register(BoardVO board){
        mapper.insertSelectKey_bno(board);
    }

    public BoardVO get(Long bno){
        return mapper.read(bno);
    }

    public boolean modify(BoardVO board){
        return mapper.update(board) == 1;
    }

    public boolean remove(Long bno){
        return mapper.delete(bno) == 1;
    }

    public List<BoardVO> getList(Criteria criteria){
        return mapper.getList(criteria);
    }

    public int getTotal(Criteria criteria){ return mapper.getTotal(criteria); }


    public List<DocVO> getLikeCnt() { return mapper.getLikeCnt(); }

    public List<DocVO> getReplyCnt() { return  mapper.getReplyCnt(); }

    public List<BoardVO> getSearchList(Criteria criteria){return mapper.getSearchList(criteria);}

    public Long viewCnt(Long qnaNo){return mapper.viewCnt(qnaNo);}
    public void updateViewCnt(Long qnaNo){mapper.updateViewCnt(qnaNo);}
}
