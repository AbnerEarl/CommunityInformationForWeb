package com.zhang.shequ.core.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.zhang.shequ.core.entity.Menu;
import com.zhang.shequ.core.model.dto.MenuDto;
import com.zhang.shequ.core.model.menu.Jstree;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-10
 */
public interface MenuService extends IService<Menu> {
	
	List<Menu> listMenuByUserId(Integer userId);

	List<Menu> getMenuList(Integer parentId);

	List<Jstree> getRoleMenu(Integer roleId);

	int updateUserMenu(MenuDto menu);

}
