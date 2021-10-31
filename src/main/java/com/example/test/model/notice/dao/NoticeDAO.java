package com.example.test.model.notice.dao;

import com.example.test.model.beans.vo.Criteria;
import com.example.test.mappers.NoticeMapper;
import com.example.test.model.notice.vo.NoticeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class NoticeDAO {

    private final NoticeMapper noticeMapper;

    public List<NoticeVO> getList(Criteria criteria){return noticeMapper.getList(criteria);}

    public NoticeVO get(Long noticeNo) {return noticeMapper.get(noticeNo); }

    public int getTotal(Criteria criteria) {return noticeMapper.getTotal(criteria); }

    public int updateViews(Long noticeNo) {return noticeMapper.updateViews(noticeNo); }
}
