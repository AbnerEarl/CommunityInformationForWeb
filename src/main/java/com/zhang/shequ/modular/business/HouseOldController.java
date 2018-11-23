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
import com.zhang.shequ.core.entity.HouseOld;
import com.zhang.shequ.core.service.HouseOldService;

/**
 * <p>
 * 二手房表 前端控制器
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-23
 */
@RestController
@RequestMapping("/houseOld")
public class HouseOldController extends BaseController {
	
	@Resource
	private HouseOldService houseOldService;
	
    @RequestMapping(value = "/houseOldManage")
    public ModelAndView houseOldManage() { 
    	return new ModelAndView("business/houseOldManage");
    }
	
    @RequestMapping(value = "/getHouseOldListByPage")
    public ResponseJson<HouseOld> getHouseOldListByPage(HouseOld houseOld) { 
    	Page<HouseOld> page = new PageFactory<HouseOld>().defaultPage();
    	Wrapper<HouseOld> wrapper = new EntityWrapper<>();
    	wrapper.where((houseOld.getTitle() != null && houseOld.getTitle() != ""), "title like concat('%',{0},'%')", houseOld.getTitle());
    	wrapper.where((houseOld.getStatus() != null && houseOld.getStatus() != -1), "status = {0}", houseOld.getStatus());
		Page<HouseOld> pageList = houseOldService.selectPage(page, wrapper);
    	return packForTable(pageList);
    }
    
    @RequestMapping(value = "/update")
    public ResponseEntity<?> update(HouseOld houseOld) {
    	boolean flag = houseOldService.updateById(houseOld);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("修改成功");
    	}else {
    		return ResponseEntity.createFailedMsg("修改失败");
    	}
    }
}

