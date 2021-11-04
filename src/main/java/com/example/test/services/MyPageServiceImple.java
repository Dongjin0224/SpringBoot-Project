package com.example.test.services;

import com.example.test.model.appointment.vo.AppointmentVO;
import com.example.test.model.appointment.vo.ReserveVO;
import com.example.test.model.beans.vo.Criteria;
import com.example.test.model.mainBoard.vo.BoardVO;
import com.example.test.model.myPage.dao.MyPageDAO;
import com.example.test.model.myPage.vo.QnaReplyVO;
import com.example.test.model.myPage.vo.VolunteerContentVO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import com.example.test.model.volunteer.vo.ApplicantsVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageServiceImple implements MyPageService {

    private final MyPageDAO myPageDAO;

    @Override
    public UserVO viewUser(Long userNo) { return myPageDAO.viewUser(userNo); }

    @Override
    public DocVO viewDoc(Long docNo) { return myPageDAO.viewDoc(docNo); }

    @Override
    public void updateUser(UserVO userVO) { myPageDAO.updateUser(userVO); }

    @Override
    public void updateDoc(DocVO docVO) { myPageDAO.updateDoc(docVO); }

    @Override
    public List<ApplicantsVO> getVolList(Long docNo) { return myPageDAO.getVolList(docNo); }

    @Override
    public List<AppointmentVO> getAppList(Long docNo) { return myPageDAO.getAppList(docNo); }

    @Override
    public List<AppointmentVO> getResList(Long userNo) { return myPageDAO.getResList(userNo); }

    @Override
    public VolunteerContentVO getVolContent(Long docNo, Long applicantsNo) { return myPageDAO.getVolContent(docNo, applicantsNo); }

    @Override
    public List<BoardVO> getQnaList(Long userNo) { return myPageDAO.getQnaList(userNo); }

    @Override
    public List<QnaReplyVO> getQnaReply(Long docNo) { return myPageDAO.getQnaReply(docNo); }

    /*@Override
    public int getVolTotal(Criteria criteria) { return myPageDAO.getVolTotal(criteria); }*/

}
