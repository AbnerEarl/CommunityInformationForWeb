package com.zhang.shequ.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhang.shequ.base.common.constant.Constants;
import com.zhang.shequ.base.model.request.PageRequest;
import com.zhang.shequ.base.model.response.ResponseJson;
import com.zhang.shequ.core.model.dto.UserDto;
import com.zhang.shequ.utils.support.HttpKit;
import com.zhang.shequ.utils.util.CookieUtil;

public class BaseController {
	
    protected UserDto getSessionUser(){
    	return (UserDto) getAttr(Constants.USER_SESSION_KEY);
    }
    
    protected Integer getSessionUserId(){
    	UserDto user = (UserDto) getAttr(Constants.USER_SESSION_KEY);
    	if(null != user){
    		return user.getId();
    	}
    	return null;	
    }
	
    /**
     * 封装为通用的分页封装
     */
    protected <T> ResponseJson<T> packForTable(Page<T> page) {
        return new ResponseJson<T>().setTotal(page.getTotal()).setRows(page.getRecords());
    }
    
    protected <T> Page<T> getPagination(PageRequest pageRequest) {
        Page<T> page =  new Page<T>(pageRequest.getPage(), pageRequest.getRows());
        page.setAsc("asc".equalsIgnoreCase(pageRequest.getOrder()));//升序 降序
        page.setOrderByField(pageRequest.getSort());//排序字段名称
        return page;
    }

    /**
     * 删除cookie
     */
    protected void deleteCookieByName(String cookieName) {
    	CookieUtil.deleteCookie(getRequest(), getResponse(), cookieName);
    }
    
    /**
     * 设置cookie
     */
    protected void setCookieByName(String cookieName, String cookieValue, int cookieMaxage) {
    	CookieUtil.setCookie(getRequest(), getResponse(), cookieName, cookieValue, cookieMaxage);
    }

	protected HttpServletRequest getRequest() {
        return HttpKit.getRequest();
    }

    protected HttpServletResponse getResponse() {
        return HttpKit.getResponse();
    }

    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    protected HttpSession getSession(Boolean flag) {
        return getRequest().getSession(flag);
    }

    protected String getPara(String name) {
        return getRequest().getParameter(name);
    }

    protected void setAttr(String name, Object value) {
        getSession().setAttribute(name, value);
    }
    
    protected Object getAttr(String name) {
    	return getSession().getAttribute(name);
    }
    
}