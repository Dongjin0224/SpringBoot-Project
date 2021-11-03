package com.example.test.mappers;

import com.example.test.model.mainBoard.vo.AnswerVO;
import com.example.test.model.user.vo.DocVO;
import org.apache.ibatis.annotations.Mapper;

import javax.servlet.http.HttpSession;

@Mapper
public interface DocMapper {

    /*의사회원가입*/
    public void DocSignUp(DocVO vo);
    /*docNo가져오기*/
    public void insertSelectKey_bno(DocVO vo);

    public DocVO docLogin(DocVO vo);

    public int checkId(DocVO vo);

    public void docLogout(HttpSession session);

    public DocVO findId(DocVO vo);

    public DocVO findPw(DocVO vo);

    public void updatePassword(DocVO vo);

    /*좋아요*/
    public void like1(Long docNo);
    public void like2(Long docNo, Long userNo, Long reQnaNo);
    public int getlike(Long docNo, Long userNo, Long reQnaNo);

    /*신고하기*/
    public AnswerVO viewReport(Long reQnaNo);
    public void report1(Long docNo);
    public void report2(Long reQnaNo, Long docNo, Long userNo, String reportType, String reportContent);
    public int getReport(Long docNo, Long userNo, Long reQnaNo);
}
