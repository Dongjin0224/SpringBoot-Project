package com.example.test.mappers;

import com.example.test.model.mainBoard.vo.AttachFileVO;

import java.util.List;

public interface BoardAttachFileMapper {
    public void insert(AttachFileVO attachFileVO);
    public void delete(String uuid);
    public List<AttachFileVO> findByBno(Long qnaNo);
}
