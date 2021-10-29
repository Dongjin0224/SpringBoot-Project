package com.example.test.mappers;

import com.example.test.model.user.vo.DocAttachFileVO;
import com.example.test.model.user.vo.DocVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppointmentMapper {

    public DocVO read(Long docNo);
    public DocAttachFileVO getFile(Long docNo);
}
