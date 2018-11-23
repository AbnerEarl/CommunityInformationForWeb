package com.zhang.shequ.base.exception;

/**
 * 抽象接口
 */
public interface ServiceExceptionEnum {

    /**
     * 获取异常编码
     */
    Integer getCode();

    /**
     * 获取异常信息
     */
    String getMessage();
    
}
