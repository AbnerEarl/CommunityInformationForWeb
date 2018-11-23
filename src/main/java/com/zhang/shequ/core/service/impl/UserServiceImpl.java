package com.zhang.shequ.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhang.shequ.base.common.constant.Constants;
import com.zhang.shequ.base.common.emnu.StatusEnum;
import com.zhang.shequ.base.model.response.ResponseEntity;
import com.zhang.shequ.core.entity.User;
import com.zhang.shequ.core.mapper.UserMapper;
import com.zhang.shequ.core.model.dto.UserDto;
import com.zhang.shequ.core.service.UserService;
import com.zhang.shequ.utils.util.MD5Util;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserMapper userMapper;

	@Override
	public ResponseEntity<UserDto> userLogin(String username, String password) {
        try {
        	UserDto user = userMapper.getUserDtoByUsername(username);
    		if (null != user) {
    			if (MD5Util.getSignatureByMD5(password).equals(user.getPassword())) {
					if(user.getStatus().equals(StatusEnum.DISABLE.getCode())){
						return ResponseEntity.createFailedMsg("帐号已冻结");
					}else {
						user.setPassword(null);
						return ResponseEntity.createSuccess("登陆成功", user);
					}
    			} 
    		} 
    		return ResponseEntity.createFailedMsg("帐号或密码错误");
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
            return ResponseEntity.createExceptionMsg("用户登陆异常");
        }
	}
	
	@Override
	public ResponseEntity<?> updatePassword(String oldpwd, String newpwd, Integer userId) {
        try {
            User info = userMapper.selectById(userId);
            if(null != info) {
                if(info.getPassword().equals(MD5Util.getSignatureByMD5(oldpwd))) {
                	info.setPassword(MD5Util.getSignatureByMD5(newpwd));
                    int count = userMapper.updateById(info);
                    if(count > 0){
                    	return ResponseEntity.createSuccessMsg("密码修改成功");
                    }
                }else {
                    return ResponseEntity.createFailedMsg("原密码错误");
                }
            }
        } catch (Exception e) {
        	log.info(e.getMessage(),e);
        }
        return ResponseEntity.createExceptionMsg("密码修改失败");
	}

	@Override
	public UserDto getByUsername(String username) {
		return userMapper.getUserDtoByUsername(username);
	}

	@Override
	public List<UserDto> getUserList(Page<UserDto> page, String condition, Integer roleId, Integer status) {
		return userMapper.getUserList(page, condition, roleId, status);
	}

	@Override
	public ResponseEntity<?> resetPassword(String ids) {
		String password = MD5Util.getSignatureByMD5(Constants.DEAFAULT_PASSWORD);
		String[] split = ids.split(",");
		for (String string : split) {
			User user = new User();
			user.setId(Integer.parseInt(string));
			user.setPassword(password);
			userMapper.updateById(user);
		}
		return ResponseEntity.createSuccessMsg("密码重置成功");
	}

}