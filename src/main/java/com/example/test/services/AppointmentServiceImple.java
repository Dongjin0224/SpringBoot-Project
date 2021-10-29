package com.example.test.services;

import com.example.test.model.appointment.dao.AppointmentDAO;
import com.example.test.model.user.vo.DocAttachFileVO;
import com.example.test.model.user.vo.DocVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImple implements AppointmentService {

    private final AppointmentDAO appointmentDAO;

    @Override
    public DocVO get(Long docNo) {
        return appointmentDAO.get(docNo);
    }

    @Override
    public DocAttachFileVO getFile(Long docNo) {
        return appointmentDAO.getFile(docNo);
    }
}
