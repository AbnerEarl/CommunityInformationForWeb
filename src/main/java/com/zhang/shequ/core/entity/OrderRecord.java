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
 * 订单记录表
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-28
 */
@TableName("sys_order_record")
public class OrderRecord extends Model<OrderRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 农场订单id
     */
    @TableField("farm_order_id")
    private Integer farmOrderId;
    /**
     * 农场商品id
     */
    @TableField("farm_product_id")
    private Integer farmProductId;
    /**
     * 商品名称
     */
    private String name;
    private String picture;
    /**
     * 商品价格
     */
    private BigDecimal price;
    /**
     * 购买数量
     */
    private Integer num;
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

    public Integer getFarmOrderId() {
        return farmOrderId;
    }

    public void setFarmOrderId(Integer farmOrderId) {
        this.farmOrderId = farmOrderId;
    }

    public Integer getFarmProductId() {
        return farmProductId;
    }

    public void setFarmProductId(Integer farmProductId) {
        this.farmProductId = farmProductId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
    public String toString() {
        return "OrderRecord{" +
        "id=" + id +
        ", farmOrderId=" + farmOrderId +
        ", farmProductId=" + farmProductId +
        ", name=" + name +
        ", price=" + price +
        ", num=" + num +
        ", createTime=" + createTime +
        "}";
    }
}
