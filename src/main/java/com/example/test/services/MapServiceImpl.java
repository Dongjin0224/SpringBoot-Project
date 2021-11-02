package com.example.test.services;

import com.example.test.model.user.dao.MapDAO;
import com.example.test.model.user.vo.DocAttachFileVO;
import com.example.test.model.user.vo.DocHosAttachFileVO;
import com.example.test.model.user.vo.DocVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService{

    private final MapDAO mapDAO;

    @Override
    public List<DocVO> getSearchList(String search) {
        return mapDAO.getSearchList(search);
    }

    @Override
    public List<DocVO> getList() {return mapDAO.getList();}

    @Override
    public List<DocVO> getDocs(String docHospitalName) {return mapDAO.getDocs(docHospitalName); }

    @Override
    public DocVO docPic(Long docNo) {
        return mapDAO.docPic(docNo);
    }

    @Override
    public DocHosAttachFileVO hosPic(Long docNo) {
        return mapDAO.hosPic(docNo);
    }
}
