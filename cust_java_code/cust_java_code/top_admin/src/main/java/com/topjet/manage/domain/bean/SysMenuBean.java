package com.topjet.manage.domain.bean;

import java.util.Date;

/**
 * Created by pengtao on 8/7/15.
 */
public class SysMenuBean extends BaseBean {




    private Integer id;//   主键
    private String name;//   菜单名称
    private String url;//   系统url
    private Integer parentId;//   父id 关联sysMenu.id
    private Integer deleted;//   是否删除,0=未删除，1=已删除
    private Date createTime;//   创建时间
    private Date updateTime;//   修改时间
    private Integer rank;//   排序
    private String actions; //注册Action 按钮|分隔

    private int subCount;//子菜单总数

    //菜单按钮
//    private List<SysMenuBtnBean> btns;

    private  String cssStyle;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getParentId() {
        return parentId;
    }
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public Integer getDeleted() {
        return deleted;
    }
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getCssStyle() {
        return cssStyle;
    }
    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }
    public Integer getRank() {
        return rank;
    }
    public void setRank(Integer rank) {
        this.rank = rank;
    }
//    public List<SysMenuBtnBean> getBtns() {
//        return btns;
//    }
//    public void setBtns(List<SysMenuBtnBean> btns) {
//        this.btns = btns;
//    }
    public String getActions() {
        return actions;
    }
    public void setActions(String actions) {
        this.actions = actions;
    }
    public int getSubCount() {
        return subCount;
    }
    public void setSubCount(int subCount) {
        this.subCount = subCount;
    }


}
