package com.zhang.shequ.utils.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 资源文件工具类
 */
public class ResourceUtil {

	private ResourceBundle resourceBundle;
	
	private ResourceUtil(String resource) {
		resourceBundle = ResourceBundle.getBundle(resource, Locale.CHINA);
	}
	
	/**
	 * 获取资源
	 */
	public static ResourceUtil getResource(String resource) {
		return new ResourceUtil(resource);
	}
	
	/**
	 * 根据key取得value
	 */
	public String getValue(String key) {
		return resourceBundle.getString(key);
	}
	
	/**
	 * 获取所有资源的Map表示
	 */
	public Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		for(String key: resourceBundle.keySet()) {
			map.put(key, resourceBundle.getString(key));
		}
		return map;
	}
	
}