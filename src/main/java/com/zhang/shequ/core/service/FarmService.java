package com.zhang.shequ.core.service;

import com.zhang.shequ.core.entity.Farm;
import com.zhang.shequ.core.model.dto.FarmDto;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 农场表 服务类
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-28
 */
public interface FarmService extends IService<Farm> {

	List<FarmDto> getFarmListByPage(Page<FarmDto> page, Farm farm);

}
