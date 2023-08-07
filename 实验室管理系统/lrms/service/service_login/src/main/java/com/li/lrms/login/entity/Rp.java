package com.li.lrms.login.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
@TableName("rp")
public class Rp {
    private Integer uuid;
    private String tname;
    private String rname;
    private String rinfo;
}
