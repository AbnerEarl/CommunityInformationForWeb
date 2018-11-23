package com.zhang.shequ.modular.home;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zhang.shequ.base.common.constant.Constants;
import com.zhang.shequ.base.controller.BaseController;
import com.zhang.shequ.base.model.response.ResponseEntity;
import com.zhang.shequ.core.common.log.LogManager;
import com.zhang.shequ.core.common.log.factory.LogTaskFactory;
import com.zhang.shequ.core.entity.User;
import com.zhang.shequ.core.model.dto.UserDto;
import com.zhang.shequ.core.service.UserService;
import com.zhang.shequ.utils.util.IPUtil;
import com.zhang.shequ.utils.util.ToolUtil;

@RestController
public class LoginController extends BaseController {
	
	@Resource
	private UserService userService;
	
    @RequestMapping(value = "/")
    public ModelAndView main() {
        return new ModelAndView("redirect:/login");
    }
    
    @RequestMapping(value = "/login")
    public ModelAndView login() {
        return new ModelAndView("home/login");
    }
    
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public ResponseEntity<?> userLogin() {
        String username = getPara("username").trim();
        String password = getPara("password").trim();
        Boolean remember = Boolean.parseBoolean(getPara("remember"));
        String kaptcha = getPara("kaptcha").trim();
        String code = (String) getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(ToolUtil.isEmpty(kaptcha) || !kaptcha.equalsIgnoreCase(code)){
        	return ResponseEntity.createFailedMsg("验证码错误");
        } 
		ResponseEntity<UserDto> result = userService.userLogin(username, password);
		if (result.getStatus() == 0) {
			getSession().setAttribute(Constants.USER_SESSION_KEY, result.getData());
			getSession().setMaxInactiveInterval(Constants.USER_SESSION_EXPIRE);
			if(remember){
				setCookieByName(Constants.USER_COOKIE_KEY, result.getData().getUsername(), Constants.USER_COOKIE_EXPIRE);
			}
			LogManager.me().executeLog(LogTaskFactory.loginLog(result.getData().getId(), IPUtil.getIpAddr(getRequest())));
		}
		return result;
    }
    
    @RequestMapping(value = "/home")
    public ModelAndView home() {
        ModelAndView mView = new ModelAndView();
        mView.setViewName("home/home");
        User user = userService.selectById(getSessionUserId());
        mView.addObject("nickname", user.getName());
        mView.addObject("avatar", user.getAvatar());
        return mView;
    }
    
    @RequestMapping(value = "/index")
    public ModelAndView index() {
        return new ModelAndView("home/index");
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
    	Integer userId = getSessionUserId();
    	getSession().removeAttribute(Constants.USER_SESSION_KEY);
    	getSession().invalidate();
    	deleteCookieByName(Constants.USER_COOKIE_KEY);
		LogManager.me().executeLog(LogTaskFactory.exitLog(userId, IPUtil.getIpAddr(getRequest())));
        return new ModelAndView("redirect:/login");
    }
	
}