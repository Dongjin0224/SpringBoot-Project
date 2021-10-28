package com.example.test.mappers;

import com.example.test.user.vo.DocAttachFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface DocAttachFileMapper {

    public void insert(DocAttachFileVO docAttachFileVO);
    public void delete(String uuid);
    public List<DocAttachFileVO> findByBno(Long bno);

}
