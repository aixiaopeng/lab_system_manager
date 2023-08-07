package com.li.lrms.login.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sc")
public class SC{
    private Integer uuid;
    private Integer cid;
    private String cname;
    private Integer lid;
    private String lname;
    private String uid;
    private String uname;
    private String cdate;
    private String ctype;
}
