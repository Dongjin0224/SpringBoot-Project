package com.example.test.mappers;

import com.example.test.model.user.vo.DocHosAttachFileVO;

import java.util.List;


public interface DocHosAttachFileMapper {

    public void insert(DocHosAttachFileVO docHosAttachFileVO);
    public void delete(String uuid);
    public List<DocHosAttachFileVO> findByBno(Long bno);

}
