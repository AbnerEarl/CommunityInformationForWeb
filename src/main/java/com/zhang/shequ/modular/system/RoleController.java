package com.zhang.shequ.modular.system;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhang.shequ.base.controller.BaseController;
import com.zhang.shequ.base.model.response.ResponseEntity;
import com.zhang.shequ.base.model.response.ResponseJson;
import com.zhang.shequ.core.common.factory.PageFactory;
import com.zhang.shequ.core.entity.Role;
import com.zhang.shequ.core.service.RoleService;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-10
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
	
	@Resource
    private RoleService roleService;
	
    @RequestMapping(value = "/roleManage")
    @ResponseBody
    public ModelAndView roleManage() {
        return new ModelAndView("system/roleManage");
    }

    /**
     * 获取当前所有角色信息
     */
    @RequestMapping(value = "/getRoleListByParam")
    @ResponseBody
    public List<Role> getRoleListByParam(Role role) {
        return roleService.getRoleListByParam(role);
    }

    /**
     * 角色管理中查询角色列表，带翻页
     */
    @RequestMapping(value = "/getRoleListByPage")
    @ResponseBody
    public ResponseJson<Role> list(Role role) {
        Page<Role> page = new PageFactory<Role>().defaultPage();
    	List<Role> roleList = roleService.getRoleListByPage(page, role);
    	page.setRecords(roleList);
        return packForTable(page);
    }

    /**
     * 角色管理中新增角色信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> add(@RequestBody Role role){
    	role.setCreateTime(new Date());
        roleService.insert(role);
        return ResponseEntity.createSuccessMsg("新增成功");
    }

    /**
     * 角色管理中修改角色信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody Role role) {
        roleService.updateById(role);
        return ResponseEntity.createSuccessMsg("修改成功");
    }

    /**
     * 角色管理中更新角色的菜单权限信息
     */
    @RequestMapping(value = "/updateMenuToRole")
    @ResponseBody
    public ResponseEntity<?> updateMenuToRole(Integer roleId, String addMenu) {
        return roleService.updateMenuToRole(roleId, addMenu);
    }

}