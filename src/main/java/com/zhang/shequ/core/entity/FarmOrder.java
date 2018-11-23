package com.zhang.shequ.core.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 农场订单表
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-28
 */
@TableName("sys_farm_order")
public class FarmOrder extends Model<FarmOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 农场id
     */
    @TableField("farm_id")
    private Integer farmId;
    /**
     * 状态(0.待发货 1.已发货 2.交易完成 3.交易取消)
     */
    private Integer status;
    /**
     * 下单人id
     */
    @TableField("user_id")
    private Integer userId;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FarmOrder{" +
        "id=" + id +
        ", farmId=" + farmId +
        ", status=" + status +
        ", userId=" + userId +
        ", createTime=" + createTime +
        "}";
    }
}
