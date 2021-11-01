package com.example.test.model.volunteer.dao;

import com.example.test.mappers.VolAttachFileMapper;
import com.example.test.model.volunteer.vo.VolAttachFileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VolAttachFileDAO {

    private final VolAttachFileMapper volAttachFileMapper;

    public void insert(VolAttachFileVO volAttachFileVO) { volAttachFileMapper.insert(volAttachFileVO); }

    public void delete(String uuid) { volAttachFileMapper.delete(uuid); }

    public List<VolAttachFileVO> findByBno(Long volunteerBoardNo) { return volAttachFileMapper.findByBno(volunteerBoardNo); }
}
