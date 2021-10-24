package com.example.test.services;

import com.example.test.user.vo.DocVO;
import org.springframework.stereotype.Service;

@Service
public interface AppointmentService {

    public DocVO get(Long docNo);

}
