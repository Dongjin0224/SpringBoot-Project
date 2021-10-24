package com.example.test.appointment.dao;

import com.example.test.mappers.AppointmentMapper;
import com.example.test.user.vo.DocVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class AppointmentDAO {

    public final AppointmentMapper appointmentMapper;

    public DocVO get(Long docNo){ return appointmentMapper.read(docNo); }

}
