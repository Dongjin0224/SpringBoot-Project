package com.example.test.mappers;

import com.example.test.model.user.vo.DocAttachFileVO;
import com.example.test.model.user.vo.DocHosAttachFileVO;
import com.example.test.model.user.vo.DocVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MapMapper {

//   검색 결과
    public List<DocVO> getSearchList(String search);
//    전체 결과
    public List<DocVO> getList();
//    병원에 등록한 의사 찾기
    public List<DocVO> getDocs(String docHospitalName);

    public DocVO docPic(Long docNo);
    public DocHosAttachFileVO hosPic(Long docNo);
}
