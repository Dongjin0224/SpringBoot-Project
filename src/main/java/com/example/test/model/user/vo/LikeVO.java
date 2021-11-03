package com.example.test.model.user.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LikeVO {

   private Long likeNo;
   private Long docNo;
   private Long userNo;
   private Long reQnaNo;
}
