package com.zhang.shequ.core.service;

import com.baomidou.mybatisplus.service.IService;
import com.zhang.shequ.base.model.response.ResponseEntity;
import com.zhang.shequ.core.entity.ScheduleJob;

/**
 * <p>
 * 定时器任务信息表 服务类
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-10
 */
public interface ScheduleJobService extends IService<ScheduleJob> {
	
	ResponseEntity<?> addScheduleJob(ScheduleJob scheduleJob);

	ResponseEntity<?> updateStatus(Integer jobId, String cmd);
	
	ResponseEntity<?> removeById(Integer jobId);

	ResponseEntity<?> updateScheduleJob(ScheduleJob scheduleJob);

	ResponseEntity<?> executeById(Integer jobId);
	
}
