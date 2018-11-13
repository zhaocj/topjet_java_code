package com.topjet.manage.domain.bean;

/**
 * Created by liyj on 2017/12/7.
 */
public class NameBean {


    private String name;
    private Integer categoryId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
