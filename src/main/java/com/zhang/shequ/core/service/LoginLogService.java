package com.zhang.shequ.core.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.zhang.shequ.core.entity.LoginLog;

/**
 * <p>
 * 登录记录表 服务类
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-10
 */
public interface LoginLogService extends IService<LoginLog> {

	List<LoginLog> getLoginLogListByPage(Page<LoginLog> page, LoginLog loginLog);

}
