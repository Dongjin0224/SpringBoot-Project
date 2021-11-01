package com.example.test.model.volunteer.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class VolAttachFileVO {

    private String uuid;
    private String uploadPath;
    private String fileName;
    private boolean image;
    private long volunteerBoardNo;

}
