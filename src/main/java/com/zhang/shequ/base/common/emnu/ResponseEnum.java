package com.zhang.shequ.base.common.emnu;

public enum ResponseEnum {
	
	SUCCESS(0,"SUCCESS"),
	FAILED(1,"FAILED"),
	EXCEPTION(2,"EXCEPTION"),
	ERROR(3,"ERROR"),
	WRONG(4,"WRONG"),
	HANDLE(5,"HANDLE");

    private final int code;
    private final String desc;

    ResponseEnum(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    
    public String getDesc(){
        return desc;
    }

}