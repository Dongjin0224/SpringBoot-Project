package com.example.test.model.appointment.dao;


import com.example.test.model.vo.AttachFileVO;
import com.example.test.mappers.AttachFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttachFileDAO {
    @Autowired
    private AttachFileMapper attachFileMapper;

    public void insert(AttachFileVO attachFileVO){
        attachFileMapper.insert(attachFileVO);
    }

    public void delete(String uuid){
        attachFileMapper.delete(uuid);
    }

    public List<AttachFileVO> findByBno(Long qnaNo){
        return attachFileMapper.findByBno(qnaNo);
    }
}
