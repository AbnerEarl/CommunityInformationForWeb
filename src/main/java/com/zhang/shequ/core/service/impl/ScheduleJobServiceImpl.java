package com.zhang.shequ.core.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhang.shequ.base.common.constant.ScheduleJobConts;
import com.zhang.shequ.base.model.response.ResponseEntity;
import com.zhang.shequ.core.common.quartz.factory.QuartzJobFactory;
import com.zhang.shequ.core.common.quartz.factory.QuartzJobFactoryDisallowConcurrentExecution;
import com.zhang.shequ.core.entity.ScheduleJob;
import com.zhang.shequ.core.mapper.ScheduleJobMapper;
import com.zhang.shequ.core.service.ScheduleJobService;
import com.zhang.shequ.utils.util.SpringContextHolder;

/**
 * <p>
 * 定时器任务信息表 服务实现类
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-10
 */
@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements ScheduleJobService {
	
public final Logger log = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);
	
	@Autowired
	@Qualifier("schedulerFactoryBean")
	private SchedulerFactoryBean schedulerFactoryBean;
	
	@Autowired
	private ScheduleJobMapper scheduleJobMapper;

	@Override
	public ResponseEntity<?> addScheduleJob(ScheduleJob scheduleJob) {
        try {
            CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
        } catch (Exception e) {
            return ResponseEntity.createExceptionMsg("cron表达式有误，不能被解析");
        }
        Object obj = null;
        try {
            if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
                obj = SpringContextHolder.getBean(scheduleJob.getSpringId());
            } else {
                Class<?> clazz = Class.forName(scheduleJob.getBeanClass());
                obj = clazz.newInstance();
            }
        } catch (Exception e) {
            return ResponseEntity.createExceptionMsg("未找到目标类！");
        }
        if (obj == null) {
        	return ResponseEntity.createExceptionMsg("未找到目标类！");
        } else {
            Class<?> clazz = obj.getClass();
            Method method = null;
            try {
                method = clazz.getMethod(scheduleJob.getMethodName());
            } catch (Exception e) {
            	return ResponseEntity.createExceptionMsg("未找到目标方法！");
            }
            if (method == null) {
            	return ResponseEntity.createExceptionMsg("未找到目标方法！");
            }
        }
        scheduleJob.setCreateTime(new Date());
        scheduleJobMapper.insert(scheduleJob);
        return ResponseEntity.createSuccessMsg("新增成功");
	}
	

	/**
	 * 从数据库中查询job
	 */
	public ScheduleJob getScheduleJobById(Integer jobId) {
		return scheduleJobMapper.selectById(jobId);
	}

	@Override
	public ResponseEntity<?> updateStatus(Integer jobId, String cmd) {
		ScheduleJob job = getScheduleJobById(jobId);
		if (job == null) {
			return ResponseEntity.createExceptionMsg("任务状态改变失败");
		}
		if ("stop".equals(cmd)) {
			deleteJob(job);
			job.setJobStatus(ScheduleJobConts.STATUS_NOT_RUNNING);
		} else if ("start".equals(cmd)) {
			job.setJobStatus(ScheduleJobConts.STATUS_RUNNING);
			addJob(job);
		}
		scheduleJobMapper.updateById(job);
        return ResponseEntity.createSuccessMsg("任务状态改变成功");
	}
	
	/**
	 * 更改任务 cron表达式
	 */
	public ResponseEntity<?> updateScheduleJob(ScheduleJob scheduleJob) {
		if (null == scheduleJob.getId()) {
			return ResponseEntity.createExceptionMsg("修改失败");
		}
		scheduleJobMapper.updateById(scheduleJob);
		ScheduleJob job = getScheduleJobById(scheduleJob.getId());
		if (job == null) {
			return ResponseEntity.createExceptionMsg("修改失败");
		}
		if (ScheduleJobConts.STATUS_RUNNING.equals(job.getJobStatus())) {
			updateJobCron(job);
		}
        return ResponseEntity.createSuccessMsg("修改成功");
	}

	/**
	 * 添加任务
	 */
	public void addJob(ScheduleJob job){
		try {
			if (job == null || job.getJobStatus() == null
					|| ScheduleJobConts.STATUS_NOT_RUNNING.equals(job.getJobStatus())) {
				return;
			}
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			// 不存在，创建一个
			if (null == trigger) {
				Class<? extends Job> clazz = ScheduleJobConts.CONCURRENT_IS.equals(job
						.getIsConcurrent()) ? QuartzJobFactory.class : QuartzJobFactoryDisallowConcurrentExecution.class;
				JobDetail jobDetail = JobBuilder.newJob(clazz)
						.withIdentity(job.getJobName(), job.getJobGroup()).build();
				jobDetail.getJobDataMap().put("scheduleJob", job);
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
						.cronSchedule(job.getCronExpression());
				trigger = TriggerBuilder.newTrigger()
						.withIdentity(job.getJobName(), job.getJobGroup())
						.withSchedule(scheduleBuilder).build();
				scheduler.scheduleJob(jobDetail, trigger);
			} else {
				// Trigger已存在，那么更新相应的定时设置
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
						.cronSchedule(job.getCronExpression());
				// 按新的cronExpression表达式重新构建trigger
				trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
						.withSchedule(scheduleBuilder).build();
				// 按新的trigger重新设置job执行
				scheduler.rescheduleJob(triggerKey, trigger);
			}
		} catch (SchedulerException e) {
			log.info(e.getMessage(), e);
		}
	}

	/**
	 * 删除一个任务
	 */
	@Override
	public ResponseEntity<?> removeById(Integer jobId){
		ScheduleJob scheduleJob = getScheduleJobById(jobId);
		if (scheduleJob == null) {
			return ResponseEntity.createExceptionMsg("修改失败");
		}
		deleteJob(scheduleJob);
		scheduleJobMapper.deleteById(jobId);
		return ResponseEntity.createSuccessMsg("删除成功");
	}

	@PostConstruct
	public void init() throws Exception {
		// 这里获取任务信息数据
		List<ScheduleJob> jobList = scheduleJobMapper.selectList(null);
		for (ScheduleJob job : jobList) {
			addJob(job);
		}
	}

	/**
	 * 获取所有计划中的任务列表
	 */
	public List<ScheduleJob> getAllJob() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler
					.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				ScheduleJob job = new ScheduleJob();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setRemarks("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler
						.getTriggerState(trigger.getKey());
				job.setJobStatus(Integer.valueOf(triggerState.name()));
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		}
		return jobList;
	}

	/**
	 * 所有正在运行的job
	 */
	public List<ScheduleJob> getRunningJob() {
		List<ScheduleJob> jobList = null;
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
			jobList = new ArrayList<ScheduleJob>(executingJobs.size());
			for (JobExecutionContext executingJob : executingJobs) {
				ScheduleJob job = new ScheduleJob();
				JobDetail jobDetail = executingJob.getJobDetail();
				JobKey jobKey = jobDetail.getKey();
				Trigger trigger = executingJob.getTrigger();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setRemarks("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setJobStatus(Integer.valueOf(triggerState.name()));
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		} catch (NumberFormatException e) {
			log.info(e.getMessage(), e);
		} catch (SchedulerException e) {
			log.info(e.getMessage(), e);
		}
		return jobList;
	}

	/**
	 * 暂停一个job
	 */
	public void pauseJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.pauseJob(jobKey);
	}

	/**
	 * 恢复一个job
	 */
	public void resumeJob(ScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.resumeJob(jobKey);
	}

	/**
	 * 删除一个job
	 */
	public void deleteJob(ScheduleJob scheduleJob) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
			scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			log.info(e.getMessage(), e);
		}
	}

	/**
	 * 立即执行job
	 */
	public void runAJobNow(ScheduleJob scheduleJob){
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
			scheduler.triggerJob(jobKey);
		} catch (SchedulerException e) {
			log.info(e.getMessage(), e);
		}
	}

	/**
	 * 更新job时间表达式
	 */
	public void updateJobCron(ScheduleJob scheduleJob) {
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (SchedulerException e) {
			log.info(e.getMessage(), e);
		}
	}

	/**
	 * 立即执行Job
	 */
	@Override
	public ResponseEntity<?> executeById(Integer jobId){
		ScheduleJob scheduleJob = getScheduleJobById(jobId);
		if (ScheduleJobConts.STATUS_RUNNING.equals(scheduleJob.getJobStatus())) {
			runAJobNow(scheduleJob);
	        return ResponseEntity.createSuccessMsg("执行任务成功");
		} else {
			return ResponseEntity.createExceptionMsg("执行任务失败！");
		}
	}
	
}
