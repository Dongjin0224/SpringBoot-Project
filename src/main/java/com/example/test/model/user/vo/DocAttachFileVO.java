package com.example.test.model.user.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class DocAttachFileVO {

    private String uuid;
    private String uploadPath;
    private String fileName;
    private boolean image;
    private Long docNo;



}
