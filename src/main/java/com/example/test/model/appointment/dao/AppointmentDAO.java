package com.example.test.model.appointment.dao;

import com.example.test.mappers.AppointmentMapper;
import com.example.test.model.appointment.vo.ReserveDocVO;
import com.example.test.model.appointment.vo.ReserveUserVO;
import com.example.test.model.appointment.vo.ReserveVO;
import com.example.test.model.user.vo.DocAttachFileVO;
import com.example.test.model.user.vo.DocVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class AppointmentDAO {

    public final AppointmentMapper appointmentMapper;

    public DocVO get(Long docNo){ return appointmentMapper.read(docNo); }
    public DocAttachFileVO getFile(Long docNo){ return appointmentMapper.getFile(docNo); }
    public void reserve(ReserveVO reserveVO){ appointmentMapper.reserve(reserveVO); }
    public ReserveUserVO getUserPhone(Long userNo){ return appointmentMapper.getUserPhone(userNo); }
    public ReserveDocVO getDocPhone(Long docNo){ return appointmentMapper.getDocPhone(docNo); }
}
