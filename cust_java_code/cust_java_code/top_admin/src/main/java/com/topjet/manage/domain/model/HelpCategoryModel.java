package com.topjet.manage.domain.model;

public class HelpCategoryModel {
    private Integer helpCategoryID;

    private Integer version;

    private String name;

    private Integer sortNo;

    private Integer isVisible;

    private String icon;

    public Integer getHelpCategoryID() {
        return helpCategoryID;
    }

    public void setHelpCategoryID(Integer helpCategoryID) {
        this.helpCategoryID = helpCategoryID;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}