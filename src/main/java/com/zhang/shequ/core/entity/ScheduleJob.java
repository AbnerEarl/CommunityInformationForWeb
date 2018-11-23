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
 * 定时器任务信息表
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-10
 */
@TableName("sys_schedule_job")
public class ScheduleJob extends Model<ScheduleJob> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * job名称
     */
    @TableField("job_name")
    private String jobName;
    /**
     * job分组
     */
    @TableField("job_group")
    private String jobGroup;
    /**
     * job状态
     */
    @TableField("job_status")
    private Integer jobStatus;
    /**
     * cron表达式
     */
    @TableField("cron_expression")
    private String cronExpression;
    /**
     * job描述
     */
    private String remarks;
    /**
     * job执行类
     */
    @TableField("bean_class")
    private String beanClass;
    /**
     * 顺序执行
     */
    @TableField("is_concurrent")
    private String isConcurrent;
    /**
     * job执行ID
     */
    @TableField("spring_id")
    private String springId;
    /**
     * 执行方法
     */
    @TableField("method_name")
    private String methodName;
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

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }

    public String getIsConcurrent() {
        return isConcurrent;
    }

    public void setIsConcurrent(String isConcurrent) {
        this.isConcurrent = isConcurrent;
    }

    public String getSpringId() {
        return springId;
    }

    public void setSpringId(String springId) {
        this.springId = springId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
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
        return "ScheduleJob{" +
        "id=" + id +
        ", jobName=" + jobName +
        ", jobGroup=" + jobGroup +
        ", jobStatus=" + jobStatus +
        ", cronExpression=" + cronExpression +
        ", remarks=" + remarks +
        ", beanClass=" + beanClass +
        ", isConcurrent=" + isConcurrent +
        ", springId=" + springId +
        ", methodName=" + methodName +
        ", createTime=" + createTime +
        "}";
    }
}
