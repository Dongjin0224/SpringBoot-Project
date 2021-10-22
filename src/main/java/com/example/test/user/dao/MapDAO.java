package com.example.test.user.dao;

import com.example.test.mappers.MapMapper;
import com.example.test.user.vo.DocVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MapDAO {

    private final MapMapper mapMapper;

    public List<DocVO> getSearchList(String search){return mapMapper.getList(search);}

}
