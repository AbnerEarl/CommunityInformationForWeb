package com.zhang.shequ.modular.business;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
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
import com.zhang.shequ.core.entity.FarmOrder;
import com.zhang.shequ.core.model.dto.FarmOrderDto;
import com.zhang.shequ.core.service.FarmOrderService;
import com.zhang.shequ.core.service.FarmService;

/**
 * <p>
 * 农场订单表 前端控制器
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-28
 */
@RestController
@RequestMapping("/farmOrder")
public class FarmOrderController extends BaseController {
	
	@Resource
	private FarmOrderService farmOrderService;
	
	@Resource
	private FarmService farmService;
	
    @RequestMapping(value = "/farmOrderManage")
    public ModelAndView farmOrderManage() { 
    	Integer userId = getSessionUserId();
    	Wrapper<Farm> wrapper = new EntityWrapper<>();
    	wrapper.eq("user_id", userId);
		Farm farm = farmService.selectOne(wrapper);
    	return new ModelAndView("business/farmOrderManage").addObject("farm", farm);
    }
    
    @RequestMapping(value = "/getFarmOrderListByPage")
    public ResponseJson<FarmOrderDto> getFarmOrderListByPage(FarmOrder farmOrder) {
    	Page<FarmOrderDto> page = new PageFactory<FarmOrderDto>().defaultPage();
		List<FarmOrderDto> pageList = farmOrderService.getFarmOrderListByPage(page, farmOrder);
		page.setRecords(pageList);
    	return packForTable(page);
    }
    
    @RequestMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody List<FarmOrder> list) {
    	boolean flag = farmOrderService.updateBatchById(list);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("修改成功");
    	}else {
    		return ResponseEntity.createFailedMsg("修改失败");
    	}
    }

}

