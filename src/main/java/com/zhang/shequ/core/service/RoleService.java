package com.zhang.shequ.core.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.zhang.shequ.base.model.response.ResponseEntity;
import com.zhang.shequ.core.entity.Role;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-10
 */
public interface RoleService extends IService<Role> {
	
	List<Role> getRoleListByPage(Page<Role> page, Role role);

	ResponseEntity<?> updateMenuToRole(Integer roleId, String addMenu);

	List<Role> getRoleListByParam(Role role);

}
