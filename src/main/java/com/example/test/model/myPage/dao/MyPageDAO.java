package com.example.test.model.myPage.dao;

import com.example.test.mappers.MyPageMapper;
import com.example.test.model.appointment.vo.AppointmentVO;
import com.example.test.model.appointment.vo.ReserveVO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import com.example.test.model.volunteer.vo.ApplicantsVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MyPageDAO {

    private final MyPageMapper myPageMapper;

    public UserVO viewUser(Long userNo){ return myPageMapper.viewUser(userNo); }
    public DocVO viewDoc(Long docNo){ return myPageMapper.viewDoc(docNo); }
    public void updateUser(UserVO userVO){ myPageMapper.updateUser(userVO); }
    public void updateDoc(DocVO docVO){ myPageMapper.updateDoc(docVO); }
    public List<ApplicantsVO> getVolList(Long docNo) { return myPageMapper.getVolList(docNo); }
    public List<AppointmentVO> getAppList(Long docNo) { return myPageMapper.getAppList(docNo); }

}
