package com.example.test.volunteer.dao;

import com.example.test.beans.vo.Criteria;
import com.example.test.mappers.VolunteerMapper;
import com.example.test.volunteer.vo.ApplicantsVO;
import com.example.test.volunteer.vo.VolunteerBoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class VolunteerDAO {
    private final VolunteerMapper volunteerMapper;
    
    //전체조회
    public List<VolunteerBoardVO> getList(Criteria criteria){return volunteerMapper.getList(criteria);}

    //상세보기
    public VolunteerBoardVO get(Long volunteerBoardNo){
        return volunteerMapper.get(volunteerBoardNo);
    }

    //게시글 등록
    public void insert(ApplicantsVO applicantsVO){ volunteerMapper.insert(applicantsVO); }

    //게시글 제목가져오기
    public String getTitle(Long bno){return volunteerMapper.getTitle(bno);}

    public int countApplicants(Long bno){return volunteerMapper.countApplicants(bno);}

    public void update(Long volunteerBoardNo){volunteerMapper.update(volunteerBoardNo);}

    public int getTotal(Criteria criteria){ return volunteerMapper.getTotal(criteria); }
}
