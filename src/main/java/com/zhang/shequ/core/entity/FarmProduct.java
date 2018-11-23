package com.zhang.shequ.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 农场商品表
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-28
 */
@TableName("sys_farm_product")
public class FarmProduct extends Model<FarmProduct> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    @TableField(value = "farm_id")
    private Integer farmId;
    /**
     * 蔬菜图片
     */
    private String pictures;
    /**
     * 蔬菜标题
     */
    private String title;
    /**
     * 蔬菜单价
     */
    private BigDecimal price;
    /**
     * 发布人id
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 状态(0.正常, 1.下架)
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public Integer getFarmId() {
		return farmId;
	}

	public void setFarmId(Integer farmId) {
		this.farmId = farmId;
	}

	@Override
    public String toString() {
        return "FarmProduct{" +
        "id=" + id +
        ", pictures=" + pictures +
        ", title=" + title +
        ", price=" + price +
        ", userId=" + userId +
        ", status=" + status +
        ", createTime=" + createTime +
        "}";
    }
}
