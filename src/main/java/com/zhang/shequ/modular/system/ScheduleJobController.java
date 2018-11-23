package com.zhang.shequ.modular.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhang.shequ.base.controller.BaseController;
import com.zhang.shequ.base.model.response.ResponseEntity;
import com.zhang.shequ.core.entity.ScheduleJob;
import com.zhang.shequ.core.service.ScheduleJobService;

/**
 * <p>
 * 定时器任务信息表 前端控制器
 * </p>
 *
 * @author ZhangGang123
 * @since 2018-08-10
 */
@Controller
@RequestMapping("/scheduleJob")
public class ScheduleJobController extends BaseController {
	
	@Resource
	private ScheduleJobService scheduleJobService;
	
    /**
     * 跳转到定时器任务管理首页
     */
    @RequestMapping("/scheduleJobManage")
    @ResponseBody
    public ModelAndView index() {
        return new ModelAndView("system/scheduleJobManage");
    }
    
    @RequestMapping(value="/list")
    @ResponseBody
    public List<ScheduleJob> list() {
        return scheduleJobService.selectList(null);
    }
    
    @RequestMapping(value="/add")
    @ResponseBody
    public ResponseEntity<?> add(ScheduleJob scheduleJob) {
    	return this.scheduleJobService.addScheduleJob(scheduleJob);
    }

    @RequestMapping(value="/updateStatus")
    @ResponseBody
    public ResponseEntity<?> updateStatus(Integer jobId, String cmd) {
    	return this.scheduleJobService.updateStatus(jobId, cmd);
    }

    @RequestMapping(value="/update")
    @ResponseBody
    public ResponseEntity<?> update(ScheduleJob scheduleJob) {
        return this.scheduleJobService.updateScheduleJob(scheduleJob);
    }

    @RequestMapping(value="/del")
    @ResponseBody
    public ResponseEntity<?> del(Integer jobId) {
    	return this.scheduleJobService.removeById(jobId);
    }

    @RequestMapping(value="/execute")
    @ResponseBody
    public ResponseEntity<?> execute(Integer jobId) {
    	return this.scheduleJobService.executeById(jobId);
    }

}

