package com.zhang.shequ.core.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zhang.shequ.utils.util.RelativeDateFormat;

public class SecondDto {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 二手商品描述
     */
    private String content;
    /**
     * 二手商品类别（1.家具 2.电器 3.杂物 4.其他）
     */
    private Integer category;
    private String categoryName;
    /**
     * 二手商品图片
     */
    private String pictures;
    
    private List<String> pictureList;
    /**
     * 新旧程度（1.全新 2.6-9成新 3.5成新  4.5成以下）
     */
    private Integer degree;
    private String degreeName;
    /**
     * 商品价值
     */
    private Integer price;
    private String priceName;
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
    
    private String userName;
    
    private String userAvatar;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

	public String getCategoryName() {
		categoryName = "";
		if(category == 1) {
			categoryName = "家具";
		}else if(category == 2) {
			categoryName = "电器";
		}else if(category == 3) {
			categoryName = "杂物";
		}else if(category == 4) {
			categoryName = "其他";
		}
		return categoryName;
	}

	public String getDegreeName() {
		degreeName = "";
		if(degree == 1) {
			degreeName = "全新";
		}else if(degree == 2) {
			degreeName = "6-9成新";
		}else if(degree == 3) {
			degreeName = "5成新";
		}else if(degree == 4) {
			degreeName = "5成新以下";
		}
		return degreeName;
	}

	public String getPriceName() {
		priceName = "";
		if(price == 1) {
			priceName = "面议";
		}else if(price == 2) {
			priceName = "交换同价值物品";
		}else if(price == 3) {
			priceName = "0-200元";
		}else if(price == 4) {
			priceName = "200-500元";
		}else if(price == 5) {
			priceName = "500-1000元";
		}else if(price == 6) {
			priceName = "1000-5000元";
		}else if(price == 7) {
			priceName = "5000元以上";
		}
		return priceName;
	}

	public String getDealTime() {
		dealTime = "";
		if(createTime != null) {
			dealTime = RelativeDateFormat.format(createTime);
		}
		return dealTime;
	}

}
