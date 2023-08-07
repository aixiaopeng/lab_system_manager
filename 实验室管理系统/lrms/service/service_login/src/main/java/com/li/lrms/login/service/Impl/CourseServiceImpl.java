package com.li.lrms.login.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.lrms.login.entity.Course;
import com.li.lrms.login.entity.Notice;
import com.li.lrms.login.mapper.CourseMapper;
import com.li.lrms.login.mapper.NoticeMapper;
import com.li.lrms.login.service.CourseService;
import com.li.lrms.login.service.NoticeService;
import org.springframework.stereotype.Service;

@Service

public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

}
