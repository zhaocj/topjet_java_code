<#include "../main/top.ftl">
<input type="hidden" id="userId" name="id" value="${userInfoBean.id?c}"/>
<input type="hidden" id="resident1" value="${userInfoBean.resident1!}"/>
<input type="hidden" id="resident2" value="${userInfoBean.resident2!}"/>
<input type="hidden" id="resident3" value="${userInfoBean.resident3!}"/>
<input type="hidden" id="resident1Older" value="${userInfoBean.resident1Older!}"/>
<input type="hidden" id="resident2Older" value="${userInfoBean.resident2Older!}"/>
<input type="hidden" id="resident3Older" value="${userInfoBean.resident3Older!}"/>
<input type="hidden" id="idNo" value="${userInfoBean.idNo!}"/>
<input type="hidden" id="status" value="${userInfoBean.useStatus!}"/>
<input type="hidden" id="userAuditStatus" name="userAuditStatus" value="${userInfoBean.userAuthStatus!""}"/>

<#--<input type="hidden" id="businessLineFisrt1" value="${userInfoBean.businessLineFisrt1!""}"/>
<input type="hidden" id="businessLineFisrt2" value="${userInfoBean.businessLineFisrt2!""}"/>
<input type="hidden" id="businessLineFisrt3" value="${userInfoBean.businessLineFisrt3!""}"/>
<input type="hidden" id="businessLineSecond1" value="${userInfoBean.businessLineSecond1!""}"/>
<input type="hidden" id="businessLineSecond2" value="${userInfoBean.businessLineSecond2!""}"/>
<input type="hidden" id="businessLineSecond3" value="${userInfoBean.businessLineSecond3!""}"/>
<input type="hidden" id="businessLineThird1" value="${userInfoBean.businessLineThird1!""}"/>
<input type="hidden" id="businessLineThird2" value="${userInfoBean.businessLineThird2!""}"/>
<input type="hidden" id="businessLineThird3" value="${userInfoBean.businessLineThird3!""}"/>
<input type="hidden" id="businessLineForth1" value="${userInfoBean.businessLineForth1!""}"/>
<input type="hidden" id="businessLineForth2" value="${userInfoBean.businessLineForth2!""}"/>
<input type="hidden" id="businessLineForth3" value="${userInfoBean.businessLineForth3!""}"/>
-->
<input type="hidden" id="businessAddressCity1" value="${userInfoBean.businessAddressCity1!""}"/>
<input type="hidden" id="businessAddressCity2" value="${userInfoBean.businessAddressCity2!""}"/>
<input type="hidden" id="businessAddressCity3" value="${userInfoBean.businessAddressCity3!""}"/>

<input type="hidden" id="businessPhotoUrlTobe1" value="${userInfoBean.businessPhotoUrlTobe1!""}"/>
<input type="hidden" id="shopFrontPhotoUrlTobe1" value="${userInfoBean.shopFrontPhotoUrlTobe1!""}"/>
<input type="hidden" id="dirverLicenceUrlTobe1" value="${userInfoBean.dirverLicenceUrlTobe1!""}"/>
<input type="hidden" id="operatingPermitsPhotoUrlTobe1" value="${userInfoBean.operatingPermitsPhotoUrlTobe1!""}"/>
<input type="hidden" id="businessCardUrlTobe1" value="${userInfoBean.businessCardUrlTobe1!""}"/>
<input type="hidden" id="bigOrdersUrlTobe1" value="${userInfoBean.bigOrdersUrlTobe1!""}"/>

<input type="hidden" id="shopFrontVersion" name="shopFrontVersion" value="${userInfoBean.shopFrontVersion!""}"/>
<input type="hidden" id="businessPhotoVersion" name="businessPhotoVersion"
       value="${userInfoBean.businessPhotoVersion!""}"/>
<input type="hidden" id="headPhotoUrlVersion" name="headPhotoUrlVersion"
       value="${userInfoBean.photoInfoHeadVersion!""}"/>
<input type="hidden" id="idNoPhotoUrlVersion" name="idNoPhotoUrlVersion"
       value="${userInfoBean.photoInfoIdNoVersion!""}"/>
<input type="hidden" id="photoInfoDrivingVersion" name="photoInfoDrivingVersion"
       value="${userInfoBean.photoInfoDrivingVersion!""}"/>
<input type="hidden" id="operatingPermitsPhotoVersion" name="operatingPermitsPhotoVersion"
       value="${userInfoBean.operatingPermitsPhotoVersion!""}"/>
<input type="hidden" id="walletId" name="walletId" value="${userInfoBean.walletId?c!""}"/>
<script type="text/javascript" src="../js/common/jquery.cityselect.js"></script>
<div class="contentBox" style="padding:10px; font-size:14px">
    <div class="fixed-table-toolbar">
        <div class="navmenu"><a href="#" onclick="javascript:history.back(-1); return false">身份认证审核</a> / 审核资料</div>
        <div class="columns columns-left btn-group pull-left fontsize-14"><font>审核状态:</font>
            <!--只有超级管理员可以选择其它部门，部门主管添加用户，默认为本部门，不可修改-->
            <select id="selectAuditResult" name="selectAuditResult" data-toggle="select"
                    class="form-control select select-default select-sm select2-offscreen" tabindex="-1"  >
                <option value="3">审核通过</option>
                <option value="2">审核驳回</option>
            </select>
        </div>
        <div class="columns columns-left btn-group pull-left" style="display:none;" id="reasonBox1">
            <font class="margin-right-5">原因:</font>
            <select id="reasonSelect" data-toggle="select" class="form-control select select-default select-sm" style="width: 280px;">
                <option value="">请选择原因</option>
            <#if userInfoBean.userType == 1>
                <option value="C照认证无效">C照认证无效</option>
                <option value="驾驶证过期">驾驶证过期</option>
                <option value="驾驶证与注册人不一致">驾驶证与注册人不一致</option>
                <option value="驾驶证不符合公司要求">驾驶证不符合公司要求</option>
                <option value="非现场拍摄证件">非现场拍摄证件</option>
                <option value="驾驶证不清晰">驾驶证不清晰</option>
            <#else>
                <option value="证件无注册用户对应信息">证件无注册用户对应信息</option>
                <option value="纸质裁剪名片无效，请提交印刷名片">纸质裁剪名片无效,请提交印刷名片</option>
                <option value="证件不符合公司要求">证件不符合公司要求</option>
                <option value="请上传清晰的证件照">请上传清晰的证件照</option>
                <option value="营业执照法人与注册人不一致">营业执照法人与注册人不一致</option>
                <option value="非现场拍摄证件">非现场拍摄证件</option>
            </#if>
                <option value="">其它(客服手动填写)</option>
            </select>
        </div>
        <div  class="columns columns-left btn-group pull-left margin-right-10 fontsize-14">
            <input class="form-control input-sm pull-left" id="auditIDReason" style=" width:200px;" placeholder="审核原因"
                   type="text" value="审核通过">
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
                <code>认证通过</code><#elseif userInfoBean.useStatus == 3><code>冻结</code><#elseif userInfoBean.useStatus == 4>
                <code>资料修改待审核</code><#else><kbd>认证失败</kbd></#if>
            </td>
            <td style="font-size:22px;">${userInfoBean.idNo}</td>
            <td style="font-size:22px;"><select id="type" disabled>
                <option value="1"  <#if userInfoBean.userType == 1>selected</#if>>司机/车主</option>
                <option value="2"  <#if userInfoBean.userType == 2>selected</#if>>货主</option>
            </select>
            </td>

        </tr>
        <tr class="info">
            <td>企业名称<span style="color:red"><#if userInfoBean.useStatus == 3>(原) </#if></span></td>
            <td>经营地址</td>
            <td>常住地</td>
            <td style="color:red"><#if userInfoBean.useStatus == 3>原常住地</#if></td>
        </tr>
        <tr>
            <td style="font-size:19px;">${userInfoBean.companyName!}<#if userInfoBean.useStatus == 3><span style="color:red">(${userInfoBean.companyNameOlder!})</span></#if></td>
            <td style="font-size:16px;" id="businessAddressCity">
                <select id="businessAddressCity_1" placeholder="请选择省" class="prov" disabled>
                </select>
                <select id="businessAddressCity_2" placeholder="请选择市" class="city" disabled>
                </select>
                <select id="businessAddressCity_3" placeholder="请选区或县" class="dist" disabled>
                </select></td>
            <td style="font-size:16px;" id="resident">
                <select id="resident_1" placeholder="请选择省" class="prov" disabled>
                </select>
                <select id="resident_2" placeholder="请选择市" class="city" disabled>
                </select>
                <select id="resident_3" placeholder="请选区或县" class="dist" disabled>
                </select>
            </td>
            <td style="font-size:16px;" id="residentOlder"><#if userInfoBean.useStatus == 3>
                <select style="color:red" id="resident_1Older" placeholder="请选择省" class="prov" disabled>
                </select>
                <select style="color:red" id="resident_2Older" placeholder="请选择市" class="city" disabled>
                </select>
                <select style="color:red" id="resident_3Older" placeholder="请选区或县" class="dist" disabled>
                </select></#if>
            </td>

        </tr>
        <tr class="info">
            <td>注册日期</td>
            <td>年龄</td>
            <td>固定电话</td>
            <td></td>
        </tr>
        <tr>
            <td>${userInfoBean.createTime?string("yyyy-MM-dd HH:mm:ss")}<#if userInfoBean.order??>
                (${userInfoBean.order}
                )</#if></td>
            <td style="font-size:19px;">${userInfoBean.age!}</td>
            <td>${userInfoBean.telephone! }</td>
            <td></td>
        </tr>
        <tr class="info">
            <td>门头照图片</td>
            <td>营业执照图片</td>
            <td>驾驶证图片</td>
            <td>营运证</td>
        </tr>
        <tr>
            <td class="ppad">
            <#if userInfoBean.shopFrontPhotoUrl??>
                <div class="">
                    <a href="${userInfoBean.shopFrontPhotoUrl}" target="_blank">
                        <img id="shopFrontPhotoUrl" src="${userInfoBean.shopFrontPhotoUrl}"
                             class="img-thumbnail img-responsive"
                             style="max-width:300px"></a>
                </div>
            </#if>
           <#-- <#if userInfoBean.shopFrontPhotoUrlTobe??>
                <div class="">
                    <a href="${userInfoBean.shopFrontPhotoUrlTobe}" target="_blank" title="原图片" >
                        <img id="shopFrontPhotoUrlTobe" id="shopFrontPhotoUrlTobe"
                             src="${userInfoBean.shopFrontPhotoUrlTobe}"
                             style="max-width:300px;border-radius:50px"></a>
                    <a href="#">原图片</a>
                </div>
                <input id="shopFrontPhotoUrlChangeFlag" value="0" type="hidden">
            </#if>-->
            </td>
            <td class="ppad">
            <#if userInfoBean.businessPhotoUrl??>
                <div class="">
                    <a href="${userInfoBean.businessPhotoUrl}" target="_blank"><img id="businessPhotoUrl"
                                                                                    src="${userInfoBean.businessPhotoUrl}"
                                                                                    style="max-width:300px"></a>
                </div>
            </#if>

           <#-- <#if userInfoBean.businessPhotoUrlTobe??>
                <div class="">
                    <a href="${userInfoBean.businessPhotoUrlTobe}" target="_blank" title="原图片"><img
                            id="businessPhotoUrlTobe"
                            src="${userInfoBean.businessPhotoUrlTobe}"
                            style="max-width:300px;border-radius:50px"></a>
                    <a href="#">原图片</a>
                </div>
                <input id="businessPhotoUrlChangeFlag" value="0" type="hidden">
            </#if>-->
            </td>
            <td class="ppad">
                <#if userInfoBean.dirverLicenceUrl??>
                        <a href="${userInfoBean.dirverLicenceUrl}" target="_blank"><img
                                src="${userInfoBean.dirverLicenceUrl}" id="dirverLicenceUrl"
                                style="max-width:300px"></a>

                </#if>
               <#-- <#if userInfoBean.dirverLicenceUrlTobe??>
                        <a href="${userInfoBean.dirverLicenceUrlTobe}" target="_blank" title="原图片"><img
                                src="${userInfoBean.dirverLicenceUrlTobe}" id="dirverLicenceUrlTobe"
                                style="max-width:300px;border-radius:50px"></a>
                        <a href="#">原图片</a>
                    <input id="dirverLicenceUrlChangeFlag" value="0" type="hidden">
                </#if>-->
            </td>
            <td class="ppad">
                <#if userInfoBean.operatingPermitsPhotoUrl??>
                    <a href="${userInfoBean.operatingPermitsPhotoUrl}" target="_blank"><img
                            src="${userInfoBean.operatingPermitsPhotoUrl}" id="operatingPermitsPhotoUrl"
                            style="max-width:300px"></a>
                </#if>
                <#--<#if userInfoBean.operatingPermitsPhotoUrlTobe??>
                    <a href="${userInfoBean.operatingPermitsPhotoUrlTobe}" target="_blank" title="原图片"><img
                            src="${userInfoBean.operatingPermitsPhotoUrlTobe}" id="operatingPermitsPhotoUrlTobe"
                            style="max-width:300px;border-radius:50px"></a>
                    <a href="#">原图片</a>
                    <input id="operatingPermitsPhotoUrlChangeFlag" value="0" type="hidden">
                </#if>-->
            </td>
        </tr>


        <tr class="info">
            <td>名片</td>
            <td>机打发票</td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="ppad">
            <#if userInfoBean.businessCardUrl??>
                <div class="">
                    <a href="${userInfoBean.businessCardUrl}" target="_blank">
                        <img id="businessCardUrl" src="${userInfoBean.businessCardUrl}"
                             class="img-thumbnail img-responsive"
                             style="max-width:300px"></a>
                </div>
            </#if>
           <#-- <#if userInfoBean.businessCardUrlTobe??>
                <div class="">
                    <a href="${userInfoBean.businessCardUrlTobe}" target="_blank" title="原图片" >
                        <img id="businessCardUrlTobe" id="businessCardTobe"
                             src="${userInfoBean.businessCardUrlTobe}"
                             style="max-width:300px;border-radius:50px"></a>
                    <a href="#">原图片</a>
                </div>
                <input id="businessCardUrlChangeFlag" value="0" type="hidden">
            </#if>-->
            </td>
            <td class="ppad">
            <#if userInfoBean.bigOrdersUrl??>
                <div class="">
                    <a href="${userInfoBean.bigOrdersUrl}" target="_blank"><img id="bigOrdersUrl"
                                                                                src="${userInfoBean.bigOrdersUrl}"
                                                                                style="max-width:300px"></a>
                </div>
            </#if>

           <#-- <#if userInfoBean.bigOrdersUrlTobe??>
                <div class="">
                    <a href="${userInfoBean.bigOrdersUrlTobe}" target="_blank" title="原图片"><img
                            id="bigOrdersTobe"
                            src="${userInfoBean.bigOrdersUrlTobe}"
                            style="max-width:300px;border-radius:50px"></a>
                    <a href="#">原图片</a>
                </div>
                <input id="bigOrdersUrlChangeFlag" value="0" type="hidden">
            </#if>-->
            </td>
        </tr>
    </table>
    <div>
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="#">操作日志</a></li>
        </ul>
        <div id="tabadmin" class="tab-pane" role="tabpanel">
            <#list IDCardDetails as IDCardHistory>
                <div class="bs-callout bs-callout-danger" id="callout-labels-inline-block"> <code>${(IDCardHistory.auditCreateTime?string("yyyy-MM-dd HH:mm:ss"))!""}</code><br>
                ${(IDCardHistory.auditName)!""}：${type} ${(IDCardHistory.remark)!""} </div>
            </#list>

        </div>
    </div>
</div>
</div>

<script type="text/javascript">
    $(function () {

        $("#selectAuditResult").change(function () {
            if ($(this).val() == "3") {
                $("#auditIDReason").val("认证通过");
                $("#reasonBox1").hide();
            }else {
                $("#reasonBox1").show();
                $("#auditIDReason").val($("#reasonSelect").val());
            }
        });

        $("#reasonSelect").change(function () {
            $("#auditIDReason").val($(this).val());
        });

        //常住地址
        //  $("#auditIDReason").val("审核通过");
        $("#resident").citySelect({
            prov: $("#resident1").val(),
            city: $("#resident2").val(),
            dist: $("#resident3").val() == '' ? '1' : $("#resident3").val()
        });
        $("#businessAddressCity").citySelect({
            prov: $("#businessAddressCity1").val(),
            city: $("#businessAddressCity2").val(),
            dist: $("#businessAddressCity3").val() == '' ? '1' : $("#businessAddressCity3").val()
        });
        //原常住地
        if($("#status").val() ==3 ){
            $("#residentOlder").citySelect({
                prov: $("#resident1Older").val(),
                city: $("#resident2Older").val(),
                dist: $("#resident3Older").val() == '' ? '1' : $("#resident3Older").val()
            });
        }

    });

    setTimeout(function () {
        $("#businessAddressCity_2").attr("disabled","disabled");
        $("#businessAddressCity_3").attr("disabled","disabled");
        $("#resident_2").attr("disabled","disabled");
        $("#resident_3").attr("disabled","disabled");
        if($("#status").val() ==3 ){
            $("#resident_2Older").attr("disabled","disabled");
            $("#resident_3Older").attr("disabled","disabled");
        }
    },500);
    function update() {
        var bt = $('#submit').button('loading');
        var paras = {
            id: $("#userId").val(),
            userAuthStatus: $("#selectAuditResult").val(),
            auditIDReason: $("#auditIDReason").val(),
            userStatusOlder: $("#userAuditStatus").val(),
            businessPhotoUrl: $("#businessPhotoUrlTobe1").val(),
            shopFrontPhotoUrl: $("#shopFrontPhotoUrlTobe1").val(),
            dirverLicenceUrl: $("#dirverLicenceUrlTobe1").val(),
            operatingPermitsPhotoUrl: $("#operatingPermitsPhotoUrlTobe1").val(),
            businessCardUrl: $("#businessCardUrlTobe1").val(),
            bigOrdersUrl: $("#bigOrdersUrlTobe1").val(),
            userType: $("#type").val(),
        };
        var url = getRootPath() + '/memberAudit/updateIDCardInfo.do';
        $.ajaxDefaultCall(url, paras, function () {
            window.history.back(-1);
        });
    }
    ;
</script>
<#include "../main/footer.ftl">