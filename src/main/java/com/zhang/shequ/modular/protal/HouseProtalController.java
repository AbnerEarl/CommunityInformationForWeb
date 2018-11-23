package com.zhang.shequ.modular.protal;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhang.shequ.base.controller.BaseController;
import com.zhang.shequ.base.model.response.ResponseEntity;
import com.zhang.shequ.core.common.factory.HouseFactory;
import com.zhang.shequ.core.entity.HouseLease;
import com.zhang.shequ.core.entity.HouseNew;
import com.zhang.shequ.core.entity.HouseOld;
import com.zhang.shequ.core.model.dto.HouseLeaseDto;
import com.zhang.shequ.core.model.dto.HouseNewDto;
import com.zhang.shequ.core.model.dto.HouseOldDto;
import com.zhang.shequ.core.model.dto.UserDto;
import com.zhang.shequ.core.service.HouseLeaseService;
import com.zhang.shequ.core.service.HouseNewService;
import com.zhang.shequ.core.service.HouseOldService;

@RestController
@RequestMapping("/protal/house")
public class HouseProtalController extends BaseController {
	
	@Resource
	private HouseNewService houseNewService;
	
	@Resource
	private HouseOldService houseOldService;
	
	@Resource
	private HouseLeaseService houseLeaseService;

    @RequestMapping(value = "/new")
    public ModelAndView housenew() { 
    	return new ModelAndView("protal/house/house_new");
    }
    
    @RequestMapping(value = "/new/my")
    public ModelAndView housenewmy() { 
    	return new ModelAndView("protal/house/my_house_new");
    }
    
    @RequestMapping(value = "/new/detail/{id}")
    public ModelAndView secondDetail(@PathVariable Integer id) { 
    	ModelAndView modelAndView = new ModelAndView("protal/house/house_new_detail");
    	HouseNew houseNew = houseNewService.selectById(id);
    	HouseNewDto houseNewDto = new HouseNewDto();
    	BeanUtils.copyProperties(houseNew,houseNewDto);
    	modelAndView.addObject("houseNew", houseNewDto);
    	return modelAndView;
    }
    
    @RequestMapping(value = "/new/my/detail/{id}")
    public ModelAndView secondMyDetail(@PathVariable Integer id) { 
    	ModelAndView modelAndView = new ModelAndView("protal/house/my_house_new_detail");
    	HouseNew houseNew = houseNewService.selectById(id);
    	HouseNewDto houseNewDto = new HouseNewDto();
    	BeanUtils.copyProperties(houseNew,houseNewDto);
    	modelAndView.addObject("houseNew", houseNewDto);
    	return modelAndView;
    }
    
    @RequestMapping(value = "/new/sign")
    public ModelAndView sign() { 
    	return new ModelAndView("protal/house/house_new_sign");
    }
    
    @RequestMapping(value = "/new/list")
    public List<HouseNew> list(Integer room, Integer acreage, Integer price) { 
    	Wrapper<HouseNew> wrapper = HouseFactory.createHouseNewWrapper(room, acreage, price);
    	wrapper.where("status = {0}", 0);
		return houseNewService.selectList(wrapper);
    }
    
    @RequestMapping(value = "/new/my/list")
    public List<HouseNew> listmy() { 
    	Integer userId = getSessionUserId();
    	Wrapper<HouseNew> wrapper = new EntityWrapper<>();
    	wrapper.where("status = {0}", 0);
    	wrapper.where("user_id = {0}", userId);
		return houseNewService.selectList(wrapper);
    }
    
    @RequestMapping(value = "/new/add")
    public ResponseEntity<?> newAdd(HouseNew houseNew) {
    	UserDto userDto = getSessionUser();
    	if(userDto != null && userDto.getStatus() == 2) {
    		return ResponseEntity.createFailedMsg("发布失败, 请进行信息认证");
    	}
    	houseNew.setUserId(userDto.getId());
    	houseNew.setStatus(0);
    	houseNew.setCreateTime(new Date());
    	boolean flag = houseNewService.insert(houseNew);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("发布成功");
    	}else {
    		return ResponseEntity.createFailedMsg("发布失败");
    	}
    }
    
    @RequestMapping(value = "/new/update")
    public ResponseEntity<?> newupdate(HouseNew houseNew) {
    	boolean flag = houseNewService.updateById(houseNew);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("修改成功");
    	}else {
    		return ResponseEntity.createFailedMsg("修改失败");
    	}
    }
    
    @RequestMapping(value = "/old")
    public ModelAndView houseold() { 
    	return new ModelAndView("protal/house/house_old");
    }
    
    @RequestMapping(value = "/old/my")
    public ModelAndView houseoldmy() { 
    	return new ModelAndView("protal/house/my_house_old");
    }
    
    @RequestMapping(value = "/old/detail/{id}")
    public ModelAndView houseOldDetail(@PathVariable Integer id) { 
    	ModelAndView modelAndView = new ModelAndView("protal/house/house_old_detail");
    	HouseOld houseOld = houseOldService.selectById(id);
    	HouseOldDto houseOldDto = new HouseOldDto();
    	BeanUtils.copyProperties(houseOld,houseOldDto);
    	modelAndView.addObject("houseOld", houseOldDto);
    	return modelAndView;
    }
    
    @RequestMapping(value = "/old/my/detail/{id}")
    public ModelAndView houseOldDetailMy(@PathVariable Integer id) { 
    	ModelAndView modelAndView = new ModelAndView("protal/house/my_house_old_detail");
    	HouseOld houseOld = houseOldService.selectById(id);
    	HouseOldDto houseOldDto = new HouseOldDto();
    	BeanUtils.copyProperties(houseOld,houseOldDto);
    	modelAndView.addObject("houseOld", houseOldDto);
    	return modelAndView;
    }
    
    @RequestMapping(value = "/old/sign")
    public ModelAndView signOld() { 
    	return new ModelAndView("protal/house/house_old_sign");
    }
    
    @RequestMapping(value = "/old/list")
    public List<HouseOld> listOld(Integer room, Integer acreage, Integer price) { 
    	Wrapper<HouseOld> wrapper = HouseFactory.createHouseOldWrapper(room, acreage, price);
    	wrapper.where("status = {0}", 0);
		return houseOldService.selectList(wrapper);
    }
    
    @RequestMapping(value = "/old/my/list")
    public List<HouseOld> listMyOld() { 
    	Integer userId = getSessionUserId();
    	Wrapper<HouseOld> wrapper = new EntityWrapper<>();
    	wrapper.where("status = {0}", 0);
    	wrapper.where("user_id = {0}", userId);
    	return houseOldService.selectList(wrapper);
    }
    
    @RequestMapping(value = "/old/add")
    public ResponseEntity<?> oldAdd(HouseOld houseOld) {
    	UserDto userDto = getSessionUser();
    	if(userDto != null && userDto.getStatus() == 2) {
    		return ResponseEntity.createFailedMsg("发布失败, 请进行信息认证");
    	}
    	houseOld.setUserId(userDto.getId());
    	houseOld.setStatus(0);
    	houseOld.setCreateTime(new Date());
    	boolean flag = houseOldService.insert(houseOld);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("发布成功");
    	}else {
    		return ResponseEntity.createFailedMsg("发布失败");
    	}
    }
    
    @RequestMapping(value = "/old/update")
    public ResponseEntity<?> oldupdate(HouseOld houseOld) {
    	boolean flag = houseOldService.updateById(houseOld);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("修改成功");
    	}else {
    		return ResponseEntity.createFailedMsg("修改失败");
    	}
    }
    
    @RequestMapping(value = "/lease")
    public ModelAndView houselease() { 
    	return new ModelAndView("protal/house/house_lease");
    }
    
    @RequestMapping(value = "/lease/my")
    public ModelAndView houseleasemy() { 
    	return new ModelAndView("protal/house/my_house_lease");
    }
    
    @RequestMapping(value = "/lease/detail/{id}")
    public ModelAndView houseleaseDetail(@PathVariable Integer id) { 
    	ModelAndView modelAndView = new ModelAndView("protal/house/house_lease_detail");
    	HouseLease houseLease = houseLeaseService.selectById(id);
    	HouseLeaseDto houseLeaseDto = new HouseLeaseDto();
    	BeanUtils.copyProperties(houseLease,houseLeaseDto);
    	modelAndView.addObject("houseLease", houseLeaseDto);
    	return modelAndView;
    }
    
    @RequestMapping(value = "/lease/my/detail/{id}")
    public ModelAndView houseleasemyDetail(@PathVariable Integer id) { 
    	ModelAndView modelAndView = new ModelAndView("protal/house/my_house_lease_detail");
    	HouseLease houseLease = houseLeaseService.selectById(id);
    	HouseLeaseDto houseLeaseDto = new HouseLeaseDto();
    	BeanUtils.copyProperties(houseLease,houseLeaseDto);
    	modelAndView.addObject("houseLease", houseLeaseDto);
    	return modelAndView;
    }
    
    @RequestMapping(value = "/lease/sign")
    public ModelAndView signlease() { 
    	return new ModelAndView("protal/house/house_lease_sign");
    }
    
    @RequestMapping(value = "/lease/list")
    public List<HouseLease> listlease(Integer room, Integer acreage, Integer price) { 
    	Wrapper<HouseLease> wrapper = HouseFactory.createHouseLeaseWrapper(room, acreage, price);
    	wrapper.where("status = {0}", 0);
		return houseLeaseService.selectList(wrapper);
    }
    
    @RequestMapping(value = "/lease/my/list")
    public List<HouseLease> listmylease() { 
    	Integer userId = getSessionUserId();
    	Wrapper<HouseLease> wrapper = new EntityWrapper<>();
    	wrapper.where("status = {0}", 0);
    	wrapper.where("user_id = {0}", userId);
    	return houseLeaseService.selectList(wrapper);
    }
    
    @RequestMapping(value = "/lease/add")
    public ResponseEntity<?> leaseAdd(HouseLease houseLease) {
    	UserDto userDto = getSessionUser();
    	if(userDto != null && userDto.getStatus() == 2) {
    		return ResponseEntity.createFailedMsg("发布失败, 请进行信息认证");
    	}
    	houseLease.setUserId(userDto.getId());
    	houseLease.setStatus(0);
    	houseLease.setCreateTime(new Date());
    	boolean flag = houseLeaseService.insert(houseLease);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("发布成功");
    	}else {
    		return ResponseEntity.createFailedMsg("发布失败");
    	}
    }
    
    @RequestMapping(value = "/lease/update")
    public ResponseEntity<?> leaseupdate(HouseLease houseLease) {
    	boolean flag = houseLeaseService.updateById(houseLease);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("修改成功");
    	}else {
    		return ResponseEntity.createFailedMsg("修改失败");
    	}
    }
	
}