package com.zhang.shequ.core.service.impl;

import com.zhang.shequ.core.entity.SecondAsk;
import com.zhang.shequ.core.mapper.SecondAskMapper;
import com.zhang.shequ.core.model.dto.SecondAskDto;
import com.zhang.shequ.core.service.SecondAskService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论记录表 服务实现类
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-22
 */
@Service
public class SecondAskServiceImpl extends ServiceImpl<SecondAskMapper, SecondAsk> implements SecondAskService {
	
	@Resource
	private SecondAskMapper secondAskMapper;

	@Override
	public List<SecondAskDto> getSecondAskListParam(Map<String, Object> map) {
		return secondAskMapper.getSecondAskListParam(map);
	}

	@Override
	public List<SecondAskDto> getSecondAskListByPage(Page<SecondAskDto> page, Integer secondId) {
		return secondAskMapper.getSecondAskListByPage(page, secondId);
	}

}
