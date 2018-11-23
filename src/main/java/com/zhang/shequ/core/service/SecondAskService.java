package com.zhang.shequ.core.service;

import com.zhang.shequ.core.entity.SecondAsk;
import com.zhang.shequ.core.model.dto.SecondAskDto;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 评论记录表 服务类
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-22
 */
public interface SecondAskService extends IService<SecondAsk> {

	List<SecondAskDto> getSecondAskListParam(Map<String, Object> map);

	List<SecondAskDto> getSecondAskListByPage(Page<SecondAskDto> page, Integer secondId);

}
