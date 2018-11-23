package com.zhang.shequ.modular.business;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhang.shequ.base.controller.BaseController;
import com.zhang.shequ.base.model.response.ResponseEntity;
import com.zhang.shequ.base.model.response.ResponseJson;
import com.zhang.shequ.core.common.factory.PageFactory;
import com.zhang.shequ.core.entity.Farm;
import com.zhang.shequ.core.entity.User;
import com.zhang.shequ.core.model.dto.FarmDto;
import com.zhang.shequ.core.service.FarmService;
import com.zhang.shequ.core.service.UserService;

/**
 * <p>
 * 农场表 前端控制器
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-28
 */
@RestController
@RequestMapping("/farm")
public class FarmController extends BaseController {
	
	@Resource
	private FarmService farmService;
	
	@Resource
	private UserService userService;
	
    @RequestMapping(value = "/farmManage")
    public ModelAndView farmManage() { 
    	return new ModelAndView("business/farmManage");
    }
    
    @RequestMapping(value = "/myfarm")
    public ModelAndView myfarm() { 
    	Integer userId = getSessionUserId();
    	Wrapper<Farm> wrapper = new EntityWrapper<>();
    	wrapper.eq("user_id", userId);
		Farm farm = farmService.selectOne(wrapper);
		FarmDto farmDto = new FarmDto();
    	BeanUtils.copyProperties(farm,farmDto);
    	return new ModelAndView("business/myFarm").addObject("farm", farmDto);
    }
	
    @RequestMapping(value = "/getFarmListByPage")
    public ResponseJson<FarmDto> getFarmListByPage(Farm farm) { 
    	Page<FarmDto> page = new PageFactory<FarmDto>().defaultPage();
		List<FarmDto> pageList = farmService.getFarmListByPage(page, farm);
    	page.setRecords(pageList);
    	return packForTable(page);
    }
    
    @RequestMapping(value = "/add")
    public ResponseEntity<?> add(Farm farm) {
    	farm.setStatus(0);
    	farm.setCreateTime(new Date());
    	boolean flag = farmService.insert(farm);
    	if(flag) {
    		User user = new User();
    		user.setId(farm.getUserId());
    		user.setRoleId(3);
    		userService.updateById(user);
    		return ResponseEntity.createSuccessMsg("注册成功");
    	}else {
    		return ResponseEntity.createFailedMsg("注册失败");
    	}
    }
    
    @RequestMapping(value = "/update")
    public ResponseEntity<?> update(Farm farm) {
    	boolean flag = farmService.updateById(farm);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("修改成功");
    	}else {
    		return ResponseEntity.createFailedMsg("修改失败");
    	}
    }

}

