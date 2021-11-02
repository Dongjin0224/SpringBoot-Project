package com.example.test.services;



import com.example.test.model.mainBoard.dao.BoardAttachFileDAO;
import com.example.test.model.mainBoard.dao.BoardDAO;
import com.example.test.model.mainBoard.vo.AttachFileVO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.model.mainBoard.vo.BoardVO;
import com.example.test.model.beans.vo.Criteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServicieImple implements BoardService {
    private final BoardDAO boardDAO;
    private final BoardAttachFileDAO boardAttachFileDAO;

    @Transactional
    @Override
    public void register(BoardVO board) {
        boardDAO.register(board);
        if(board.getAttachList() == null || board.getAttachList().size() == 0){
            return;
        }
        board.getAttachList().forEach(attach -> {
            attach.setQnaNo(board.getQnaNo());
            log.info("첨부파일 insert");
            boardAttachFileDAO.insert(attach);
        });
    }
    @Override
    public BoardVO get(Long qnaNo) {
        return boardDAO.get(qnaNo);
    }

    @Override
    public boolean modify(BoardVO board) {
        return boardDAO.modify(board);
    }

    @Override
    public boolean remove(Long qnaNo) {
        return boardDAO.remove(qnaNo);
    }

    @Override
    public List<BoardVO> getList(Criteria criteria) { return boardDAO.getList(criteria); }

    @Override
    public int getTotal(Criteria criteria) { return boardDAO.getTotal(criteria); }

    @Override
    public List<AttachFileVO> getAttachList(Long qnaNo) {
        return boardAttachFileDAO.findByBno(qnaNo);
    }

    @Override
    public List<BoardVO> getSearchList(Criteria criteria) {
        return boardDAO.getSearchList(criteria);
    }

    @Override
    public List<DocVO> getLikeCnt() { return boardDAO.getLikeCnt(); }

    @Override
    public List<DocVO> getReplyCnt() { return boardDAO.getReplyCnt(); }


    @Override
    public void updateView(Long qnaNo) {
       boardDAO.updateViewCnt(qnaNo);
    }

}
