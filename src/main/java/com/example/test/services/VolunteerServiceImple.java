package com.example.test.services;

import com.example.test.model.beans.vo.Criteria;
import com.example.test.model.volunteer.dao.VolunteerDAO;
import com.example.test.model.volunteer.vo.ApplicantsVO;
import com.example.test.model.volunteer.vo.VolunteerBoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerServiceImple implements VolunteerService {

    private final VolunteerDAO volunteerDAO;

    @Override
    public List<VolunteerBoardVO> getList(Criteria criteria) {
        return volunteerDAO.getList(criteria);
    }

    @Override
    public VolunteerBoardVO get(Long volunteerBoardNo) {
        return volunteerDAO.get(volunteerBoardNo);
    }

    @Override
    public void insert(ApplicantsVO applicantsVO) {
        volunteerDAO.insert(applicantsVO);
    }

    @Override
    public String getTitle(Long bno) {
        return volunteerDAO.getTitle(bno);
    }

    @Override
    public int countApplicants(Long bno) {
        return volunteerDAO.countApplicants(bno);
    }

    @Override
    public void update(Long volunteerBoardNo) {
        volunteerDAO.update(volunteerBoardNo);
    }

    @Override
    public int getTotal(Criteria criteria) { return volunteerDAO.getTotal(criteria); }
}
