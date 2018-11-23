package com.zhang.shequ.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhang.shequ.core.entity.Menu;
import com.zhang.shequ.core.mapper.MenuMapper;
import com.zhang.shequ.core.model.dto.MenuDto;
import com.zhang.shequ.core.model.menu.Attr;
import com.zhang.shequ.core.model.menu.Jstree;
import com.zhang.shequ.core.service.MenuService;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-10
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
	
private static final Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);
	
    @Resource
    private MenuMapper menuMapper;

	@Override
	public List<Menu> listMenuByUserId(Integer userId) {
		List<Menu> menulist = new ArrayList<>();
        try {
        	menulist = menuMapper.listMenuByUserId(userId);
		} catch (Exception e) {
			log.info(e.getMessage(),e);
		}
        return menulist;
	}

	@Override
	public List<Menu> getMenuList(Integer parentId) {
    	List<Menu> lists = null;
        try {
        	Wrapper<Menu> wrapper = new EntityWrapper<>(new Menu());
        	if(parentId != null && parentId != -1){
        		wrapper.eq("parent_id", parentId);
        	}
			lists = menuMapper.selectList(wrapper);
		} catch (Exception e) {
			log.info(e.getMessage(),e);
		}
        return lists;
	}

	@Override
	public List<Jstree> getRoleMenu(Integer roleId) {
		List<Jstree> jstreeList = new ArrayList<Jstree>();
        try {
			List<Menu> menuList = menuMapper.getRoleMenu(roleId);
			for (Menu item : menuList) {
			    Jstree jt = new Jstree();
			    Attr attr = new Attr();
			    jt.setData(item.getName());
			    jt.setState("closed");
			    jt.setId(String.valueOf(item.getId()));
			    attr.setId(String.valueOf(item.getId()));
			    attr.setRel("folder");
			    attr.setParent(null);
			    jt.setAttr(attr);
			    jstreeList.add(jt);
			}
		} catch (Exception e) {
        	log.info(e.getMessage(),e);
		}
        return jstreeList;
	}

	@Override
	public int updateUserMenu(MenuDto model) {
        try {
            int count = 0;
            Menu menu = new Menu();
            if(!model.getType().equals("del")){
            	menu.setId(model.getId());
            	menu.setName(model.getName());
            	menu.setParentId(model.getParentId());
            	menu.setIcon(model.getIcon());
            	menu.setUrl(model.getUrl());
            	menu.setSortId(model.getSortId());
            	menu.setStatus(model.getStatus());
            }
            if (model.getType().equals("add") && model.getId() == 0) {
            	menu.setId(null);
            	menu.setCreateTime(new Date());
                count = menuMapper.insert(menu);
            } else if (model.getType().equals("update")) {
                count = menuMapper.updateById(menu);
            } else if(model.getType().equals("del")){
            	count = menuMapper.deleteById(model.getId());
            }
            return count;
        } catch (Exception e) {
        	log.info(e.getMessage(),e);
            return 0;
        }
	}

}
