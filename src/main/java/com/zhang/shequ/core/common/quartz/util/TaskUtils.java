package com.zhang.shequ.core.common.quartz.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.zhang.shequ.core.entity.ScheduleJob;
import com.zhang.shequ.utils.util.SpringContextHolder;

public class TaskUtils {

    public final static Logger log = Logger.getLogger(TaskUtils.class);

    /**
     * 通过反射调用scheduleJob中定义的方法
     * 
     * @param scheduleJob
     */
    public static void invokMethod(ScheduleJob scheduleJob) {
        Object object = null;
        Class<?> clazz = null;
        if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
            object = SpringContextHolder.getBean(scheduleJob.getSpringId());
        } else if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
            try {
                clazz = Class.forName(scheduleJob.getBeanClass());
                object = SpringContextHolder.getBean(clazz);
            } catch (ClassNotFoundException e) {
                log.error(e.getMessage(), e);
            }
        }
        if (object == null) {
            return;
        }
        clazz = object.getClass();
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(scheduleJob.getMethodName());
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        } catch (SecurityException e) {
            log.error(e.getMessage(), e);
        }
        if (method != null) {
            try {
                method.invoke(object);
            } catch (IllegalAccessException e) {
                log.error(e.getMessage(), e);
            } catch (IllegalArgumentException e) {
                log.error(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
