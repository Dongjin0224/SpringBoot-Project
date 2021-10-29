package com.example.test.services;

import com.example.test.beans.vo.Criteria;
import com.example.test.notice.vo.NoticeVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoticeService {

    public List<NoticeVO> getList(Criteria criteria);

    public NoticeVO get(Long noticeNo);

    public int getTotal(Criteria criteria);

    public int updateViews(Long noticeNo);
}
