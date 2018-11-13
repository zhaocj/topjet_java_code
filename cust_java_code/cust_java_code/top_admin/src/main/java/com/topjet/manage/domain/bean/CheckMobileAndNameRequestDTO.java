package com.topjet.manage.domain.bean;

/**
 * Created by liyj on 2017/11/15.
 */
public class CheckMobileAndNameRequestDTO {

    private String Name;

    private String Mobile;

    private String Pid;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }
}
