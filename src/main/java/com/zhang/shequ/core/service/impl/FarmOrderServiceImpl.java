package com.zhang.shequ.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhang.shequ.core.entity.FarmOrder;
import com.zhang.shequ.core.mapper.FarmOrderMapper;
import com.zhang.shequ.core.model.dto.FarmOrderDto;
import com.zhang.shequ.core.service.FarmOrderService;

/**
 * <p>
 * 农场订单表 服务实现类
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-28
 */
@Service
public class FarmOrderServiceImpl extends ServiceImpl<FarmOrderMapper, FarmOrder> implements FarmOrderService {
	
	@Resource
	private FarmOrderMapper farmOrderMapper;

	@Override
	public List<FarmOrderDto> getOrderList(Integer userId) {
		return farmOrderMapper.getOrderList(userId);
	}

	@Override
	public List<FarmOrderDto> getFarmOrderListByPage(Page<FarmOrderDto> page, FarmOrder farmOrder) {
		return farmOrderMapper.getFarmOrderListByPage(page, farmOrder);
	}

}
