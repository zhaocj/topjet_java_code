package com.topjet.manage.domain.model;

import java.util.Date;

public class SysMenuBtnModel extends BaseModel {
    private Integer id;

    private Integer menuId;

    private String btnName;

    private String btnType;

    private String actionUrls;

    private String sysMenuBtncol;

    private Date createTime;

    private Date updateTime;

    private Integer createBy;

    private Integer updateBy;

    private Integer version;

    private Integer deleted;

    private static final long serialVersionUID = 1L;

    public SysMenuBtnModel(Integer id, Integer menuId, String btnName, String btnType, String actionUrls, String sysMenuBtncol, Date createTime, Date updateTime, Integer createBy, Integer updateBy, Integer version, Integer deleted) {
        this.id = id;
        this.menuId = menuId;
        this.btnName = btnName;
        this.btnType = btnType;
        this.actionUrls = actionUrls;
        this.sysMenuBtncol = sysMenuBtncol;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.version = version;
        this.deleted = deleted;
    }

    public SysMenuBtnModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName == null ? null : btnName.trim();
    }

    public String getBtnType() {
        return btnType;
    }

    public void setBtnType(String btnType) {
        this.btnType = btnType == null ? null : btnType.trim();
    }

    public String getActionUrls() {
        return actionUrls;
    }

    public void setActionUrls(String actionUrls) {
        this.actionUrls = actionUrls == null ? null : actionUrls.trim();
    }

    public String getSysMenuBtncol() {
        return sysMenuBtncol;
    }

    public void setSysMenuBtncol(String sysMenuBtncol) {
        this.sysMenuBtncol = sysMenuBtncol == null ? null : sysMenuBtncol.trim();
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

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}