package com.example.test.services;

import com.example.test.appointment.dao.AppointmentDAO;
import com.example.test.user.vo.DocVO;
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
}
