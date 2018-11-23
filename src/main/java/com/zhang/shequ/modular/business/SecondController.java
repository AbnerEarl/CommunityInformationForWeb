package com.zhang.shequ.modular.business;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhang.shequ.base.controller.BaseController;
import com.zhang.shequ.base.model.response.ResponseEntity;
import com.zhang.shequ.base.model.response.ResponseJson;
import com.zhang.shequ.core.common.factory.PageFactory;
import com.zhang.shequ.core.entity.Second;
import com.zhang.shequ.core.model.dto.SecondDto;
import com.zhang.shequ.core.service.SecondService;

/**
 * <p>
 * 二手商品表 前端控制器
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-22
 */
@RestController
@RequestMapping("/second")
public class SecondController extends BaseController {
	
	@Resource
	private SecondService secondService;
	
    @RequestMapping(value = "/secondManage")
    public ModelAndView secondManage() { 
    	return new ModelAndView("business/secondManage");
    }
	
    @RequestMapping(value = "/getSecondListByPage")
    public ResponseJson<SecondDto> getSecondListByPage(Second second) { 
    	Page<SecondDto> page = new PageFactory<SecondDto>().defaultPage();
    	List<SecondDto> list = secondService.getSecondListByPage(page, second);
    	page.setRecords(list);
    	return packForTable(page);
    }
    
    @RequestMapping(value = "/update")
    public ResponseEntity<?> update(Second second) {
    	boolean flag = secondService.updateById(second);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("修改成功");
    	}else {
    		return ResponseEntity.createFailedMsg("修改失败");
    	}
    }

}

