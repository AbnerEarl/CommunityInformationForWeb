package com.zhang.shequ.core.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 农场表
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-28
 */
public class FarmDto {

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 农场名称
     */
    private String name;
    /**
     * 农场图片
     */
    private String pictures;
    
    private List<String> pictureList;
    /**
     * 农场地址
     */
    private String address;
    /**
     * 农场详情
     */
    private String remarks;
    /**
     * 联系电话
     */
    private String linkphone;
    
    private String linkphoneTo;
    /**
     * 联系人
     */
    private String linkman;
    /**
     * 注册人id
     */
    private Integer userId;
    
    private String userName;
    /**
     * 状态(0.正常 1.冻结)
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getLinkphone() {
        return linkphone;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getPictureList() {
		pictureList = new ArrayList<>();
		if(pictures != null && !pictures.equals("")) {
			String[] splits = pictures.split(",");
			for (String string : splits) {
				pictureList.add(string);
			}
		}
		return pictureList;
	}

	public String getLinkphoneTo() {
		linkphoneTo = "";
		if(linkphone != null && linkphone != "") {
			linkphoneTo = linkphone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
		}
		return linkphoneTo;
	}

}
