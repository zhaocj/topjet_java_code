package com.topjet.common.auth.service.dto;

/**
 * Created by tsj010 on 2018/3/28.
 */
public class IDCheckRequestDTO {




    private int QueryType;//   认证类型 1.单条 2.批量
    private String IdentityInfo;//	认证信息 例：单条：张三,140421199003086816 批量：张三,140421199003086816;李四,140421197005063456

    public int getQueryType() {
        return QueryType;
    }

    public void setQueryType(int queryType) {
        QueryType = queryType;
    }

    public String getIdentityInfo() {
        return IdentityInfo;
    }

    public void setIdentityInfo(String identityInfo) {
        IdentityInfo = identityInfo;
    }
}
