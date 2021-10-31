package com.example.test.model.user.dao;

import com.example.test.mappers.DocHosAttachFileMapper;
import com.example.test.model.user.vo.DocHosAttachFileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DocHosAttachFileDAO {

    private final DocHosAttachFileMapper docHosAttachFileMapper;

    public void insert(DocHosAttachFileVO docHosAttachFileVO){
        docHosAttachFileMapper.insert(docHosAttachFileVO);
    }

    public void delete(String uuid){
        docHosAttachFileMapper.delete(uuid);
    }

    public List<DocHosAttachFileVO> findByBno(Long docNo) {
        return docHosAttachFileMapper.findByBno(docNo);
    }

    /*public void deleteAll(Long bno){docAttachFileMapper.deleteAll(bno);}

    public List<DocAttachFileVO> getOldFiles() {return docAttachFileMapper.getOldFiles();}
*/

}
