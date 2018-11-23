package com.zhang.shequ.utils.util;

import org.dozer.DozerBeanMapper;

import com.zhang.shequ.utils.json.GsonUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BeanCopier {
	
    private static final String DOZER_CONFIG = "dozer-config/dozerBeanMapping.xml";
    
    private static DozerBeanMapper mapper;

    private BeanCopier() {
    	
    }

    private static BeanCopier single = null;

    // 静态工厂方法
    public static BeanCopier getInstance() {
        if (single == null) {
            single = new BeanCopier();
        }
        return single;
    }

    static {
        mapper = new DozerBeanMapper();
        List<String> mappers = new ArrayList<String>();
        mappers.add(DOZER_CONFIG);
        mapper.setMappingFiles(mappers);
    }

    /**
     * 对象转换
     */
    public static <T> T copy(Object source, Class<T> destinationClass) {
        return (T) mapper.map(source, destinationClass);
    }

    /**
     * 对象集合转换
     */
    public static <T> List<T> copy(Collection<?> sourceList, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList<T>();

        for (Object sourceObject : sourceList) {
            T destinationObject = mapper.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * 复制对象
     */
    public static <T> T copyJSONObject(Object o, Class<T> calssz) {
        String jsonData = GsonUtil.objectToJson(o);
        return GsonUtil.jsonToPojo(jsonData, calssz);
    }

    /**
     * 赋值list
     */
    public static <T> List<T> copyJSONList(Object o, Class<T> calssz) {
        String jsonData = GsonUtil.objectToJson(o);
        return GsonUtil.jsonToList(jsonData, calssz);
    }

}