package com.zhang.shequ.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhang.shequ.core.entity.Menu;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-10
 */
public interface MenuMapper extends BaseMapper<Menu> {

	List<Menu> getRoleMenu(@Param("roleId") Integer roleId);

	List<Menu> listMenuByUserId(@Param("userId") Integer userId);

}
