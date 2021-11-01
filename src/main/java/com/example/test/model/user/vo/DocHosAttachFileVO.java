package com.example.test.model.user.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class DocHosAttachFileVO {

    private String hosUuid;
    private String hosUploadPath;
    private String hosFileName;
    private boolean hosImage;
    private Long docNo;


}
