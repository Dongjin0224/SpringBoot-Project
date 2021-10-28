package com.example.test.services;


import com.example.test.appointment.dao.ReplyDAO;
import com.example.test.board.vo.Criteria;
import com.example.test.board.vo.ReplyPageDTO;
import com.example.test.board.vo.ReplyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyDAO replyDAO;

    public int register(ReplyVO replyVO){
        log.info("register..............");
        return replyDAO.register(replyVO);
    }

    public ReplyVO get(Long reQnaNo){
        log.info("get..............");
        return replyDAO.get(reQnaNo);
    }

    public int remove(Long reQnaNo){
        log.info("remove..............");
        return replyDAO.remove(reQnaNo);
    }

    public int modify(ReplyVO replyVO){
        log.info("modify..............");
        return replyDAO.modify(replyVO);
    }

    public ReplyPageDTO getList(Long reQnaNo, Criteria criteria){
        log.info("getList..............");
        return new ReplyPageDTO(replyDAO.getTotal(reQnaNo), replyDAO.getList(reQnaNo, criteria));
    }
}
