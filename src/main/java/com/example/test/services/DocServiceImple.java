package com.example.test.services;

import com.example.test.model.user.dao.DocAttachFileDAO;
import com.example.test.model.user.dao.DocDAO;
import com.example.test.model.user.dao.DocHosAttachFileDAO;
import com.example.test.model.user.vo.DocAttachFileVO;
import com.example.test.model.user.vo.DocHosAttachFileVO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.SSLContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocServiceImple implements DocService{

    private final DocDAO docDAO;
    private final DocAttachFileDAO docAttachFileDAO;
    private final DocHosAttachFileDAO docHosAttachFileDAO;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void DocSignUp(DocVO vo) {
        docDAO.doctorSignUp(vo);



        if(vo.getAttachList() ==null || vo.getAttachList().size() ==0){
            return;
        }
        log.info("--------AttachList------------");
        log.info(String.valueOf(vo.getAttachList().size()));
        log.info("-----------------------------");
        vo.getAttachList().forEach(attach -> {
            attach.setDocNo(vo.getDocNo());
            log.info("upload....... 들어가는 중");
            docAttachFileDAO.insert(attach);
        });

        if(vo.getHosattachList() == null || vo.getHosattachList().size() == 0){
            return;
        }
        log.info("--------HosAttachList------------");
        log.info(String.valueOf(vo.getHosattachList().size()));
        log.info("----------------------------------");
        vo.getHosattachList().forEach(attach -> {
            attach.setDocNo(vo.getDocNo());
            log.info("hosUpload....... 들어가는 중");
            docHosAttachFileDAO.insert(attach);
        });


    }

    @Override
    public List<DocAttachFileVO> getAttachList(Long docNo) {
        return docAttachFileDAO.findByBno(docNo);
    }

    @Override
    public DocVO docLogin(DocVO vo) {
        return docDAO.docLogin(vo);
    }

    @Override
    public List<DocHosAttachFileVO> getHosAttachList(Long docNo) {
        return docHosAttachFileDAO.findByBno(docNo);
    }

    @Override
    public void docLogout(HttpSession session) { docDAO.docLogout(session); }

}
