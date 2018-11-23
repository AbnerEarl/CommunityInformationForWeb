package com.zhang.shequ.core.model.dto;

import java.util.Date;

public class FarmOrderDto{

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 农场id
     */
    private Integer farmId;
    
    private String farmName;
    /**
     * 状态(0.待发货 1.已发货 2.交易完成 3.交易取消)
     */
    private Integer status;
    
    private String statusValue;
    /**
     * 下单人id
     */
    private Integer userId;
    
    private String userName;
    
    private String userPhone;
    
    private String userAddress;
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

    public Integer getFarmId() {
        return farmId;
    }

    public void setFarmId(Integer farmId) {
        this.farmId = farmId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getFarmName() {
		return farmName;
	}

	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getStatusValue() {
		statusValue = "";
		if(status == 0) {
			statusValue = "待发货";
		}else if(status == 1) {
			statusValue = "已发货";
		}else if(status == 2) {
			statusValue = "交易完成";
		}else if(status == 3) {
			statusValue = "交易取消";
		}
		return statusValue;
	}

}
