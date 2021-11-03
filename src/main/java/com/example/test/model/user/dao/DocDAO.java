package com.example.test.model.user.dao;

import com.example.test.mappers.DocMapper;
import com.example.test.model.mainBoard.vo.AnswerVO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;

@Repository
@Slf4j
@RequiredArgsConstructor
public class DocDAO {

    private final DocMapper docMapper;
    /*의사 회원가입*/
    public void doctorSignUp(DocVO vo){
        docMapper.insertSelectKey_bno(vo);
    }

    /*의사 로그인*/
    public DocVO docLogin(DocVO vo){ return docMapper.docLogin(vo); }

    /*회원중복체크*/
    public int checkId(DocVO vo){
        return docMapper.checkId(vo);
    }

    public void docLogout(HttpSession session) { docMapper.docLogout(session); }

    public DocVO findId(DocVO vo){
        return docMapper.findId(vo);
    }
    public DocVO findPw(DocVO vo){
        return docMapper.findPw(vo);
    }

    public void updatePassword(DocVO vo){
        docMapper.updatePassword(vo);
    }

    /* 좋아요 */
    public void like1(Long docNo) { docMapper.like1(docNo); }
    public void like2(Long docNo, Long userNo, Long reQnaNo) { docMapper.like2(docNo, userNo, reQnaNo);}
    public int getLike(Long docNo, Long userNo, Long reQnaNo) { return docMapper.getlike(docNo, userNo, reQnaNo); }

    /* 신고하기 */
    public AnswerVO viewReport(Long reQnaNo) { return docMapper.viewReport(reQnaNo);}
    public void report1(Long docNo) {docMapper.report1(docNo);};
    public void report2(Long reQnaNo, Long docNo, Long userNo, String reportType, String reportContent) {
        docMapper.report2(reQnaNo, docNo, userNo, reportType, reportContent);
    }
    public int getReport(Long docNo, Long userNo, Long reQnaNo){ return docMapper.getReport(docNo, userNo, reQnaNo);}
}