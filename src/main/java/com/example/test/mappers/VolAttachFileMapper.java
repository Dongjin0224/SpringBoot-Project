package com.example.test.mappers;

import com.example.test.model.volunteer.vo.VolAttachFileVO;

import java.util.List;

public interface VolAttachFileMapper {

    public void insert(VolAttachFileVO volAttachFileVO);
    public void delete(String uuid);
    public List<VolAttachFileVO> findByBno(Long bno);
}
