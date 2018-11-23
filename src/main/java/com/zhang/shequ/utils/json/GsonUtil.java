package com.zhang.shequ.utils.json;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * gson工具
 *
 */
public class GsonUtil {
	
	private static final Logger log = LoggerFactory.getLogger(GsonUtil.class);

	//定义Gson对象
    private static final Gson gson = new GsonBuilder().create();

    /**
     * 将对象转换成json字符串
     * @param object
     * @return
     */
    public static String objectToJson(Object object) {
    	try {
    		String string = gson.toJson(object);
    		return string;
		} catch (Exception e) {
			log.info(e.getMessage(),e);
		}
    	return null;
    }
    
    /**
     * 将json结果集转化为对象
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {  
    	try {
    		T t = gson.fromJson(jsonData, beanType); 
    		return t;
		} catch (Exception e) {
			log.info(e.getMessage(),e);
		}
    	return null;
    }
    
    /**
     * 将json数据转换成List集合
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {  
        try {
            List<T> list = new ArrayList<T>();  
            JsonArray array = new JsonParser().parse(jsonData).getAsJsonArray();  
            for(final JsonElement elem : array){  
                list.add(gson.fromJson(elem, beanType));  
            }  
            return list;  
		} catch (Exception e) {
			log.info(e.getMessage(),e);
		}  
        return null;
    } 
    
    /**
     * 将json数据转换成有map对象的List集合
     * @param jsonData
     * @return
     */
    public static <T> List<Map<String, T>> GsonToListMap(String jsonData) {  
    	Type type = new TypeToken<List<Map<String, T>>>() { }.getType();
        try {
			List<Map<String, T>> list = gson.fromJson(jsonData, type);   
			return list;
		} catch (Exception e) {
			log.info(e.getMessage(),e);
		}  
        return null;
    } 
    
    /**
     * 将json数据转换成map对象
     * @param jsonData
     * @return
     */
    public static <T> Map<String, T> GsonToMap(String jsonData) {  
    	Type type = new TypeToken<Map<String, T>>() { }.getType();
        try {
			Map<String, T> map = gson.fromJson(jsonData, type);   
			return map;
		} catch (Exception e) {
			log.info(e.getMessage(),e);
		}  
        return null;
    }  

}