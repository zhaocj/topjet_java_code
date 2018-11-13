package com.topjet.manage.constants;

import java.io.Serializable;

/**
 * Created by Tym on 2016/8/24.
 */
public class BasePageBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer page=1;//当前页数
    private Integer rows=10;//每页展示条数
    private Integer pageStart;//数据记录起始值（及limit pageStart,page）

    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }
    public void setRows(Integer rows) {
        this.rows = rows;
    }
    public Integer getPageStart() {
        pageStart= (this.page-1)*this.rows;
        return pageStart;
    }
    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }


}
