package com.example.test.mappers;

import com.example.test.model.user.vo.DocAttachFileVO;

import java.util.List;


public interface DocAttachFileMapper {

    public void insert(DocAttachFileVO docAttachFileVO);
    public void delete(String uuid);
    public List<DocAttachFileVO> findByBno(Long bno);

}
