package com.zhang.shequ.core.mapper;

import com.zhang.shequ.core.entity.Farm;
import com.zhang.shequ.core.model.dto.FarmDto;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 农场表 Mapper 接口
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-28
 */
public interface FarmMapper extends BaseMapper<Farm> {

	List<FarmDto> getFarmListByPage(Page<FarmDto> page, Farm farm);

}
