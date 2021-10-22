package com.example.test.services;

import com.example.test.user.vo.DocVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MapService {

//    검색된 목록 가져오기
    public List<DocVO> getSearchList(String search);
//    전체 목록
    public List<DocVO> getList();
//      의사 찾기
    public List<DocVO> getDocs(String docHospitalName);
}
