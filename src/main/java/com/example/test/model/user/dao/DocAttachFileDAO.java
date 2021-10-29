package com.example.test.model.user.dao;

import com.example.test.mappers.DocAttachFileMapper;
import com.example.test.model.user.vo.DocAttachFileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocAttachFileDAO {

    @Autowired
    private DocAttachFileMapper docAttachFileMapper;

    public void insert(DocAttachFileVO docAttachFileVO){
        docAttachFileMapper.insert(docAttachFileVO);
    }

    public void delete(String uuid){
        docAttachFileMapper.delete(uuid);
    }

    public List<DocAttachFileVO> findByBno(Long docNo) {
        return docAttachFileMapper.findByBno(docNo);
    }

    /*public void deleteAll(Long bno){docAttachFileMapper.deleteAll(bno);}

    public List<DocAttachFileVO> getOldFiles() {return docAttachFileMapper.getOldFiles();}
*/

}
