package com.example.test.mappers;

import com.example.test.model.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /*회원가입*/
    public void memberSignUp(UserVO vo);

    public UserVO userLogin(UserVO vo);

    public int checkId(UserVO vo);

    public UserVO findId(UserVO vo);

    public UserVO findPw(UserVO vo);

    public void updatePassword(UserVO vo);

}
