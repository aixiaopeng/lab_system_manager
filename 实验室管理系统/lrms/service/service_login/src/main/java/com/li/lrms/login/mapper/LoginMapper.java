package com.li.lrms.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.lrms.model.user.User;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

@Mapper
public interface LoginMapper extends BaseMapper<User> {


}
