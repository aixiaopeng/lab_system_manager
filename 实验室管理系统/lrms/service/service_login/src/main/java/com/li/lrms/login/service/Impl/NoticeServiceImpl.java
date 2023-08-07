package com.li.lrms.login.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.lrms.login.entity.Notice;
import com.li.lrms.login.mapper.LoginMapper;
import com.li.lrms.login.mapper.NoticeMapper;
import com.li.lrms.login.service.LoginService;
import com.li.lrms.login.service.NoticeService;
import com.li.lrms.model.user.User;
import org.springframework.stereotype.Service;

@Service

public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
}
