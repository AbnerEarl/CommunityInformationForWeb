package com.zhang.shequ.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhang.shequ.core.entity.LoginLog;
import com.zhang.shequ.core.mapper.LoginLogMapper;
import com.zhang.shequ.core.service.LoginLogService;

/**
 * <p>
 * 登录记录表 服务实现类
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-10
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {
	
	@Resource
	private LoginLogMapper loginLogMapper;

	@Override
	public List<LoginLog> getLoginLogListByPage(Page<LoginLog> page, LoginLog loginLog) {
		Wrapper<LoginLog> wrapper = new EntityWrapper<LoginLog>();
		wrapper.eq("user_id", loginLog.getUserId());
		wrapper.eq(loginLog.getLogname() != null && !loginLog.getLogname().equals(""), "logname", loginLog.getLogname());
		return loginLogMapper.selectPage(page, wrapper);
	}

}
