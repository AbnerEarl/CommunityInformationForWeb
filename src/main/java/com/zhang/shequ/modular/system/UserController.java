package com.zhang.shequ.modular.system;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhang.shequ.base.common.constant.Constants;
import com.zhang.shequ.base.controller.BaseController;
import com.zhang.shequ.base.model.response.ResponseEntity;
import com.zhang.shequ.base.model.response.ResponseJson;
import com.zhang.shequ.core.common.factory.PageFactory;
import com.zhang.shequ.core.common.factory.UserFactory;
import com.zhang.shequ.core.model.dto.UserDto;
import com.zhang.shequ.core.service.UserService;
import com.zhang.shequ.utils.util.MD5Util;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-10
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
    @RequestMapping(value = "/userManage")
    @ResponseBody
    public ModelAndView endUserManage() {
    	UserDto userDto = getSessionUser();
    	ModelAndView modelAndView = new ModelAndView("system/userManage");
    	modelAndView.addObject("roleId", userDto.getRoleId());
        return modelAndView;
    }
	
	@RequestMapping(value = "/getUser")
    @ResponseBody
    public ResponseEntity<UserDto> getUser() {
        UserDto user = getSessionUser();
		UserDto userDto = userService.getByUsername(user.getUsername());
		return ResponseEntity.createSuccess(userDto);
    }
    
    /**
     * 首页中密码修改操作
     */
    @RequestMapping(value = "/updatePassword")
    @ResponseBody
    public ResponseEntity<?> updatePassword(String oldpwd, String newpwd) {
        return userService.updatePassword(oldpwd, newpwd, getSessionUserId());
    }
    
    /**
     * 后台用户管理中查询后台用户列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public ResponseJson<UserDto> list(String condition, Integer roleId, Integer status) {
        Page<UserDto> page = new PageFactory<UserDto>().defaultPage();
    	List<UserDto> userList = userService.getUserList(page, condition, roleId, status);
    	page.setRecords(userList);
        return packForTable(page);
    }

    /**
     * 后台用户管理中新增后台用户信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> add(UserDto user) {
        UserDto theUser = userService.getByUsername(user.getUsername());
    	if(theUser != null){
    		return ResponseEntity.createFailedMsg("用户名已存在");
    	}
    	user.setPassword(MD5Util.getSignatureByMD5(Constants.DEAFAULT_PASSWORD));
    	user.setAvatar(Constants.DEAFAULT_AVATAR);
    	user.setCreateTime(new Date());
        userService.insert(UserFactory.createUser(user));
        return ResponseEntity.createSuccessMsg("新增成功");
    }

    /**
     * 后台用户管理中修改后台用户信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> update(UserDto user) {
        userService.updateById(UserFactory.createUser(user));
        return ResponseEntity.createSuccessMsg("修改成功");
    }
    
    /**
     * 重置密码
     */
    @RequestMapping(value = "/resetPassword")
    @ResponseBody
    public ResponseEntity<?> resetPassword(String ids) {
        return userService.resetPassword(ids);
    }

}

