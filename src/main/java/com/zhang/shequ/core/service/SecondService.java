package com.zhang.shequ.core.service;

import com.zhang.shequ.core.entity.Second;
import com.zhang.shequ.core.model.dto.SecondDto;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 二手商品表 服务类
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-22
 */
public interface SecondService extends IService<Second> {

	List<SecondDto> getSecondListParam(Map<String, Object> map);

	SecondDto getSecondById(Integer id);

	List<SecondDto> getSecondListByPage(Page<SecondDto> page, Second second);

}
