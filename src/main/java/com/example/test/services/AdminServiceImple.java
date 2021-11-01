package com.example.test.services;

import com.example.test.model.admin.dao.AdminDAO;
import com.example.test.model.notice.vo.NoticeVO;
import com.example.test.model.user.vo.DocVO;
import com.example.test.model.user.vo.UserVO;
import com.example.test.model.volunteer.dao.VolAttachFileDAO;
import com.example.test.model.volunteer.vo.VolAttachFileVO;
import com.example.test.model.volunteer.vo.VolunteerBoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImple implements AdminService {

    private final AdminDAO adminDAO;
    private final VolAttachFileDAO volAttachFileDAO;

    /* 회원 관리 */
    @Override
    public List<UserVO> readUser() {
        return adminDAO.readUser();
    }

    @Override
    public List<DocVO> readDoc() {
        return adminDAO.readDoc();
    }

    @Override
    public int totalUser() {
        return adminDAO.totalUser();
    }

    @Override
    public int totalDoc() {
        return adminDAO.totalDoc();
    }

    @Override
    public boolean updateUser(UserVO user) {
        return adminDAO.updateUser(user);
    }

    @Override
    public boolean updateDoc(DocVO doc) {
        return adminDAO.updateDoc(doc);
    }

    /* 공지사항 등록 */
    @Override
    public void insertNotice(NoticeVO noticeVO) { adminDAO.insertNotice(noticeVO); }

    /* 봉사공고 등록 */
    @Override
    public void insertVolunteer(VolunteerBoardVO volunteerBoardVO) {

        adminDAO.insertVolunteer(volunteerBoardVO);

        if(volunteerBoardVO.getAttachList() ==null || volunteerBoardVO.getAttachList().size() ==0){
            return;
        }
        log.info("--------AttachList------------");
        log.info(String.valueOf(volunteerBoardVO.getAttachList().size()));
        log.info("-----------------------------");
        volunteerBoardVO.getAttachList().forEach(attach -> {
            attach.setVolunteerBoardNo(volunteerBoardVO.getVolunteerBoardNo());
            log.info("upload....... 들어가는 중");
            volAttachFileDAO.insert(attach);
        });
    }

    @Override
    public List<DocVO> report() { return adminDAO.report(); }

    @Override
    public List<VolAttachFileVO> getAttachList(Long volunteerBoardNo) {
        return volAttachFileDAO.findByBno(volunteerBoardNo);
    }

}