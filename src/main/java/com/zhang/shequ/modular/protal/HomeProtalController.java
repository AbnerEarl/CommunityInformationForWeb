package com.zhang.shequ.modular.protal;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhang.shequ.base.common.constant.Constants;
import com.zhang.shequ.base.controller.BaseController;
import com.zhang.shequ.base.model.response.ResponseEntity;
import com.zhang.shequ.core.common.log.LogManager;
import com.zhang.shequ.core.common.log.factory.LogTaskFactory;
import com.zhang.shequ.core.entity.User;
import com.zhang.shequ.core.model.dto.UserDto;
import com.zhang.shequ.core.service.UserService;
import com.zhang.shequ.utils.util.IPUtil;
import com.zhang.shequ.utils.util.MD5Util;

@RestController
@RequestMapping(value = "/protal")
public class HomeProtalController extends BaseController {
	
	@Resource 
	private UserService userService;
	
    @RequestMapping(value = "/login")
    public ModelAndView login() { 
        return new ModelAndView("protal/home/login");
    }
    
    @RequestMapping(value = "/password")
    public ModelAndView password() { 
        return new ModelAndView("protal/home/password");
    }
    
    @RequestMapping(value = "/passwordNext")
    public ModelAndView passwordNext(Integer id) { 
    	ModelAndView model = new ModelAndView("protal/home/passwordNext");
    	return model.addObject("userId", id);
    }
    
    @RequestMapping(value = "/register")
    public ModelAndView register() { 
    	return new ModelAndView("protal/home/register");
    }
    
    @RequestMapping(value = "/registerNext")
    public ModelAndView registerNext(String phone) { 
    	ModelAndView model = new ModelAndView("protal/home/registerNext");
    	return model.addObject("phone", phone);
    }
    
    @RequestMapping(value = "/index")
    public ModelAndView index() {
        return new ModelAndView("protal/home/index");
    }
    
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public ResponseEntity<?> userLogin() {
        String username = getPara("username").trim();
        String password = getPara("password").trim();
		ResponseEntity<UserDto> result = userService.userLogin(username, password);
		if (result.getStatus() == 0) {
			getSession().setAttribute(Constants.USER_SESSION_KEY, result.getData());
			getSession().setMaxInactiveInterval(Constants.USER_SESSION_EXPIRE);
			setCookieByName(Constants.USER_COOKIE_KEY, result.getData().getUsername(), Constants.USER_COOKIE_EXPIRE);
			LogManager.me().executeLog(LogTaskFactory.loginLog(result.getData().getId(), IPUtil.getIpAddr(getRequest())));
		}
		return result;
    }
    
    @RequestMapping(value = "/getPhoneYzm", method = RequestMethod.POST)
    public ResponseEntity<?> getPhoneYzm(String phone){
    	Wrapper<User> wrapper = new EntityWrapper<>();
    	wrapper.eq("phone", phone);
    	User user = userService.selectOne(wrapper);
		if(user != null) {
			return ResponseEntity.createSuccessMsg("验证码已发送至您的手机，请及时验证");
		}else {
			return ResponseEntity.createFailedMsg("手机号不存在");
		}
    }
    
    @RequestMapping(value = "/getPhoneRegisterYzm", method = RequestMethod.POST)
    public ResponseEntity<?> getPhoneRegisterYzm(String phone){
    	Wrapper<User> wrapper = new EntityWrapper<>();
    	wrapper.eq("phone", phone);
    	User user = userService.selectOne(wrapper);
		if(user != null) {
			return ResponseEntity.createFailedMsg("手机号已注册");
		}else {
			return ResponseEntity.createSuccessMsg("验证码已发送至您的手机，请及时验证");
		}
    }
    
    @RequestMapping(value = "/resetPassOne", method = RequestMethod.POST)
    public ResponseEntity<?> resetPassOne(String phone, String yzmCode){
    	if(!yzmCode.equals(Constants.DEAFAULT_MESSAGE_CODE)) {
    		return ResponseEntity.createFailedMsg("验证码错误");
    	}else {
    		Wrapper<User> wrapper = new EntityWrapper<>();
    		wrapper.eq("phone", phone);
    		User user = userService.selectOne(wrapper);
    		if(user != null) {
    			return ResponseEntity.createSuccess(user);
    		}else {
    			return ResponseEntity.createFailedMsg("手机号不存在");
    		}
    	}
    }
    
    @RequestMapping(value = "/resetPassFilish", method = RequestMethod.POST)
    public ResponseEntity<?> resetPassFilish(User user){
    	user.setPassword(MD5Util.getSignatureByMD5(user.getPassword()));
    	boolean flag = userService.updateById(user);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("密码重置成功");
    	}else {
    		return ResponseEntity.createFailedMsg("密码重置失败");
    	}
    }
    
    @RequestMapping(value = "/registerOne", method = RequestMethod.POST)
    public ResponseEntity<?> registerOne(String phone, String yzmCode){
    	if(!yzmCode.equals(Constants.DEAFAULT_MESSAGE_CODE)) {
    		return ResponseEntity.createFailedMsg("验证码错误");
    	}else {
    		Wrapper<User> wrapper = new EntityWrapper<>();
    		wrapper.eq("phone", phone);
    		User user = userService.selectOne(wrapper);
    		if(user != null) {
    			return ResponseEntity.createFailedMsg("手机号已存在");
    		}else {
    			return ResponseEntity.createSuccess(phone);
    		}
    	}
    }
    
    @RequestMapping(value = "/registerFilish", method = RequestMethod.POST)
    public ResponseEntity<?> registerFilish(User user){
		Wrapper<User> wrapper = new EntityWrapper<>();
		wrapper.eq("username", user.getUsername());
		int count = userService.selectCount(wrapper);
		if(count > 0) {
			return ResponseEntity.createFailedMsg("用户名已存在");
		}
    	user.setPassword(MD5Util.getSignatureByMD5(user.getPassword()));
    	user.setCreateTime(new Date());
    	user.setAvatar(Constants.DEAFAULT_AVATAR);
    	user.setStatus(2);
    	user.setRoleId(Constants.RESIDENT_ID);
    	boolean flag = userService.insert(user);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("注册成功");
    	}else {
    		return ResponseEntity.createFailedMsg("注册失败");
    	}
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
    	Integer userId = getSessionUserId();
    	getSession().removeAttribute(Constants.USER_SESSION_KEY);
    	getSession().invalidate();
    	deleteCookieByName(Constants.USER_COOKIE_KEY);
		LogManager.me().executeLog(LogTaskFactory.exitLog(userId, IPUtil.getIpAddr(getRequest())));
        return new ModelAndView("redirect:./login");
    }
	
}