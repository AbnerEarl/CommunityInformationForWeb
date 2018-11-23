package com.zhang.shequ.core.mapper;

import com.zhang.shequ.core.entity.Second;
import com.zhang.shequ.core.model.dto.SecondDto;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 二手商品表 Mapper 接口
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-22
 */
public interface SecondMapper extends BaseMapper<Second> {

	List<SecondDto> getSecondListParam(Map<String, Object> map);

	SecondDto getSecondById(@Param("id") Integer id);

	List<SecondDto> getSecondListByPage(Page<SecondDto> page, Second second);

}
