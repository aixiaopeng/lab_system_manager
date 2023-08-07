package com.li.lrms.login.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.lrms.login.entity.Course;
import com.li.lrms.login.entity.Lm;
import com.li.lrms.login.mapper.CourseMapper;
import com.li.lrms.login.mapper.LmMapper;
import com.li.lrms.login.service.CourseService;
import com.li.lrms.login.service.LmService;
import org.springframework.stereotype.Service;

@Service

public class LmServiceImpl extends ServiceImpl<LmMapper, Lm> implements LmService {
}
