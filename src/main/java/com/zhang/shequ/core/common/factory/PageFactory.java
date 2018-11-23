package com.zhang.shequ.core.common.factory;

import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhang.shequ.utils.support.HttpKit;

/**
 * 默认的分页参数创建
 */
public class PageFactory<T> {

    public Page<T> defaultPage() {
        HttpServletRequest request = HttpKit.getRequest();
        int rows = Integer.valueOf(request.getParameter("rows"));     //每页多少条数据
        int page = Integer.valueOf(request.getParameter("page"));   //每页的偏移量(本页当前有多少条)
        Page<T> pageInfo = new Page<>(page, rows);
        pageInfo.setOpenSort(false);
        return pageInfo;
    }
    
}