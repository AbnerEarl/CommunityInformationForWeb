package com.zhang.shequ.core.service.impl;

import com.zhang.shequ.core.entity.Second;
import com.zhang.shequ.core.mapper.SecondMapper;
import com.zhang.shequ.core.model.dto.SecondDto;
import com.zhang.shequ.core.service.SecondService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 二手商品表 服务实现类
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-22
 */
@Service
public class SecondServiceImpl extends ServiceImpl<SecondMapper, Second> implements SecondService {
	
	@Resource
	private SecondMapper secondMapper;

	@Override
	public List<SecondDto> getSecondListParam(Map<String, Object> map) {
		return secondMapper.getSecondListParam(map);
	}

	@Override
	public SecondDto getSecondById(Integer id) {
		return secondMapper.getSecondById(id);
	}

	@Override
	public List<SecondDto> getSecondListByPage(Page<SecondDto> page, Second second) {
		return secondMapper.getSecondListByPage(page, second);
	}

}
