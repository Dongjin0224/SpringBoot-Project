package com.example.test.services;

import com.example.test.model.notice.vo.NoticeVO;
import com.example.test.model.user.vo.DocAttachFileVO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import com.example.test.model.volunteer.vo.VolAttachFileVO;
import com.example.test.model.volunteer.vo.VolunteerBoardVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    /* 회원 관리 */
    public List<UserVO> readUser();
    public List<DocVO> readDoc();
    public int totalUser();
    public int totalDoc();
    public boolean updateUser(UserVO user);
    public boolean updateDoc(DocVO doc);

    /* 공지사항 등록 */
    public void insertNotice(NoticeVO noticeVO);

    /* 봉사공고 등록 */
    public void insertVolunteer(VolunteerBoardVO volunteerBoardVO);

    /* 신고 관리 */
    public List<DocVO> report();

    public List<VolAttachFileVO> getAttachList(Long volunteerBoardNo);
}
