package com.zhang.shequ.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhang.shequ.base.model.response.ResponseEntity;
import com.zhang.shequ.core.entity.Role;
import com.zhang.shequ.core.entity.RoleMenu;
import com.zhang.shequ.core.mapper.RoleMapper;
import com.zhang.shequ.core.mapper.RoleMenuMapper;
import com.zhang.shequ.core.service.RoleService;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-10
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private RoleMenuMapper roleMenuMapper;
	
	@Override
	public List<Role> getRoleListByParam(Role role) {
		Wrapper<Role> wrapper = new EntityWrapper<Role>();
		wrapper.eq("status", 0);
		return roleMapper.selectList(wrapper);
	}

	@Override
	public List<Role> getRoleListByPage(Page<Role> page, Role role) {
		Wrapper<Role> wrapper = new EntityWrapper<Role>();
		wrapper.where((role.getStatus() != null && role.getStatus() != -1), "status = {0}", role.getStatus());
		wrapper.where((role.getName() != null && !role.getName().equals("")), "name like CONCAT('%',{0},'%')", role.getName());
		return roleMapper.selectPage(page, wrapper);
	}

	@Override
	public ResponseEntity<?> updateMenuToRole(Integer roleId, String menuids) {
        try {
            int result = 0;
            // 先清楚该角色的所有权限
            Map<String, Object> columnMap = new HashMap<String, Object>();
            columnMap.put("role_id", roleId);
			roleMenuMapper.deleteByMap(columnMap);
            // 再插入该角色的所选中的权限
            String menuidsArray[] = menuids.split(",");
            for (int i = 0; i < menuidsArray.length; i++) {
            	if (menuidsArray[i] != null && menuidsArray[i] != "") {
            		RoleMenu roleMenu = new RoleMenu();
            		roleMenu.setRoleId(roleId);
            		roleMenu.setMenuId(Integer.parseInt(menuidsArray[i]));
            		int flag = roleMenuMapper.insert(roleMenu);
            		if (flag > 0) {
            			result++;
            		}
            	}
            }
            if (result > 0) {
            	return ResponseEntity.createSuccessMsg("角色权限修改成功");
            }
        } catch (Exception e) {
        	log.info(e.getMessage(),e);
        }
		return ResponseEntity.createFailedMsg("角色权限修改失败");
	}
	
}
