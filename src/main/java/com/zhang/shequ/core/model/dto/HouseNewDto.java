package com.zhang.shequ.core.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zhang.shequ.utils.util.RelativeDateFormat;

public class HouseNewDto {

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 房屋主题
     */
    private String title;
    /**
     * 房屋详情
     */
    private String remarks;
    /**
     * 房屋图片
     */
    private String pictures;
    
    private List<String> pictureList;
    /**
     * 卧室数量
     */
    private Integer countRoom;
    /**
     * 卫生间数量
     */
    private Integer countToilet;
    /**
     * 厅数量
     */
    private Integer countHall;
    /**
     * 装修情况(1.未装修 2.普通装修 3.精装修)
     */
    private String decoration;
    /**
     * 房屋面积
     */
    private BigDecimal acreage;
    /**
     * 房屋预计单价
     */
    private BigDecimal price;
    /**
     * 房屋用途(1.普通住宅 2.店铺)
     */
    private String purpose;
    /**
     * 房屋地址
     */
    private String address;
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
     * 联系qq
     */
    private String linkqq;
    /**
     * 发布人id
     */
    private Integer userId;
    /**
     * 状态(0.正常, 1.已过期)
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    
    private String dealTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public Integer getCountRoom() {
        return countRoom;
    }

    public void setCountRoom(Integer countRoom) {
        this.countRoom = countRoom;
    }

    public Integer getCountToilet() {
        return countToilet;
    }

    public void setCountToilet(Integer countToilet) {
        this.countToilet = countToilet;
    }

    public Integer getCountHall() {
        return countHall;
    }

    public void setCountHall(Integer countHall) {
        this.countHall = countHall;
    }

    public String getDecoration() {
        return decoration;
    }

    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }

    public BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getLinkqq() {
        return linkqq;
    }

    public void setLinkqq(String linkqq) {
        this.linkqq = linkqq;
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

	public String getDealTime() {
		dealTime = "";
		if(createTime != null) {
			dealTime = RelativeDateFormat.format(createTime);
		}
		return dealTime;
	}

}