package com.zhang.shequ.core.common.factory;

import org.springframework.beans.BeanUtils;

import com.zhang.shequ.core.entity.User;
import com.zhang.shequ.core.model.dto.UserDto;

/**
 * 用户创建工厂
 */
public class UserFactory {
	
    public static User createUser(UserDto userDto){
        if(userDto == null){
            return null;
        }else{
            User user = new User();
            BeanUtils.copyProperties(userDto,user);
            return user;
        }
    }

}
