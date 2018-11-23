package com.zhang.shequ.modular.business;


import java.util.Date;

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
import com.zhang.shequ.core.entity.Farm;
import com.zhang.shequ.core.entity.FarmProduct;
import com.zhang.shequ.core.service.FarmProductService;
import com.zhang.shequ.core.service.FarmService;

/**
 * <p>
 * 农场商品表 前端控制器
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-28
 */
@RestController
@RequestMapping("/farmProduct")
public class FarmProductController extends BaseController {
	
	@Resource
	private FarmProductService farmProductService;
	
	@Resource
	private FarmService farmService;
	
    @RequestMapping(value = "/farmProductManage")
    public ModelAndView farmProductManage() { 
    	Integer userId = getSessionUserId();
    	Wrapper<Farm> wrapper = new EntityWrapper<>();
    	wrapper.eq("user_id", userId);
		Farm farm = farmService.selectOne(wrapper);
    	return new ModelAndView("business/farmProductManage").addObject("farm", farm);
    }
    
    @RequestMapping(value = "/getFarmProductListByPage")
    public ResponseJson<FarmProduct> getFarmProductListByPage(FarmProduct FarmProduct) { 
    	Page<FarmProduct> page = new PageFactory<FarmProduct>().defaultPage();
    	Wrapper<FarmProduct> wrapper = new EntityWrapper<>();
    	wrapper.where((FarmProduct.getTitle() != null && FarmProduct.getTitle() != ""), "title like concat('%',{0},'%')", FarmProduct.getTitle());
    	wrapper.where((FarmProduct.getStatus() != null && FarmProduct.getStatus() != -1), "status = {0}", FarmProduct.getStatus());
    	wrapper.where("farm_id = {0}", FarmProduct.getFarmId());
		Page<FarmProduct> pageList = farmProductService.selectPage(page, wrapper);
    	return packForTable(pageList);
    }
    
    @RequestMapping(value = "/add")
    public ResponseEntity<?> add(FarmProduct farmProduct) {
    	Integer userId = getSessionUserId();
    	farmProduct.setUserId(userId);
    	farmProduct.setStatus(0);
    	farmProduct.setCreateTime(new Date());
    	boolean flag = farmProductService.insert(farmProduct);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("新增成功");
    	}else {
    		return ResponseEntity.createFailedMsg("新增失败");
    	}
    }
    
    @RequestMapping(value = "/update")
    public ResponseEntity<?> update(FarmProduct farmProduct) {
    	boolean flag = farmProductService.updateById(farmProduct);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("修改成功");
    	}else {
    		return ResponseEntity.createFailedMsg("修改失败");
    	}
    }

}

