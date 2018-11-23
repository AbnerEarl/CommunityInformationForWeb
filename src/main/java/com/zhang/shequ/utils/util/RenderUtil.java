package com.zhang.shequ.utils.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.zhang.shequ.utils.json.GsonUtil;

/**
 * 渲染工具类
 */
public class RenderUtil {

    /**
     * 渲染json对象
     */
    public static void renderJson(HttpServletResponse response, Object jsonObject) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(GsonUtil.objectToJson(jsonObject));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
}
