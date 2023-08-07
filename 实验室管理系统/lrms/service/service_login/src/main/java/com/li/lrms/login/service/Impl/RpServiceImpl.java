package com.li.lrms.login.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.lrms.login.entity.Lm;
import com.li.lrms.login.entity.Rp;
import com.li.lrms.login.mapper.LmMapper;
import com.li.lrms.login.mapper.RpMapper;
import com.li.lrms.login.service.LmService;
import com.li.lrms.login.service.RpService;
import org.springframework.stereotype.Service;

@Service

public class RpServiceImpl extends ServiceImpl<RpMapper, Rp> implements RpService {
}
