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
 * 出租房表
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-23
 */
@TableName("sys_house_lease")
public class HouseLease extends Model<HouseLease> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
    /**
     * 卧室数量
     */
    @TableField("count_room")
    private Integer countRoom;
    /**
     * 卫生间数量
     */
    @TableField("count_toilet")
    private Integer countToilet;
    /**
     * 厅数量
     */
    @TableField("count_hall")
    private Integer countHall;
    /**
     * 房屋出租价格
     */
    private BigDecimal price;
    /**
     * 房屋面积
     */
    private BigDecimal acreage;
    /**
     * 房屋地址
     */
    private String address;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "HouseLease{" +
        "id=" + id +
        ", title=" + title +
        ", remarks=" + remarks +
        ", pictures=" + pictures +
        ", countRoom=" + countRoom +
        ", countToilet=" + countToilet +
        ", countHall=" + countHall +
        ", price=" + price +
        ", acreage=" + acreage +
        ", address=" + address +
        ", linkphone=" + linkphone +
        ", linkman=" + linkman +
        ", linkqq=" + linkqq +
        ", userId=" + userId +
        ", status=" + status +
        ", createTime=" + createTime +
        "}";
    }
}
