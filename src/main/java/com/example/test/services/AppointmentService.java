package com.example.test.services;

import com.example.test.model.appointment.vo.ReserveDocVO;
import com.example.test.model.appointment.vo.ReserveUserVO;
import com.example.test.model.appointment.vo.ReserveVO;
import com.example.test.model.user.vo.DocAttachFileVO;
import com.example.test.model.user.vo.DocVO;
import org.springframework.stereotype.Service;

@Service
public interface AppointmentService {

    public DocVO get(Long docNo);
    public DocAttachFileVO getFile(Long docNo);
    public void reserve(ReserveVO reserveVO);
    public ReserveUserVO getUserPhone(Long userNo);
    public ReserveDocVO getDocPhone(Long docNo);
}
