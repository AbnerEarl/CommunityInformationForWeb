package com.zhang.shequ.modular.business;


import javax.annotation.Resource;

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
import com.zhang.shequ.core.entity.HouseLease;
import com.zhang.shequ.core.service.HouseLeaseService;

/**
 * <p>
 * 出租房表 前端控制器
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-23
 */
@RestController
@RequestMapping("/houseLease")
public class HouseLeaseController extends BaseController {
	
	@Resource
	private HouseLeaseService houseLeaseService;
	
    @RequestMapping(value = "/houseLeaseManage")
    public ModelAndView houseLeaseManage() { 
    	return new ModelAndView("business/houseLeaseManage");
    }
	
    @RequestMapping(value = "/getHouseLeaseListByPage")
    public ResponseJson<HouseLease> getHouseLeaseListByPage(HouseLease houseLease) { 
    	Page<HouseLease> page = new PageFactory<HouseLease>().defaultPage();
    	Wrapper<HouseLease> wrapper = new EntityWrapper<>();
    	wrapper.where((houseLease.getTitle() != null && houseLease.getTitle() != ""), "title like concat('%',{0},'%')", houseLease.getTitle());
    	wrapper.where((houseLease.getStatus() != null && houseLease.getStatus() != -1), "status = {0}", houseLease.getStatus());
		Page<HouseLease> pageList = houseLeaseService.selectPage(page, wrapper);
    	return packForTable(pageList);
    }
    
    @RequestMapping(value = "/update")
    public ResponseEntity<?> update(HouseLease houseLease) {
    	boolean flag = houseLeaseService.updateById(houseLease);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("修改成功");
    	}else {
    		return ResponseEntity.createFailedMsg("修改失败");
    	}
    }
}

