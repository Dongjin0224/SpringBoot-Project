package com.example.test.services;

import com.example.test.model.mainBoard.vo.AnswerVO;
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
    public int checkId(DocVO vo) {
        int result =docDAO.checkId(vo);
        return result;
    }

    @Override
    public void docLogout(HttpSession session) { docDAO.docLogout(session); }

    @Override
    public DocVO findId(DocVO vo) {
        return docDAO.findId(vo);
    }

    @Override
    public DocVO findPw(DocVO vo) {
        return docDAO.findPw(vo);
    }

    @Override
    public void updatePassword(DocVO vo) {

    }


    /* 좋아요 */
    @Override
    public void like1(Long docNo) {
        docDAO.like1(docNo);
    }

    @Override
    public void like2(Long docNo, Long userNo, Long reQnaNo) {
        docDAO.like2(docNo, userNo, reQnaNo);
    }

    @Override
    public int getLike(Long docNo, Long userNo, Long reQnaNo) {
        return docDAO.getLike(docNo, userNo, reQnaNo);
    }

    @Override
    public AnswerVO viewReport(Long reQnaNo) {
        return docDAO.viewReport(reQnaNo);
    }

    @Override
    public void report1(Long docNo) {
        docDAO.report1(docNo);
    }

    @Override
    public void report2(Long reQnaNo, Long docNo, Long userNo, String reportType, String reportContent) {
        docDAO.report2(reQnaNo, docNo, userNo, reportType, reportContent);
    }

    @Override
    public int getReport(Long docNo, Long userNo, Long reQnaNo) {
        return docDAO.getReport(docNo, userNo, reQnaNo);
    }

}
