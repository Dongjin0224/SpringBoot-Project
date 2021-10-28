package com.example.test.services;

import com.example.test.user.dao.DocAttachFileDAO;
import com.example.test.user.dao.DocDAO;
import com.example.test.user.dao.UserDAO;
import com.example.test.user.vo.DocAttachFileVO;
import com.example.test.user.vo.DocVO;
import com.example.test.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocSeviceImple implements DocService{

    private final DocDAO docDAO;
    private final DocAttachFileDAO docAttachFileDAO;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void DocSignUp(DocVO vo) {
        docDAO.doctorSignUp(vo);
        if(vo.getAttachList() ==null || vo.getAttachList().size() ==0){
            return;
        }

        vo.getAttachList().forEach(attach -> {
            attach.setDocNo(vo.getDocNo());
            docAttachFileDAO.insert(attach);
        });
    }

    @Override
    public List<DocAttachFileVO> getAttachList(Long docNo) {
        return docAttachFileDAO.findByBno(docNo);
    }


}
