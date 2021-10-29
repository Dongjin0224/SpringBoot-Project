package com.example.test.services;

import com.example.test.model.user.vo.DocAttachFileVO;
import com.example.test.model.user.vo.DocVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppointmentService {

    public DocVO get(Long docNo);
    public DocAttachFileVO getFile(Long docNo);
}
