package com.example.test.services;

import com.example.test.user.dao.MapDAO;
import com.example.test.user.vo.DocVO;
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
}
