package com.example.test.mappers;

import com.example.test.user.vo.DocVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MapMapper {

//   검색 결과
    public List<DocVO> getList(String search);
}
