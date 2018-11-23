package com.zhang.shequ.modular.protal;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zhang.shequ.base.common.constant.Constants;
import com.zhang.shequ.base.controller.BaseController;
import com.zhang.shequ.base.model.response.ResponseEntity;
import com.zhang.shequ.core.entity.Farm;
import com.zhang.shequ.core.entity.FarmOrder;
import com.zhang.shequ.core.entity.FarmProduct;
import com.zhang.shequ.core.entity.OrderRecord;
import com.zhang.shequ.core.model.dto.FarmDto;
import com.zhang.shequ.core.model.dto.FarmOrderDto;
import com.zhang.shequ.core.service.FarmOrderService;
import com.zhang.shequ.core.service.FarmProductService;
import com.zhang.shequ.core.service.FarmService;
import com.zhang.shequ.core.service.OrderRecordService;

@RestController
@RequestMapping("/protal/farm")
public class FarmProtalController extends BaseController {
	
	@Resource
	private FarmService farmService;
	
	@Resource
	private FarmProductService farmProductService;
	
	@Resource
	private FarmOrderService farmOrderService;
	
	@Resource
	private OrderRecordService orderRecordService;
	
    @RequestMapping(value = "/farm")
    public ModelAndView farmManage() { 
    	return new ModelAndView("protal/farm/farm");
    }
	
    @RequestMapping(value = "/list")
    public List<Farm> list() { 
		Map<String, Object> columnMap = new HashMap<String, Object>();
		columnMap.put("status", 0);
		List<Farm> pageList = farmService.selectByMap(columnMap);
    	return pageList;
    }
    
    @RequestMapping(value = "/detail/{id}")
    public ModelAndView farmDetail(@PathVariable Integer id) { 
    	ModelAndView modelAndView = new ModelAndView("protal/farm/farm_detail");
    	Farm farm = farmService.selectById(id);
    	FarmDto farmDto = new FarmDto();
    	BeanUtils.copyProperties(farm,farmDto);
    	modelAndView.addObject("farm", farmDto);
    	return modelAndView;
    }
    
    @RequestMapping(value = "/product/{id}")
    public ModelAndView farmproduct(@PathVariable Integer id) { 
    	ModelAndView modelAndView = new ModelAndView("protal/farm/farm_product");
    	Farm farm = farmService.selectById(id);
    	modelAndView.addObject("farm", farm);
    	return modelAndView;
    }
    
    @RequestMapping(value = "/product/list")
    public List<FarmProduct> listproduct(Integer farmId) { 
		Map<String, Object> columnMap = new HashMap<String, Object>();
		columnMap.put("status", 0);
		columnMap.put("farm_id", farmId);
		List<FarmProduct> pageList = farmProductService.selectByMap(columnMap);
    	return pageList;
    }
    
    @RequestMapping(value = "/cart/add")
    @SuppressWarnings("unchecked")
    public ResponseEntity<?> cartadd(Integer farmId, Integer farmProductId) { 
    	Integer userId = getSessionUserId();
    	Set<Integer> farmProductIds = (Set<Integer>) getSession().getAttribute(farmId + Constants.CART_FARM_SESSION_KEY + userId);
    	if(farmProductIds != null) {
    		farmProductIds.add(farmProductId);
    	}else {
    		farmProductIds = new HashSet<>();
    		farmProductIds.add(farmProductId);
    	}
		getSession().setAttribute(farmId + Constants.CART_FARM_SESSION_KEY + userId, farmProductIds);
    	return ResponseEntity.createSuccessMsg("已加入购物车");
    }
    
    @RequestMapping(value = "/cart/{id}")
    public ModelAndView cart(@PathVariable Integer id) { 
    	ModelAndView modelAndView = new ModelAndView("protal/farm/farm_cart");
    	Farm farm = farmService.selectById(id);
    	modelAndView.addObject("farm", farm);
    	return modelAndView;
    }
    
    @RequestMapping(value = "/cart/list")
    public List<FarmProduct> listcart(Integer farmId) { 
    	Integer userId = getSessionUserId();
    	List<FarmProduct> list = new ArrayList<FarmProduct>();
    	@SuppressWarnings("unchecked")
		Set<Integer> farmProductIds = (Set<Integer>) getSession().getAttribute(farmId + Constants.CART_FARM_SESSION_KEY + userId);
    	if(farmProductIds != null) {
    		list = farmProductService.selectBatchIds(farmProductIds);
    	}
    	return list;
    }
    
    @RequestMapping(value = "/cart/delete")
    @SuppressWarnings("unchecked")
    public ResponseEntity<?> cartdelete(Integer farmId, Integer farmProductId) { 
    	Integer userId = getSessionUserId();
    	Set<Integer> farmProductIds = (Set<Integer>) getSession().getAttribute(farmId + Constants.CART_FARM_SESSION_KEY + userId);
    	if(farmProductIds != null) {
    		farmProductIds.remove(farmProductId);
    	}
		getSession().setAttribute(farmId + Constants.CART_FARM_SESSION_KEY + userId, farmProductIds);
    	return ResponseEntity.createSuccessMsg("删除成功");
    }
    
    @RequestMapping(value = "/order")
    public ModelAndView farmorder() { 
    	return new ModelAndView("protal/farm/farm_order");
    }
    
    @RequestMapping(value = "/order/list")
    public List<FarmOrderDto> listorder() { 
    	Integer userId = getSessionUserId();
		List<FarmOrderDto> pageList = farmOrderService.getOrderList(userId);
    	return pageList;
    }
    
    @RequestMapping(value = "/order/add/{farmId}")
    public ResponseEntity<?> orderadd(@PathVariable Integer farmId, @RequestBody List<OrderRecord> orderRecords) { 
    	Integer userId = getSessionUserId();
    	FarmOrder farmOrder = new FarmOrder();
    	farmOrder.setFarmId(farmId);
    	farmOrder.setStatus(0);
    	farmOrder.setUserId(userId);
    	farmOrder.setCreateTime(new Date());
		boolean flag = farmOrderService.insert(farmOrder);
		if(flag) {
			getSession().removeAttribute(farmId + Constants.CART_FARM_SESSION_KEY + userId);
			for (OrderRecord orderRecord : orderRecords) {
				orderRecord.setFarmOrderId(farmOrder.getId());
				orderRecord.setCreateTime(new Date());
			}
			orderRecordService.insertBatch(orderRecords);
			return ResponseEntity.createSuccessMsg("下单成功");
		}else {
			return ResponseEntity.createFailedMsg("下单失败");

		}
    }
    
    @RequestMapping(value = "/order/cancle")
    public ResponseEntity<?> ordercancle(FarmOrder farmOrder) { 
		boolean flag = farmOrderService.updateById(farmOrder);
		if(flag) {
			return ResponseEntity.createSuccessMsg("取消成功");
		}else {
			return ResponseEntity.createFailedMsg("取消失败");

		}
    }
    
    @RequestMapping(value = "/order/detail/{orderId}")
    public ModelAndView orderDetail(@PathVariable Integer orderId) { 
    	ModelAndView modelAndView = new ModelAndView("protal/farm/farm_order_detail");
    	FarmOrder farmOrder = farmOrderService.selectById(orderId);
    	modelAndView.addObject("farmOrder", farmOrder);
    	return modelAndView;
    }
    
    @RequestMapping(value = "/order/record/list")
    public List<OrderRecord> listorderrecord(Integer farmOrderId) { 
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("farm_order_id", farmOrderId);
		List<OrderRecord> pageList = orderRecordService.selectByMap(columnMap);
    	return pageList;
    }
    
}

