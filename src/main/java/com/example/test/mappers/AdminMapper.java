package com.example.test.mappers;

import com.example.test.model.notice.vo.NoticeVO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import com.example.test.model.volunteer.vo.VolunteerBoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    /*회원 관리*/
    public List<UserVO> readUser();
    public List<DocVO> readDoc();
    public int totalUser();
    public int totalDoc();
    public int updateUser(UserVO user);
    public int updateDoc(DocVO doc);

    /*공지사항 등록*/
    public void insertNotice(NoticeVO noticeVO);

    /*봉사공고 등록*/
    public void insertVolunteer(VolunteerBoardVO volunteerBoardVO);

    /*신고 관리*/
    public List<DocVO> report();
}
