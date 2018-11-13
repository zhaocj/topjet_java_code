<#include "../main/top.ftl">
<input type="hidden" id="userId" name="id" value="${userInfoBean.id?c}"/>
<input type="hidden" id="idNo" value="${userInfoBean.idNo!}"/>
<input type="hidden" id="status" value="${userInfoBean.useStatus!}"/>
<input type="hidden" id="headPhotoUrl" name="headPhotoUrl" value="${userInfoBean.headPhotoUrl!""}"/>
<input type="hidden" id="headPhotoUrlTobe" name="headPhotoUrlTobe" value="${userInfoBean.headPhotoUrlTobe!""}"/>
<#--<input type="hidden" id="userInfoVersion" name="userInfoVersion" value="${userInfoBean.userInfoVersion?c}"/>-->
<#--<input type="hidden" id="userDataUpdateVersion" name="userDataUpdateVersion" value="${userInfoBean.userDataUpdateVersion?c}"/>-->
<div class="contentBox" style="padding:10px; font-size:14px">
    <div class="fixed-table-toolbar">
        <div class="navmenu"><a href="#" onclick="javascript:history.back(-1); return false">头像认证审核</a> / 会员资料</div>
        <div class="columns columns-left btn-group pull-left fontsize-14"><font>审核状态:</font>
            <!--只有超级管理员可以选择其它部门，部门主管添加用户，默认为本部门，不可修改-->
            <select id="selectAuditResult" name="selectAuditResult" data-toggle="select"
                    class="form-control select select-default select-sm select2-offscreen" tabindex="-1"  >
                <option value="3">审核通过</option>
                <option value="2">审核失败</option>
            </select>
        </div>
        <div  class="columns columns-left btn-group pull-left margin-right-10 fontsize-14">
            <input class="form-control input-sm pull-left" id="auditIDReason" style=" width:200px;" placeholder="审核原因"
                   type="text" value="">
        </div>
        <div class="columns columns-left btn-group pull-right">
            <button type="button" id="submit" class="btn btn-primary btn-sm" onclick="update()">提 交</button>
        </div>
    </div>
    <div class="clearfix margin-top-15"></div>
    <table class="table table-bordered">
        <tr class="info">
            <td width="20%">手机号码</td>
            <td width="20%">姓名</td>
            <td width="20%">身份证号</td>
            <td width="20%">身份</td>
        </tr>
        <tr>
            <td style="font-size:22px;">${userInfoBean.mobile! }</td>
            <td style="font-size:22px;">${userInfoBean.username! } <#if userInfoBean.useStatus == 0>
                <code>未认证</code><#elseif userInfoBean.useStatus == 1><code>待认证</code><#elseif userInfoBean.useStatus == 2>
                <code>认证通过</code><#elseif userInfoBean.useStatus == 4><code>认证失败</code><#elseif userInfoBean.useStatus == 3>
                <code>资料修改待审核</code><#else><kbd>冻结</kbd></#if>
            </td>
            <td style="font-size:22px;">${userInfoBean.idNo}</td>
            <td style="font-size:22px;"><select id="type" disabled>
                <option value="1"  <#if userInfoBean.userType == 1>selected</#if>>司机/车主</option>
                <option value="2"  <#if userInfoBean.userType == 2>selected</#if>>货主</option>
            </select>
            </td>
        </tr>
        <tr class="info">
            <td>注册日期</td>
            <td>年龄</td>
            <td>手机号</td>
            <td>企业名称<span style="color:red"><#if userInfoBean.useStatus == 3>(原) </#if></span></td>
        </tr>
        <tr>
            <td>${userInfoBean.createTime?string("yyyy-MM-dd HH:mm:ss")}<#if userInfoBean.order??>
                (${userInfoBean.order}
                )</#if></td>
            <td style="font-size:19px;">${userInfoBean.age!}</td>
            <td>${userInfoBean.mobile! }</td>
            <td style="font-size:19px;">${userInfoBean.companyName!}<#if userInfoBean.useStatus == 3><span style="color:red">(${userInfoBean.companyNameOlder!})</span></#if></td>
        </tr>
        <tr class="info">
            <td>身份证照片</td>
            <td>原头像</td>
            <td>新头像</td>
        </tr>
        <tr>
            <td class="ppad">
            <#if userInfoBean.idNoPhotoUrl??>
                <div class="">
                    <a href="${userInfoBean.idNoPhotoUrl}" target="_blank" title="身份证">
                        <img id="idNoPhotoUrl" src="${userInfoBean.idNoPhotoUrl}"
                             class="img-thumbnail img-responsive"
                             style="max-width:300px"></a>
                </div>
            </#if>
            </td>
            <td class="ppad">
                <#if userInfoBean.headPhotoUrl??>
                    <div class="">
                        <a href="${userInfoBean.headPhotoUrl}" target="_blank" title="原图片">
                            <img id="shopFrontPhotoUrl" src="${userInfoBean.headPhotoUrl}"
                                 class="img-thumbnail img-responsive"
                                 style="max-width:300px"></a>
                    </div>
                </#if>
            </td>
            <td class="ppad">
                <#if userInfoBean.headPhotoUrlTobe??>
                    <div class="">
                        <a href="${userInfoBean.headPhotoUrlTobe}" target="_blank" title="新图片"><img
                                id="businessPhotoUrlTobe"
                                src="${userInfoBean.headPhotoUrlTobe}"
                                style="max-width:300px;border-radius:50px"></a>
                    </div>
                    <input id="businessPhotoUrlChangeFlag" value="0" type="hidden">
                </#if>
            </td>
        </tr>
    </table>
    <div id = "operaLog">
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="#">操作日志</a></li>
        </ul>
        <div id="tabadmin" class="tab-pane" role="tabpanel">
            <#list headDetails as headHistory>
                <div class="bs-callout bs-callout-danger" id="callout-labels-inline-block"> <code>${(headHistory.createTime?string("yyyy-MM-dd HH:mm:ss"))!""}</code><br>
                ${(headHistory.auditName)!""}：${type} ${(headHistory.remark)!""} </div>
            </#list>
        </div>
    </div>
    <#--<input type="hidden" id="headStatus" name="headStatus" value="${headDetails.headStatus}">-->
</div>

<script type="text/javascript">
    //页面加载 状态为0 不显示操作日志
   /* $(document).ready(function(){
        if($("#headStatus").val() == 0){
            $("#operaLog").hide();
        }else{
            $("#operaLog").show();
        }
    });*/
    $("#auditIDReason").val("审核通过");
    $("#selectAuditResult").change(function () {
        if ($(this).val() == "2") {
            $("#auditIDReason").val("");
        }else{
            $("#auditIDReason").val("审核通过");
        }
    });
    function update() {
        var bt = $('#submit').button('loading');
        var paras = {
            id: $("#userId").val(),
            auditResult:$("#selectAuditResult").val(),
            auditResultMsg: $("#auditIDReason").val(),
            headPhotoUrl: $("#headPhotoUrl").val(),
            headPhotoUrlTobe: $("#headPhotoUrlTobe").val()
          /*  userDataUpdateVersion:$("#userDataUpdateVersion").val()*/
        };
        var url = getRootPath() + '/memberAudit/updateHeadDetail.do';
        $.ajaxDefaultCall(url, paras, function () {
            window.history.back(-1);
        });
    }
    ;
</script>
<#include "../main/footer.ftl">