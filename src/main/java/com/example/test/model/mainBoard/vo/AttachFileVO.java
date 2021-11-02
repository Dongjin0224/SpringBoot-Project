package com.example.test.model.mainBoard.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AttachFileVO {

    private String uuid;
    private String uploadPath;
    private String fileName;
    private boolean image;
    private Long qnaNo;
}
