package com.zhang.shequ.core.common.log.factory;

import java.util.Date;

import com.zhang.shequ.base.common.emnu.LogTypeEnum;
import com.zhang.shequ.core.entity.LoginLog;

/**
 * 日志对象创建工厂
 */
public class LogFactory {

    /**
     * 创建登录日志
     */
    public static LoginLog createLoginLog(LogTypeEnum logTypeEnum, Integer userId, String ip) {
        LoginLog loginLog = new LoginLog();
        loginLog.setLogname(logTypeEnum.getMessage());
        loginLog.setUserId(userId);
        loginLog.setCreateTime(new Date());
        loginLog.setIp(ip);
        return loginLog;
    }
    
}