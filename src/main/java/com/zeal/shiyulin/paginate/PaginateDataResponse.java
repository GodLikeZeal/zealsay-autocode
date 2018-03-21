package com.zeal.shiyulin.paginate;

/**
 * Created by zeal on 2016/10/24.
 */
public class PaginateDataResponse {
    Object data;
    long total;
    int start;
    String sort;
    String order;
    int size;

    public PaginateDataResponse() {
    }

    public Object getData() {
        return this.data;
    }

    public PaginateDataResponse setData(Object data) {
        this.data = data;
        return this;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getStart() {
        return this.start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getSort() {
        return this.sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
