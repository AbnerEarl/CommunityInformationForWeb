package com.zhang.shequ.core.service.impl;

import com.zhang.shequ.core.entity.Farm;
import com.zhang.shequ.core.mapper.FarmMapper;
import com.zhang.shequ.core.model.dto.FarmDto;
import com.zhang.shequ.core.service.FarmService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 农场表 服务实现类
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-28
 */
@Service
public class FarmServiceImpl extends ServiceImpl<FarmMapper, Farm> implements FarmService {
	
	@Resource
	private FarmMapper farmMapper;

	@Override
	public List<FarmDto> getFarmListByPage(Page<FarmDto> page, Farm farm) {
		return farmMapper.getFarmListByPage(page, farm);
	}

}
