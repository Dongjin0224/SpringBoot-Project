package com.example.test.model.user.dao;

import com.example.test.mappers.MapMapper;
import com.example.test.model.user.vo.DocAttachFileVO;
import com.example.test.model.user.vo.DocHosAttachFileVO;
import com.example.test.model.user.vo.DocVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MapDAO {

    private final MapMapper mapMapper;

//    검색결과
    public List<DocVO> getSearchList(String search){return mapMapper.getSearchList(search);}
//      전체결과
    public List<DocVO> getList(){return mapMapper.getList();}
//    의사찾기
    public List<DocVO> getDocs(String docHospitalName){return mapMapper.getDocs(docHospitalName);}

    public DocHosAttachFileVO hosPic(Long docNo){return mapMapper.hosPic(docNo);}
    public DocVO docPic(Long docNo){return mapMapper.docPic(docNo);}
}
