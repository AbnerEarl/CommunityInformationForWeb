package com.zhang.shequ.modular.protal;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zhang.shequ.base.common.constant.Constants;
import com.zhang.shequ.base.controller.BaseController;
import com.zhang.shequ.base.model.response.ResponseEntity;
import com.zhang.shequ.core.entity.User;
import com.zhang.shequ.core.model.dto.UserDto;
import com.zhang.shequ.core.service.UserService;
import com.zhang.shequ.utils.util.MD5Util;

@RestController
@RequestMapping(value = "/protal/user")
public class UserProtalController extends BaseController {
	
	@Resource 
	private UserService userService;
	
    @RequestMapping(value = "/user")
    public ModelAndView user() { 
    	Integer userId = getSessionUserId();
    	User user = userService.selectById(userId);
    	ModelAndView model = new ModelAndView("protal/user/user");
    	model.addObject("name", user.getName());
    	model.addObject("status", user.getStatus());
    	model.addObject("avatar", user.getAvatar());
    	model.addObject("address", user.getAddress());
    	return model;
    }
    
    @RequestMapping(value = "/admin")
    public ModelAndView admin() { 
    	Integer userId = getSessionUserId();
    	User user = userService.selectById(userId);
    	ModelAndView model = new ModelAndView("protal/user/user_admin");
    	model.addObject("name", user.getName());
    	model.addObject("avatar", user.getAvatar());
    	model.addObject("sex", user.getSex());
    	model.addObject("username", user.getUsername());
    	model.addObject("email", user.getEmail());
    	model.addObject("idcard", user.getIdcard());
    	model.addObject("address", user.getAddress());
    	return model;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> update(User user){
    	UserDto userDto = getSessionUser();
    	user.setId(userDto.getId());
    	boolean flag = userService.updateById(user);
    	if(flag) {
    		userDto = userService.getByUsername(userDto.getUsername());
    		getSession().setAttribute(Constants.USER_SESSION_KEY, userDto);
    		return ResponseEntity.createSuccessMsg("修改成功");
    	}else {
    		return ResponseEntity.createFailedMsg("修改失败");
    	}
    }
    
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public ResponseEntity<?> updatePassword(String oldPass, String newPass){
    	Integer userId = getSessionUserId();
    	User user = userService.selectById(userId);
    	if(!MD5Util.getSignatureByMD5(oldPass).equals(user.getPassword())) {
    		return ResponseEntity.createFailedMsg("旧密码错误");
    	}
    	user.setPassword(MD5Util.getSignatureByMD5(newPass));
    	boolean flag = userService.updateById(user);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("修改成功");
    	}else {
    		return ResponseEntity.createFailedMsg("修改失败");
    	}
    }
    
    @RequestMapping(value = "/sex")
    public ModelAndView sex() { 
    	return new ModelAndView("protal/user/user_sex");
    }
    
    @RequestMapping(value = "/password")
    public ModelAndView password() { 
    	return new ModelAndView("protal/user/user_password");
    }
    
    @RequestMapping(value = "/email")
    public ModelAndView email() { 
    	return new ModelAndView("protal/user/user_email");
    }
    
    @RequestMapping(value = "/avatar")
    public ModelAndView avatar() { 
    	UserDto userDto = getSessionUser();
    	ModelAndView model = new ModelAndView("protal/user/user_avatar");
    	model.addObject("avatar", userDto.getAvatar());
    	return model;
    }
    
}