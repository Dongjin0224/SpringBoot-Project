package com.example.test.model.admin.dao;

import com.example.test.mappers.AdminMapper;
import com.example.test.model.notice.vo.NoticeVO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import com.example.test.model.volunteer.vo.VolunteerBoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class AdminDAO {

    public final AdminMapper adminMapper;

    /* 회원 관리 */
    public List<UserVO> readUser(){ return adminMapper.readUser(); }
    public List<DocVO> readDoc(){ return adminMapper.readDoc(); }
    public int totalUser(){ return adminMapper.totalUser(); }
    public int totalDoc(){ return adminMapper.totalDoc(); }
    public boolean updateUser(UserVO user){ return adminMapper.updateUser(user) == 1; }
    public boolean updateDoc(DocVO doc){ return adminMapper.updateDoc(doc) == 1; }

    /*공지사항 등록*/
    public void insertNotice(NoticeVO noticeVO) { adminMapper.insertNotice(noticeVO); }

    /*봉사공고 등록*/
    public void insertVolunteer(VolunteerBoardVO volunteerBoardVO) { adminMapper.insertVolunteer(volunteerBoardVO);}
}
