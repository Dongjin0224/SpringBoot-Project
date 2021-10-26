package com.example.test.mappers;

import com.example.test.user.vo.DocVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppointmentMapper {

    public DocVO read(Long docNo);
}
