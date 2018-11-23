package com.zhang.shequ.modular.business;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhang.shequ.base.controller.BaseController;
import com.zhang.shequ.base.model.response.ResponseEntity;
import com.zhang.shequ.base.model.response.ResponseJson;
import com.zhang.shequ.core.common.factory.PageFactory;
import com.zhang.shequ.core.entity.SecondAsk;
import com.zhang.shequ.core.model.dto.SecondAskDto;
import com.zhang.shequ.core.service.SecondAskService;

/**
 * <p>
 * 评论记录表 前端控制器
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-22
 */
@RestController
@RequestMapping("/secondAsk")
public class SecondAskController extends BaseController {
	
	@Resource
	private SecondAskService secondAskService;
	
    @RequestMapping(value = "/getSecondAskListByPage")
    public ResponseJson<SecondAskDto> getSecondAskListByPage(Integer secondId) { 
    	Page<SecondAskDto> page = new PageFactory<SecondAskDto>().defaultPage();
    	List<SecondAskDto> list = secondAskService.getSecondAskListByPage(page, secondId);
    	page.setRecords(list);
    	return packForTable(page);
    }
    
    @RequestMapping(value = "/delete")
    public ResponseEntity<?> askDelete(Integer id) {
    	boolean flag = secondAskService.deleteById(id);
    	if(flag) {
    		return ResponseEntity.createSuccessMsg("删除成功");
    	}else {
    		return ResponseEntity.createFailedMsg("删除失败");
    	}
    }
    
    @RequestMapping(value = "/add")
    public ResponseEntity<?> askAdd(SecondAsk secondAsk) {
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

}

