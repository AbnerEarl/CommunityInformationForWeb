package com.zhang.shequ.modular.protal;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zhang.shequ.base.controller.BaseController;
import com.zhang.shequ.base.model.response.ResponseEntity;
import com.zhang.shequ.core.entity.Second;
import com.zhang.shequ.core.entity.SecondAsk;
import com.zhang.shequ.core.model.dto.SecondAskDto;
import com.zhang.shequ.core.model.dto.SecondDto;
import com.zhang.shequ.core.model.dto.UserDto;
import com.zhang.shequ.core.service.SecondAskService;
import com.zhang.shequ.core.service.SecondService;

@RestController
@RequestMapping("/protal/second")
public class SecondProtalController extends BaseController {
	
	@Resource
	private SecondService secondService;
	
	@Resource
	private SecondAskService secondAskService;
	
    @RequestMapping(value = "/second")
    public ModelAndView second() { 
    	return new ModelAndView("protal/second/second");
    }
    
    @RequestMapping(value = "/second/my")
    public ModelAndView secondmy() { 
    	return new ModelAndView("protal/second/my_second");
    }
    
    @RequestMapping(value = "/sign")
    public ModelAndView sign() { 
    	return new ModelAndView("protal/second/second_sign");
    }
    
    @RequestMapping(value = "/list")
    public List<SecondDto> list(Integer category, Integer price) { 
    	Map<String, Object> map = new HashMap<>();
    	map.put("category", category);
    	map.put("price", price);
    	map.put("status", 0);
    	return secondService.getSecondListParam(map);
    }
    
    @RequestMapping(value = "/my/list")
    public List<SecondDto> mylist() { 
    	Integer userId = getSessionUserId();
    	Map<String, Object> map = new HashMap<>();
    	map.put("userId", userId);
    	map.put("status", 0);    	
    	return secondService.getSecondListParam(map);
    }
    
    @RequestMapping(value = "/detail/{id}")
    public ModelAndView secondDetail(@PathVariable Integer id) { 
    	ModelAndView modelAndView = new ModelAndView("protal/second/second_detail");
    	SecondDto secondDto = secondService.getSecondById(id);
    	modelAndView.addObject("second", secondDto);
    	return modelAndView;
    }
    
    @RequestMapping(value = "my/detail/{id}")
    public ModelAndView secondmyDetail(@PathVariable Integer id) { 
    	ModelAndView modelAndView = new ModelAndView("protal/second/my_second_detail");
    	SecondDto secondDto = secondService.getSecondById(id);
    	modelAndView.addObject("second", secondDto);
    	return modelAndView;
    }
    
    @RequestMapping(value = "/ask/list")
    public List<SecondAskDto> asklist(Integer secondId) { 
    	Map<String, Object> map = new HashMap<>();
    	map.put("secondId", secondId);
    	return secondAskService.getSecondAskListParam(map);
    }
    
    @RequestMapping(value = "/ask/add")
    public ResponseEntity<?> askadd(SecondAsk secondAsk) {
    	Integer userId = getSessionUserId();
    	secondAsk.setUserId(userId);
    	secondAsk.setCreateTime(new Date());
    	boolean flag = secondAskService.insert(secondAsk);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("回复成功");
    	}else {
    		return ResponseEntity.createFailedMsg("回复失败");
    	}
    }
    
    @RequestMapping(value = "/add")
    public ResponseEntity<?> sign(Second second) {
    	UserDto userDto = getSessionUser();
    	if(userDto != null && userDto.getStatus() == 2) {
    		return ResponseEntity.createFailedMsg("发布失败, 请进行信息认证");
    	}
    	second.setUserId(userDto.getId());
    	second.setStatus(0);
    	second.setCreateTime(new Date());
    	boolean flag = secondService.insert(second);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("发布成功");
    	}else {
    		return ResponseEntity.createFailedMsg("发布失败");
    	}
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