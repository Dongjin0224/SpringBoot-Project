package com.example.test.payment.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GetTokenVO {
    private String access_token;
    private long now;
    private long expired_at;

}
