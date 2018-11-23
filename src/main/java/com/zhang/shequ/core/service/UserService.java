package com.zhang.shequ.core.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.zhang.shequ.base.model.response.ResponseEntity;
import com.zhang.shequ.core.entity.User;
import com.zhang.shequ.core.model.dto.UserDto;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-10
 */
public interface UserService extends IService<User> {

	ResponseEntity<UserDto> userLogin(String username, String password);
	
	ResponseEntity<?> updatePassword(String oldpwd, String newpwd,Integer userId);

	UserDto getByUsername(String username);

	List<UserDto> getUserList(Page<UserDto> page, String condition, Integer roleId, Integer status);

	ResponseEntity<?> resetPassword(String ids);

}
