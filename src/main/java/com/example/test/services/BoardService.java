package com.example.test.services;


import com.example.test.model.mainBoard.vo.AttachFileVO;
import com.example.test.model.mainBoard.vo.BoardVO;
import com.example.test.model.beans.vo.Criteria;
import com.example.test.model.user.vo.DocVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    public void register(BoardVO board);
    public BoardVO get(Long qnaNo);
    public boolean modify(BoardVO board);
    public boolean remove(Long qnaNo);
    public List<BoardVO> getList(Criteria criteria);
    public int getTotal(Criteria criteria);
    public List<AttachFileVO> getAttachList(Long qnaNo);
    public List<BoardVO> getSearchList(Criteria criteria);
    public List<DocVO> getLikeCnt();
    public List<DocVO> getReplyCnt();
}
