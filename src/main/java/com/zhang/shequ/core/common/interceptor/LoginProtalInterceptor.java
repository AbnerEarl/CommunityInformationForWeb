package com.zhang.shequ.core.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhang.shequ.base.common.constant.Constants;
import com.zhang.shequ.core.model.dto.UserDto;
import com.zhang.shequ.core.service.UserService;
import com.zhang.shequ.utils.util.CookieUtil;
import com.zhang.shequ.utils.util.SpringContextHolder;

public class LoginProtalInterceptor implements HandlerInterceptor {
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getId() != null) {
        	UserDto userDto = (UserDto) session.getAttribute(Constants.USER_SESSION_KEY);
			if(userDto != null){
				return true;
			}
        } 
    	String username = CookieUtil.getCookieValue(request, Constants.USER_COOKIE_KEY);
    	if(StringUtils.isNotBlank(username)){
    		UserService userService = SpringContextHolder.getBean(UserService.class);
    		UserDto user = userService.getByUsername(username);
    		if(null != user){
    			user.setPassword(null);
    			session.setAttribute(Constants.USER_SESSION_KEY, user);
				session.setMaxInactiveInterval(Constants.USER_SESSION_EXPIRE);
    			return true;
    		}
    	}
    	response.sendRedirect(request.getContextPath() + "/protal/login");
        return false;
    }

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {
	}
    
}