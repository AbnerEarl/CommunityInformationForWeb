package com.zhang.shequ.core.mapper;

import com.zhang.shequ.core.entity.SecondAsk;
import com.zhang.shequ.core.model.dto.SecondAskDto;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 评论记录表 Mapper 接口
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-22
 */
public interface SecondAskMapper extends BaseMapper<SecondAsk> {

	List<SecondAskDto> getSecondAskListParam(Map<String, Object> map);

	List<SecondAskDto> getSecondAskListByPage(Page<SecondAskDto> page, @Param("secondId") Integer secondId);

}
