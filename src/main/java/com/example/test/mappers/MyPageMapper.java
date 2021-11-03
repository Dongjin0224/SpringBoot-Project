package com.example.test.mappers;

import com.example.test.model.appointment.vo.AppointmentVO;
import com.example.test.model.appointment.vo.ReserveVO;
import com.example.test.model.mainBoard.vo.BoardVO;
import com.example.test.model.myPage.vo.QnaReplyVO;
import com.example.test.model.myPage.vo.VolunteerContentVO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import com.example.test.model.volunteer.vo.ApplicantsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface MyPageMapper {

    public UserVO viewUser(Long userNo);
    public DocVO viewDoc(Long docNo);

    public void updateUser(UserVO userVO);
    public void updateDoc(DocVO docVO);

    public void deleteUser(Long userNo);
    public void deleteDoc(Long docNo);

    public List<ApplicantsVO> getVolList(Long docNo);

    public List<AppointmentVO> getAppList(Long docNo);

    public List<AppointmentVO> getResList(Long userNo);

    public VolunteerContentVO getVolContent(Long docNo, Long applicantsNo);

    public List<BoardVO> getQnaList(Long userNo);

    public List<QnaReplyVO> getQnaReply(Long docNo);
}
