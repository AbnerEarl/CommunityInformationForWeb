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
 * 二手商品表
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-22
 */
@TableName("sys_second")
public class Second extends Model<Second> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 二手商品描述
     */
    private String content;
    /**
     * 二手商品类别（1.家具 2.电器 3.杂物 4.其他）
     */
    private Integer category;
    /**
     * 二手商品图片
     */
    private String pictures;
    /**
     * 新旧程度（1.全新 2.6-9成新 3.5成新  4.5成以下）
     */
    private Integer degree;
    /**
     * 商品价值
     */
    private Integer price;
    /**
     * 联系电话
     */
    private String linkphone;
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
    @TableField("user_id")
    private Integer userId;
    /**
     * 状态(0.正常, 1.已过期)
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Second{" +
        "id=" + id +
        ", content=" + content +
        ", category=" + category +
        ", pictures=" + pictures +
        ", degree=" + degree +
        ", price=" + price +
        ", linkphone=" + linkphone +
        ", linkman=" + linkman +
        ", linkqq=" + linkqq +
        ", userId=" + userId +
        ", status=" + status +
        ", createTime=" + createTime +
        "}";
    }
}
