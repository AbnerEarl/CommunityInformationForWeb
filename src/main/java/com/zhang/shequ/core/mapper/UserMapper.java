package com.zhang.shequ.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhang.shequ.core.entity.User;
import com.zhang.shequ.core.model.dto.UserDto;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-10
 */
public interface UserMapper extends BaseMapper<User> {

	UserDto getUserDtoByUsername(@Param("username") String username);

	List<UserDto> getUserList(Page<UserDto> page, @Param("condition") String condition, @Param("roleId") Integer roleId, @Param("status") Integer status);

}
