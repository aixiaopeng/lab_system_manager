package com.li.lrms.login.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
@TableName("lm")
public class Lm {
    private Integer uuid;
    private String lname;
    private String mname;
    private String minfo;
    private Integer mcount;
}
