package com.zhang.shequ.core.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.zhang.shequ.core.entity.FarmOrder;
import com.zhang.shequ.core.model.dto.FarmOrderDto;

/**
 * <p>
 * 农场订单表 服务类
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-28
 */
public interface FarmOrderService extends IService<FarmOrder> {

	List<FarmOrderDto> getOrderList(Integer userId);

	List<FarmOrderDto> getFarmOrderListByPage(Page<FarmOrderDto> page, FarmOrder farmOrder);

}
