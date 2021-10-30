package com.example.test.mappers;

import com.example.test.model.user.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {

    public void updateUser(UserVO vo);

}
