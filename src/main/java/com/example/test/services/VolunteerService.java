package com.example.test.services;

import com.example.test.model.beans.vo.Criteria;
import com.example.test.model.volunteer.vo.ApplicantsVO;
import com.example.test.model.volunteer.vo.VolAttachFileVO;
import com.example.test.model.volunteer.vo.VolunteerBoardVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VolunteerService {

    //전체목록
    public List<VolunteerBoardVO> getList(Criteria criteria);

    //상세보기
    public VolunteerBoardVO get(Long volunteerBoardNo);

    public void insert(ApplicantsVO applicantsVO);

    public String getTitle(Long bno);

    public int countApplicants(Long bno);

    public void update(Long volunteerBoardNo);

    public int getTotal(Criteria criteria);

    public List<VolAttachFileVO> findByBno(Long volunteerBoardNo);

    public int checkCnt(Long docNo, Long volunteerBoardNo);
}
