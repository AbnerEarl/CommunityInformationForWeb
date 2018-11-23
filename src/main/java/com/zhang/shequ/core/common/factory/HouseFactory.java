package com.zhang.shequ.core.common.factory;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zhang.shequ.core.entity.HouseLease;
import com.zhang.shequ.core.entity.HouseNew;
import com.zhang.shequ.core.entity.HouseOld;

/**
 * 用户创建工厂
 */
public class HouseFactory {
	
    public static Wrapper<HouseNew> createHouseNewWrapper(Integer room, Integer acreage, Integer price){
    	Wrapper<HouseNew> wrapper = new EntityWrapper<>();
    	wrapper = getRoomWrapper(wrapper, room);
    	wrapper = getAcreageWrapper(wrapper, acreage);
    	wrapper = getNewPriceWrapper(wrapper, price);
    	return wrapper;
    }
    
    public static Wrapper<HouseOld> createHouseOldWrapper(Integer room, Integer acreage, Integer price){
    	Wrapper<HouseOld> wrapper = new EntityWrapper<>();
    	wrapper = getRoomWrapper(wrapper, room);
    	wrapper = getAcreageWrapper(wrapper, acreage);
    	wrapper = getOldPriceWrapper(wrapper, price);
    	return wrapper;
    }

	public static Wrapper<HouseLease> createHouseLeaseWrapper(Integer room, Integer acreage, Integer price){
		Wrapper<HouseLease> wrapper = new EntityWrapper<>();
		wrapper = getRoomWrapper(wrapper, room);
		wrapper = getAcreageWrapper(wrapper, acreage);
		wrapper = getLeasePriceWrapper(wrapper, price);
		return wrapper;
	}

    private static <T> Wrapper<T> getRoomWrapper(Wrapper<T> wrapper, Integer room) {
    	if(room != null && room != -1) {
    		if(room <= 5) {
    			wrapper.where("count_room = {0}", room);
    		}else {
    			wrapper.where("count_room > {0}", 5);
    		}
    	}
    	return wrapper;
    }
    
    private static <T> Wrapper<T> getAcreageWrapper(Wrapper<T> wrapper, Integer acreage) {
    	if(acreage != null && acreage != -1) {
    		switch (acreage) {
			case 1:
				wrapper.where("acreage between {0} and {1}", 0, 50);
				break;
			case 2:
				wrapper.where("acreage between {0} and {1}", 50, 90);
				break;
			case 3:
				wrapper.where("acreage between {0} and {1}", 90, 150);
				break;
			case 4:
				wrapper.where("acreage between {0} and {1}", 150, 200);
				break;
			case 5:
				wrapper.where("acreage >= {0}", 200);
				break;
			}
    	}
    	return wrapper;
    }
    
    private static <T> Wrapper<T> getNewPriceWrapper(Wrapper<T> wrapper, Integer price) {
    	if(price != null && price != -1) {
    		switch (price) {
			case 1:
				wrapper.where("price between {0} and {1}", 0, 5000);
				break;
			case 2:
				wrapper.where("price between {0} and {1}", 5000, 8000);
				break;
			case 3:
				wrapper.where("price between {0} and {1}", 8000, 12000);
				break;
			case 4:
				wrapper.where("price between {0} and {1}", 12000, 20000);
				break;
			case 5:
				wrapper.where("price >= {0}", 20000);
				break;
			}
    	}
    	return wrapper;
    }
    
    private static <T> Wrapper<T> getOldPriceWrapper(Wrapper<T> wrapper, Integer price) {
    	if(price != null && price != -1) {
    		switch (price) {
			case 1:
				wrapper.where("price between {0} and {1}", 0, 50);
				break;
			case 2:
				wrapper.where("price between {0} and {1}", 50, 100);
				break;
			case 3:
				wrapper.where("price between {0} and {1}", 100, 200);
				break;
			case 4:
				wrapper.where("price between {0} and {1}", 200, 500);
				break;
			case 5:
				wrapper.where("price >= {0}", 500);
				break;
			}
    	}
    	return wrapper;
    }
    
    private static <T> Wrapper<T> getLeasePriceWrapper(Wrapper<T> wrapper, Integer price) {
    	if(price != null && price != -1) {
    		switch (price) {
			case 1:
				wrapper.where("price between {0} and {1}", 0, 1000);
				break;
			case 2:
				wrapper.where("price between {0} and {1}", 1000, 3000);
				break;
			case 3:
				wrapper.where("price between {0} and {1}", 3000, 5000);
				break;
			case 4:
				wrapper.where("price between {0} and {1}", 5000, 10000);
				break;
			case 5:
				wrapper.where("price >= {0}", 10000);
				break;
			}
    	}
    	return wrapper;
    }
    
}
