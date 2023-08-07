package com.li.lrms.login.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.lrms.login.entity.SC;
import com.li.lrms.login.mapper.SCMapper;

import com.li.lrms.login.service.SCService;
import org.springframework.stereotype.Service;

@Service
public class SCServiceImpl extends ServiceImpl<SCMapper, SC> implements SCService {
}
