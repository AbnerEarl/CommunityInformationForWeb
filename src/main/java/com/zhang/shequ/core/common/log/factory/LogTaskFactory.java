package com.zhang.shequ.core.common.log.factory;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhang.shequ.base.common.emnu.LogTypeEnum;
import com.zhang.shequ.core.common.log.LogManager;
import com.zhang.shequ.core.entity.LoginLog;
import com.zhang.shequ.core.mapper.LoginLogMapper;
import com.zhang.shequ.utils.util.SpringContextHolder;

/**
 * 日志操作任务创建工厂
 */
public class LogTaskFactory {

    private static Logger logger = LoggerFactory.getLogger(LogManager.class);
    
    private static LoginLogMapper loginLogMapper = SpringContextHolder.getBean(LoginLogMapper.class);
    
    public static TimerTask loginLog(final Integer userId, final String ip) {
        return new TimerTask() {
        	
            @Override
            public void run() {
                try {
                    LoginLog loginLog = LogFactory.createLoginLog(LogTypeEnum.LOGIN, userId, ip);
                    loginLogMapper.insert(loginLog);
                } catch (Exception e) {
                    logger.error("创建登录日志异常!", e);
                }
            }
        };
    }

    public static TimerTask exitLog(final Integer userId, final String ip) {
        return new TimerTask() {
        	
            @Override
            public void run() {
                LoginLog loginLog = LogFactory.createLoginLog(LogTypeEnum.EXIT, userId, ip);
                try {
                    loginLogMapper.insert(loginLog);
                } catch (Exception e) {
                    logger.error("创建退出日志异常!", e);
                }
            }
        };
    }

}