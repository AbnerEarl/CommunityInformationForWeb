package com.zhang.shequ.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zhang.shequ.core.entity.FarmOrder;
import com.zhang.shequ.core.model.dto.FarmOrderDto;

/**
 * <p>
 * 农场订单表 Mapper 接口
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-28
 */
public interface FarmOrderMapper extends BaseMapper<FarmOrder> {

	List<FarmOrderDto> getOrderList(@Param("userId") Integer userId);

	List<FarmOrderDto> getFarmOrderListByPage(Page<FarmOrderDto> page, FarmOrder farmOrder);

}
