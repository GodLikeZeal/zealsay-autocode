package com.zeal.shiyulin.paginate;

/**
 * Created by zeal on 2016/10/24.
 * 封装的接收分页参数的对象
 */
public abstract class PaginateQueryProperty<T> {
    protected Integer start;
    protected Integer size;
    protected String sort;
    protected String order;



    protected Integer end;

    public PaginateQueryProperty() {
    }

    public Integer getStart() {
       if(start==null){
           start=0;
       }
        return this.start+1;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        if(size==null){
            size=10;
        }
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }


    public String getSort() {
        return this.sort;
    }

    public void setSort(String sort) {
        if(sort==null){
            sort="asc";
        }
        this.sort = sort;
    }

    public String getOrder() {
        if(order==null){
            order="1";
        }
        return this.order;
    }

    public void setOrder(String order) {

        this.order = order;
    }

    public Integer getEnd() {
        if(start==null){
            start=0;
        }
        if(size==null){
            size=10;
        }
        if(end==null){
            if(start==null){
                start=0;
            }
            if(size==null){
                size=10;
            }
            end=start+size;
        }
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }


    public void teswt(T t){

    }
}
