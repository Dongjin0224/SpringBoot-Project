package com.example.test.services;

import com.example.test.model.appointment.vo.AppointmentVO;
import com.example.test.model.appointment.vo.ReserveVO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import com.example.test.model.volunteer.vo.ApplicantsVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface MyPageService {

    public UserVO viewUser(Long userNo);
    public DocVO viewDoc(Long docNo);
    public void updateUser(UserVO userVO);
    public void updateDoc(DocVO docVO);
    public List<ApplicantsVO> getVolList(Long docNo);
    public List<AppointmentVO> getAppList(Long docNo);
}
