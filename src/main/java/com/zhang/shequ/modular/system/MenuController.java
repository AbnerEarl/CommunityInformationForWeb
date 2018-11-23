package com.zhang.shequ.modular.system;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhang.shequ.base.controller.BaseController;
import com.zhang.shequ.base.model.response.ResponseEntity;
import com.zhang.shequ.core.entity.Menu;
import com.zhang.shequ.core.model.dto.MenuDto;
import com.zhang.shequ.core.model.menu.Jstree;
import com.zhang.shequ.core.service.MenuService;
import com.zhang.shequ.utils.menu.MenuTreeUtil;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-10
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
	
	@Resource
	private MenuService menuService;

    @RequestMapping(value = "/menuManage")
    @ResponseBody
    public ModelAndView menuManage() {
        return new ModelAndView("system/menuManage");
    }

    /**
     * 读取后台菜单(展示在首页)
     */
    @RequestMapping(value = "/getMenuList", method = RequestMethod.GET)
    @ResponseBody
    public String getMenuList() {
        String menu = null;
        Integer userId = getSessionUserId();
        if (null != userId) {
            List<Menu> menulist = menuService.listMenuByUserId(userId);
            menu = MenuTreeUtil.getMenuJsonByMap(menulist, 0);
        }
        return menu;
    }

    /**
     * 菜单管理中左侧树形结构展示
     */
    @RequestMapping(value = "/getChildMenuList")
    @ResponseBody
    public String getChildMenuList(Integer parentId) {
        List<Menu> list = menuService.getMenuList(parentId);
        return MenuTreeUtil.getMenuListJsonByTable(list, 0);
    }

    /**
     * 菜单管理中右侧表格子级菜单数据展示
     */
    @RequestMapping(value = "/getChildMenuListForTable")
    @ResponseBody
    public List<Menu> getChildMenuListForTable(Integer parentId) {
        return menuService.getMenuList(parentId);
    }

   /**
    * 菜单管理中数据保存操作
    */
    @RequestMapping(value = "/updateMenu")
    @ResponseBody
    public ResponseEntity<?> updateMenu(@RequestBody List<MenuDto> list, HttpServletRequest request) throws Exception {
        int i = 0;
        for (MenuDto menu : list) {
        	menu.setParentId(menu.getParentId() != null ? menu.getParentId() : 0);
            if (null == menu.getSortId()) {
                menu.setSortId(0);
            }
            if (menu.getType().equals("add")) {
                menu.setId(0);
            }
            int count = menuService.updateUserMenu(menu);
            if (count > 0) {
                i++;
            }
        }
        if (i == list.size()) {
            return ResponseEntity.createSuccessMsg("菜单更新成功");
        } else {
        	return ResponseEntity.createFailedMsg("菜单更新失败");
        }
    }

    /**
     * 角色管理中根据roleId获取菜单信息
     */
    @RequestMapping(value = "/getRoleMenu")
    @ResponseBody
    public List<Jstree> getRoleMenu(Integer roleId) {
        return menuService.getRoleMenu(roleId);
    }

}

