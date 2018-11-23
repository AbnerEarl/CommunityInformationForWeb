package com.zhang.shequ.base.exception;

/**
 * 封装的异常对象
 */
public class ResourceException extends RuntimeException {

	private static final long serialVersionUID = 3569127086593812453L;

	private Integer code;

    private String message;

    public ResourceException(ServiceExceptionEnum serviceExceptionEnum) {
        this.code = serviceExceptionEnum.getCode();
        this.message = serviceExceptionEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}