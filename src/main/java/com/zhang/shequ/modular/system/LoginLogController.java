package com.zhang.shequ.modular.system;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhang.shequ.base.controller.BaseController;
import com.zhang.shequ.base.model.response.ResponseJson;
import com.zhang.shequ.core.common.factory.PageFactory;
import com.zhang.shequ.core.entity.LoginLog;
import com.zhang.shequ.core.service.LoginLogService;

/**
 * <p>
 * 登录记录表 前端控制器
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-10
 */
@RestController
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController {
	
	@Resource
	private LoginLogService loginLogService;
	
    @RequestMapping(value = "/loginLogManage")
    @ResponseBody
    public ModelAndView deptManage() {
        return new ModelAndView("system/loginLogManage");
    }

    /**
     * 部门管理中查询部门列表，带翻页
     */
    @RequestMapping(value = "/getLoginLogListByPage")
    @ResponseBody
    public ResponseJson<LoginLog> getLoginLogListByPage(LoginLog loginLog) {
    	Integer userId = getSessionUserId();
    	loginLog.setUserId(userId);
        Page<LoginLog> page = new PageFactory<LoginLog>().defaultPage();
    	List<LoginLog> list = loginLogService.getLoginLogListByPage(page, loginLog);
    	page.setRecords(list);
        return packForTable(page);
    }

}

