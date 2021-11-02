package com.example.test.model.mainBoard.dao;


import com.example.test.model.mainBoard.vo.AttachFileVO;
import com.example.test.mappers.BoardAttachFileMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardAttachFileDAO {

    private final BoardAttachFileMapper boardAttachFileMapper;

    public void insert(AttachFileVO attachFileVO){
        boardAttachFileMapper.insert(attachFileVO);
    }

    public void delete(String uuid){
        boardAttachFileMapper.delete(uuid);
    }

    public List<AttachFileVO> findByBno(Long qnaNo){
        return boardAttachFileMapper.findByBno(qnaNo);
    }
}
