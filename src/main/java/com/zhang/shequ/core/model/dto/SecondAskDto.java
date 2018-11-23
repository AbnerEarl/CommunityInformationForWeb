package com.zhang.shequ.core.model.dto;

import java.util.Date;

import com.zhang.shequ.utils.util.RelativeDateFormat;

public class SecondAskDto {

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 用户id
     */
    private Integer userId;
    
    private String userName;
    
    private String userAvatar;
    /**
     * 二手商品id
     */
    private Integer secondId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSecondId() {
        return secondId;
    }

    public void setSecondId(Integer secondId) {
        this.secondId = secondId;
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

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public String getDealTime() {
		dealTime = "";
		if(createTime != null) {
			dealTime = RelativeDateFormat.format(createTime);
		}
		return dealTime;
	}

}
