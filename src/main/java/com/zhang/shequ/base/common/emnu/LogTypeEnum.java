package com.zhang.shequ.base.common.emnu;

/**
 * 日志类型
 */
public enum LogTypeEnum {

    LOGIN("登录日志"),
    EXIT("退出日志");

    String message;

    LogTypeEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
