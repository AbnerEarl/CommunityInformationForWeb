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
import com.zhang.shequ.core.entity.HouseNew;
import com.zhang.shequ.core.service.HouseNewService;

/**
 * <p>
 * 新房表 前端控制器
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-23
 */
@RestController
@RequestMapping("/houseNew")
public class HouseNewController extends BaseController {
	
	@Resource
	private HouseNewService houseNewService;
	
    @RequestMapping(value = "/houseNewManage")
    public ModelAndView houseNewManage() { 
    	return new ModelAndView("business/houseNewManage");
    }
	
    @RequestMapping(value = "/getHouseNewListByPage")
    public ResponseJson<HouseNew> getSecondListByPage(HouseNew houseNew) { 
    	Page<HouseNew> page = new PageFactory<HouseNew>().defaultPage();
    	Wrapper<HouseNew> wrapper = new EntityWrapper<>();
    	wrapper.where((houseNew.getTitle() != null && houseNew.getTitle() != ""), "title like concat('%',{0},'%')", houseNew.getTitle());
    	wrapper.where((houseNew.getStatus() != null && houseNew.getStatus() != -1), "status = {0}", houseNew.getStatus());
		Page<HouseNew> pageList = houseNewService.selectPage(page, wrapper);
    	return packForTable(pageList);
    }
    
    @RequestMapping(value = "/update")
    public ResponseEntity<?> update(HouseNew houseNew) {
    	boolean flag = houseNewService.updateById(houseNew);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("修改成功");
    	}else {
    		return ResponseEntity.createFailedMsg("修改失败");
    	}
    }

}

