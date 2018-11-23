package com.zhang.shequ.base.model.response;

import java.util.List;

public class ResponseJson<T> extends IgnoreResponse {

    /**
     * 生成带total总条数和列表的JSON串
     */
    private Integer total;

    private List<T> rows;
  
    public Integer getTotal() {
        return total;
    }

    public ResponseJson<T> setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public List<T> getRows() {
        return rows;
    }

    public ResponseJson<T> setRows(List<T> rows) {
        this.rows = rows;
        return this;
    }

}