package com.zhang.shequ.core.model.dto;

import java.util.Date;

public class MenuDto {
	
	/**
	 * 类型
	 */
	private String type;

    /**
     * 主键id
     */
	private Integer id;
    /**
     * 父结点id
     */
	private Integer parentId;
    /**
     * 菜单名称
     */
	private String name;
    /**
     * 图标地址
     */
	private String icon;
    /**
     * 访问url地址
     */
	private String url;
    /**
     * 排序号
     */
	private Integer sortId;
    /**
     * 菜单状态(0.可用, 1.不可用)
     */
	private Integer status;
    /**
     * 创建时间
     */
	private Date createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}