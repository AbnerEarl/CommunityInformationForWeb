package com.zhang.shequ.modular.business;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhang.shequ.base.controller.BaseController;
import com.zhang.shequ.base.model.response.ResponseJson;
import com.zhang.shequ.core.common.factory.PageFactory;
import com.zhang.shequ.core.entity.OrderRecord;
import com.zhang.shequ.core.service.OrderRecordService;

/**
 * <p>
 * 订单记录表 前端控制器
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-28
 */
@RestController
@RequestMapping("/orderRecord")
public class OrderRecordController extends BaseController {
	
	@Resource
	private OrderRecordService orderRecordService;
	
    @RequestMapping(value = "/getOrderRecordByPage")
    public ResponseJson<OrderRecord> getOrderRecordByPage(Integer farmOrderId) { 
    	Page<OrderRecord> page = new PageFactory<OrderRecord>().defaultPage();
    	Wrapper<OrderRecord> wrapper = new EntityWrapper<>();
    	wrapper.where("farm_order_id = {0}", farmOrderId);
		Page<OrderRecord> pageList = orderRecordService.selectPage(page, wrapper);
    	return packForTable(pageList);
    }

}

