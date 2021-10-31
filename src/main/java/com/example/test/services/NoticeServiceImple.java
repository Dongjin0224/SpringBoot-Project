package com.example.test.services;

import com.example.test.model.beans.vo.Criteria;
import com.example.test.model.notice.dao.NoticeDAO;
import com.example.test.model.notice.vo.NoticeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImple implements NoticeService {

    private final NoticeDAO noticeDAO;

    @Override
    public List<NoticeVO> getList(Criteria criteria) { return noticeDAO.getList(criteria); }

    @Override
    public NoticeVO get(Long noticeNo) { return noticeDAO.get(noticeNo); }

    @Override
    public int getTotal(Criteria criteria) { return noticeDAO.getTotal(criteria); }

    @Override
    public int updateViews(Long noticeNo) { return noticeDAO.updateViews(noticeNo); }
}
