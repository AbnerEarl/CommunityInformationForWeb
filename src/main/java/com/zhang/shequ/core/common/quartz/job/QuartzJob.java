package com.zhang.shequ.core.common.quartz.job;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.zhang.shequ.core.entity.HouseLease;
import com.zhang.shequ.core.entity.HouseNew;
import com.zhang.shequ.core.entity.HouseOld;
import com.zhang.shequ.core.entity.Second;
import com.zhang.shequ.core.service.HouseLeaseService;
import com.zhang.shequ.core.service.HouseNewService;
import com.zhang.shequ.core.service.HouseOldService;
import com.zhang.shequ.core.service.SecondService;
import com.zhang.shequ.utils.util.DateUtil;

@Component
public class QuartzJob {
	
	@Resource
	private SecondService secondService;
	
	@Resource
	private HouseNewService houseNewService;
	
	@Resource
	private HouseOldService houseOldService;
	
	@Resource
	private HouseLeaseService houseLeaseService;
	
    /**
     * 测试用例
     */
    public void test() {
    	System.out.println(new Date());
    }
    
    public void businessDeal() {
    	Map<String, Object> columnMap = new HashMap<>();
    	columnMap.put("status", 0);
    	List<Second> seconds = secondService.selectByMap(columnMap);
    	for (Second second : seconds) {
    		if(DateUtil.isNumBeteen(second.getCreateTime(), 3)) {
    			second.setStatus(1);
    			secondService.updateById(second);
    		}
		}
    	List<HouseNew> houseNews = houseNewService.selectByMap(columnMap);
    	for (HouseNew houseNew : houseNews) {
    		if(DateUtil.isNumBeteen(houseNew.getCreateTime(), 7)) {
    			houseNew.setStatus(1);
    			houseNewService.updateById(houseNew);
    		}
    	}
    	List<HouseOld> houseOlds = houseOldService.selectByMap(columnMap);
    	for (HouseOld houseOld : houseOlds) {
    		if(DateUtil.isNumBeteen(houseOld.getCreateTime(), 7)) {
    			houseOld.setStatus(1);
    			houseOldService.updateById(houseOld);
    		}
    	}
    	List<HouseLease> houseLeases = houseLeaseService.selectByMap(columnMap);
    	for (HouseLease houseLease : houseLeases) {
    		if(DateUtil.isNumBeteen(houseLease.getCreateTime(), 7)) {
    			houseLease.setStatus(1);
    			houseLeaseService.updateById(houseLease);
    		}
    	}
    }
    
}