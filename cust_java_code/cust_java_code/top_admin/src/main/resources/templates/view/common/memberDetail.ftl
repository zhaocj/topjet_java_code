<#include "../main/top.ftl">
<input type="hidden" id="userId" name="id" value="${userInfoBean.id?c!""}"/>
<input type="hidden" id="resident1" value="${userInfoBean.resident1!}"/>
<input type="hidden" id="resident2" value="${userInfoBean.resident2!}"/>
<input type="hidden" id="resident3" value="${userInfoBean.resident3!}"/>
<input type="hidden" id="idNo" value="${userInfoBean.idNo!}"/>
<#--<input type="hidden" id="userInfoVersion" name="userInfoVersion" value="${userInfoBean.userInfoVersion?c}"/>-->

<input type="hidden" id="businessLineFisrt1" value="${userInfoBean.businessLineFisrt1!""}"/>
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

<input type="hidden" id="businessLineFisrtId" value="${userInfoBean.businessLineFisrtId!""}"/>
<input type="hidden" id="businessLineSecondId" value="${userInfoBean.businessLineSecondId!""}"/>
<input type="hidden" id="businessLineThirdId" value="${userInfoBean.businessLineThirdId!""}"/>
<input type="hidden" id="businessLineForthId" value="${userInfoBean.businessLineForthId!""}"/>

<input type="hidden" id="businessAddressCity1" value="${userInfoBean.businessAddressCity1!""}"/>
<input type="hidden" id="businessAddressCity2" value="${userInfoBean.businessAddressCity2!""}"/>
<input type="hidden" id="businessAddressCity3" value="${userInfoBean.businessAddressCity3!""}"/>

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
        <div class="navmenu"><a href="#" onclick="javascript:history.back(-1); return false">会员管理</a> / 会员资料</div>
        <div class="columns columns-left btn-group pull-right">
       <#-- <#if title=="会员管理">-->
            <button type="button" id="submit" class="btn btn-primary btn-sm" onclick="update()">更新资料</button>
       <#-- </#if>-->
        </div>
    </div>
    <div class="clearfix margin-top-15"></div>
    <table class="table table-bordered">
        <tr class="info">
            <td width="20%">手机号码</td>
            <td width="20%">姓名</td>
            <td width="20%">身份证号</td>
            <td width="20%">身份</td>
            <td width="20%">注册日期</td>
        </tr>
        <tr>
            <td style="font-size:22px;">${userInfoBean.mobile! }</td>
            <td style="font-size:22px;">${userInfoBean.username! } <#if userInfoBean.useStatus == 0>
                <code>未认证</code><#elseif userInfoBean.useStatus == 1><code>待认证</code><#elseif userInfoBean.useStatus == 2>
                <code>认证通过</code><#elseif userInfoBean.useStatus == 4><code>认证失败</code><#elseif userInfoBean.useStatus == 3>
                <code>资料修改待审核</code><#else><kbd>冻结</kbd><label
                    style="font-size:20px;">(${userInfoBean.reditRemark! })</label></#if>
            </td>
            <td style="font-size:22px;">${(userInfoBean.idNo)!""}</td>
            <td style="font-size:22px;"><select id="type">
                <option value="1"  <#if userInfoBean.userType == 1>selected</#if>>司机/车主</option>
                <option value="2"  <#if userInfoBean.userType == 2>selected</#if>>货主</option>
            </select>
            </td>
            <td>${(userInfoBean.createTime?string("yyyy-MM-dd HH:mm:ss"))!""}<#if userInfoBean.order??>
                (${userInfoBean.order}
                )</#if></td>
        </tr>
        <tr class="info">
            <td>企业名称</td>
            <td>年龄</td>
            <td>经营地址</td>
            <td>常住地</td>
            <td>固定电话</td>
        </tr>
        <tr>
            <td><input type="text" id="companyName"
                                               value="${userInfoBean.companyName!}"></td>
            <td><label style="font-size:16px">${userInfoBean.age!}</label></td>
            <td id="businessAddressCity">
                <select id="businessAddressCity_1" placeholder="请选择省" class="prov">
                </select>
                <select id="businessAddressCity_2" placeholder="请选择市" class="city">
                </select>
                <select id="businessAddressCity_3" placeholder="请选区或县" class="dist">
                </select></td>
            <td id="resident">
                <select id="resident_1" placeholder="请选择省" class="prov">
                </select>
                <select id="resident_2" placeholder="请选择市" class="city">
                </select>
                <select id="resident_3" placeholder="请选区或县" class="dist">
                </select>
            </td>
            <td><input type="text" id="telephone" value="${userInfoBean.telephone! }"></td>
        </tr>
        <tr class="info">
            <td>经营路线1</td>
            <td>经营路线2</td>
            <td>经营路线3</td>
            <td>经营路线4</td>
            <td></td>
        </tr>
        <tr>
            <td id="businessLineFisrt">
                <select id="businessLineFisrt_1"  placeholder="请选择省" class="prov">
                </select>
                <select id="businessLineFisrt_2" placeholder="请选择市" class="city">
                </select>
                <select id="businessLineFisrt_3" placeholder="请选区或县" class="dist">
                </select>
            </td>
            <td id="businessLineSecond"><select id="businessLineSecond_1" placeholder="请选择省" class="prov">
            </select>
                <select id="businessLineSecond_2" placeholder="请选择市" class="city">
                </select>
                <select id="businessLineSecond_3" placeholder="请选区或县" class="dist">
                </select>
            </td>
            <td id="businessLineThird"><select id="businessLineThird_1" placeholder="请选择省" class="prov">
            </select>
                <select id="businessLineThird_2" placeholder="请选择市" class="city">
                </select>
                <select id="businessLineThird_3" placeholder="请选区或县" class="dist">
                </select></td>
            <td id="businessLineForth"><select id="businessLineForth_1" placeholder="请选择省" class="prov">
            </select>
                <select id="businessLineForth_2" placeholder="请选择市" class="city">
                </select>
                <select id="businessLineForth_3" placeholder="请选区或县" class="dist">
                </select></td>
        </tr>
        <!-----总数查看时显示的页面为当前条件筛选+用户ID的例表页-------->
        <tr class="info">
            <td>订单总数</td>
            <td>承运总数</td>
            <td>车辆总数</td>
            <td>被评价总数</td>
            <td>已评价总数</td>
        </tr>
        <tr>
            <td>${userInfoBean.ownerOrderCount!}次<a href="#">　
                <li class="icon-search" onclick="javascript:getOrderList(${userInfoBean.mobile! })"></li>
            </a></td>
            <td>${userInfoBean.driverOrderCount!}次<a href="#">　
                <li class="icon-search"></li>
            </a></td>
            <td>${userInfoBean.truckCount!}辆<a href="#">　
                <li class="icon-search"></li>
            </a></td>
            <td>${userInfoBean.commentedCount!}次<a href="#">　
                <li class="icon-search"></li>
            </a></td>
            <td>${userInfoBean.commentCount!}次<a href="#">　
                <li class="icon-search"></li>
            </a></td>
        </tr>
        <tr class="info">
            <td>积分</td>
            <td>钱包余额</td>
            <td>信誉值</td>
            <td>评价得分</td>
            <td></td>
        </tr>
        <tr>
            <td>${userInfoBean.score!'0'}<a href="#">　
                <li class="icon-search"></li>
            </a></td>
            <td id="walletBalance">0.00</td>
            <td>${userInfoBean.credit!'0'}<a href="#">　
                <li class="icon-search"></li>
            </a></td>
            <td>
            <#if userInfoBean.userType == 2>${userInfoBean.ownerCommentLevel!'0'}
            <#else>${userInfoBean.driverCommentLevel!'0'}</#if>
                分<a href="#">　
                <li class="icon-search"></li>
            </a></td>
            <td></td>
        </tr>
        <tr class="info">
            <td>推荐人手机号</td>
            <td>我推荐的用户</td>
            <td>我添加的熟车</td>
            <td>注册认证状态(备注)</td>
            <td>身份认证状态(备注)</td>
        </tr>
        <tr>
            <td>
            ${userInfoBean.recommendUserMobile!"无"}
            </td>
            <td>${userInfoBean.recommendCount!"0"}人<a href="#">　
                <li class="icon-search"></li>
            </a></td>
            <td>${userInfoBean.ownerTruckCount!"0"}辆<a href="#">　
                <li class="icon-search"></li>
            </a></td>
            <td>
            <#switch userInfoBean.useStatus>
                <#case 0>未认证<#break >
                <#case 1>认证中<#break >
                <#case 2>认证通过<#break >
                <#case 3>资料修改待审核<#break >
                <#case 4>认证失败<#break >
            </#switch>
                (${reditRemark!""})
            </td>
            <td><#switch userInfoBean.userAuthStatus>
                <#case 0>未认证<#break >
                <#case 1>认证中<#break >
                <#case 2>认证失败<#break >
                <#case 3>认证通过<#break >
            </#switch>
                (${userInfoBean.auditIDReason!""})
            </td>
        </tr>
        <tr class="info">
            <td>头像图片</td>
            <td>身份证图片</td>
            <td>门头照图片</td>
            <td>营业执照图片</td>
            <td>驾驶证图片</td>
        </tr>
        <tr>
            <td class="ppad">
            <#if userInfoBean.headPhotoUrl??>
                <div class="mag">
                    <a href="${userInfoBean.headPhotoUrl}" target="_blank">
                        <img id="headPhotoUrl" src="${userInfoBean.headPhotoUrl}"
                             style="max-width:200px"></a>
                </div>
            </#if>
            <#-- <#if userInfoBean.headPhotoUrlTobe??>
                 <div class="mag">
                     <a href="${userInfoBean.headPhotoUrlTobe}" target="_blank"><img id="headPhotoUrlTobe"
                                                                                     src="${userInfoBean.headPhotoUrlTobe}"
                                                                                     style="max-width:200px"></a>

                 </div>
             </#if>-->
            </td>
            <td class="ppad">
            <#if userInfoBean.idNoPhotoUrl??>
                <div class="mag">
                    <a href="${userInfoBean.idNoPhotoUrl}" target="_blank">
                        <img id="idNoPhotoUrl" src="${userInfoBean.idNoPhotoUrl}"

                             style="max-width:200px"></a>
                </div>
            </#if>
            <#--<#if userInfoBean.idNoPhotoUrlTobe??>
                <div class="mag">
                    <a href="${userInfoBean.idNoPhotoUrlTobe}" target="_blank"><img id="idNoPhotoUrlTobe"
                                                                                    src="${userInfoBean.idNoPhotoUrlTobe}"
                                                                                    style="max-width:200px"></a>

                </div>
            </#if>-->
            </td>
            <td class="ppad">
            <#if userInfoBean.shopFrontPhotoUrl??>
                <div class="">
                    <a href="${userInfoBean.shopFrontPhotoUrl}" target="_blank">
                        <img id="shopFrontPhotoUrl" src="${userInfoBean.shopFrontPhotoUrl}"
                             class="img-thumbnail img-responsive"
                             style="max-width:200px"></a>
                </div>

            </#if>
            <#--  <input id="shopFrontPhotoUrlChangeFlag" value="0" type="hidden">
          <#if userInfoBean.shopFrontPhotoUrlTobe??>
              <div class="">
                  <a href="${userInfoBean.shopFrontPhotoUrlTobe}" target="_blank">
                      <img id="shopFrontPhotoUrlTobe" id="shopFrontPhotoUrlTobe"
                           src="${userInfoBean.shopFrontPhotoUrlTobe}"
                           style="max-width:200px"></a>
              </div>
              <#if userInfoBean.status == 3>
                  <a class="btn btn-primary btn-sm" id="shopFrontPhotoUrlA"
                     onclick="photoUrlUpdate('#shopFrontPhotoUrl')">更新</a>
              </#if>
          </#if>-->
            </td>
            <td class="ppad">
            <#if userInfoBean.businessPhotoUrl??>
                <div class="">
                    <a href="${userInfoBean.businessPhotoUrl}" target="_blank"><img id="businessPhotoUrl"
                                                                                    src="${userInfoBean.businessPhotoUrl}"
                                                                                    style="max-width:200px"></a>
                </div>
            </#if>
            <#--  <input id="businessPhotoUrlChangeFlag" value="0" type="hidden">
          <#if userInfoBean.businessPhotoUrlTobe??>
              <div class="">
                  <a href="${userInfoBean.businessPhotoUrlTobe}" target="_blank"><img
                          id="businessPhotoUrlTobe"
                          src="${userInfoBean.businessPhotoUrlTobe}"
                          style="max-width:200px"></a>
              </div>
              <#if userInfoBean.status == 3>
                  <a class="btn btn-primary btn-sm" id="businessPhotoUrlA"
                     onclick="photoUrlUpdate('#businessPhotoUrl')">更新</a>
              </#if>
          </#if>-->
            </td>
            <td class="ppad">
            <#if userInfoBean.dirverLicenceUrl??>
                <div class="">
                    <a href="${userInfoBean.dirverLicenceUrl}" target="_blank"><img
                            src="${userInfoBean.dirverLicenceUrl}" id="dirverLicenceUrl"
                            style="max-width:200px"></a>
                </div>
            </#if>
            <#--   <input id="dirverLicenceUrlChangeFlag" value="0" type="hidden">
           <#if userInfoBean.dirverLicenceUrlTobe??>
               <div class="">
                   <a href="${userInfoBean.dirverLicenceUrlTobe}" target="_blank"><img
                           src="${userInfoBean.dirverLicenceUrlTobe}" id="dirverLicenceUrlTobe"
                           style="max-width:200px"></a>
               </div>
               <#if userInfoBean.status == 3>
                   <a class="btn btn-primary btn-sm" id="dirverLicenceUrlA"
                      onclick="photoUrlUpdate('#dirverLicenceUrl')">更新</a>
               </#if></#if>-->
            </td>
        </tr>
        <tr class="info">
            <td>营运证</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="ppad">
            <#if userInfoBean.operatingPermitsPhotoUrl??>
                <a href="${userInfoBean.operatingPermitsPhotoUrl}" target="_blank"><img
                        src="${userInfoBean.operatingPermitsPhotoUrl}" id="operatingPermitsPhotoUrl"
                        style="max-width:200px"></a>
            </#if>
            <#--      <input id="operatingPermitsPhotoUrlChangeFlag" value="0" type="hidden">
              <#if userInfoBean.operatingPermitsPhotoUrlTobe??>
                  <a href="${userInfoBean.operatingPermitsPhotoUrlTobe}" target="_blank"><img
                          src="${userInfoBean.operatingPermitsPhotoUrlTobe}" id="operatingPermitsPhotoUrlTobe"
                          style="max-width:200px"></a>
                  <#if userInfoBean.status == 3>
                      <button type="button" class="btn btn-primary btn-sm" id="operatingPermitsPhotoUrlA"
                              onclick="photoUrlUpdate('#operatingPermitsPhotoUrl')">更新
                      </button>
                  </#if>
              </#if>-->
            </td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
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
            <#--<#if userInfoBean.businessCardUrlTobe??>-->
                <#--<div class="">-->
                    <#--<a href="${userInfoBean.businessCardUrlTobe}" target="_blank" title="原图片" >-->
                        <#--<img id="businessCardUrlTobe" id="businessCardTobe"-->
                             <#--src="${userInfoBean.businessCardUrlTobe}"-->
                             <#--style="max-width:300px;border-radius:50px"></a>-->
                    <#--<a href="#">原图片</a>-->
                <#--</div>-->
                <#--<input id="businessCardUrlChangeFlag" value="0" type="hidden">-->
            <#--</#if>-->
            </td>
            <td class="ppad">
            <#if userInfoBean.bigOrdersUrl??>
                <div class="">
                    <a href="${userInfoBean.bigOrdersUrl}" target="_blank"><img id="bigOrdersUrl"
                                                                                src="${userInfoBean.bigOrdersUrl}"
                                                                                style="max-width:300px"></a>
                </div>
            </#if>

            <#--<#if userInfoBean.bigOrdersUrlTobe??>-->
                <#--<div class="">-->
                    <#--<a href="${userInfoBean.bigOrdersUrlTobe}" target="_blank" title="原图片"><img-->
                            <#--id="bigOrdersTobe"-->
                            <#--src="${userInfoBean.bigOrdersUrlTobe}"-->
                            <#--style="max-width:300px;border-radius:50px"></a>-->
                    <#--<a href="#">原图片</a>-->
                <#--</div>-->
                <#--<input id="bigOrdersUrlChangeFlag" value="0" type="hidden">-->
            <#--</#if>-->
            </td>
        </tr>
    </table>
<div>
    <ul class="nav nav-tabs" role="tablist">
        <#--<li role="presentation" class="active"><a href="#tabuser" aria-controls="profile" role="tab"
                                                  data-toggle="tab">用户操作日志</a></li>-->
        <li role="presentation"><a href="#tabadmin" aria-controls="profile" role="tab" data-toggle="tab">冻结日志</a>
        </li>
    </ul>
</div>
<div class="tab-content">
    <#--<div id="tabuser" class="tab-pane active" role="tabpanel">
        <div class="bs-callout bs-callout-info" id="callout-labels-inline-block"><code>2016-6-17 12:52</code><br>
            选择司机 车主ID：15780323278
        </div>
        <div class="bs-callout bs-callout-info" id="callout-labels-inline-block"><code>2016-6-17 12:36</code><br>
            发布订单 订单号：2321432544324
        </div>
    </div>-->
  <div id="tabadmin" class="tab-pane active" role="tabpanel">
        <#list ahmList as auditHistory>
            <div class="bs-callout bs-callout-danger" id="callout--inline-block"><code>${auditHistory.createTime?string("yyyy-MM-dd HH:mm")}</code><br>
                ${auditHistory.auditName!}：${auditHistory.remark!}
            </div>
        </#list>
    </div>
</div>
</div>

<script type="text/javascript">
    var memberStatus = ${userInfoBean.memberStatus};
    //页面加载 如果处于冻结状态 则不显示更新按钮
    //memberStatus= 0 为黑名单为冻结 deleted = 1不是黑名单 自然不是冻结状态
    $(document).ready(function(){
        if(memberStatus == 0){//为黑名单为冻结  则不显示更新按钮
            $("#submit").attr("disabled", true);//禁用状态
        }else if(memberStatus == 1){//不是黑名单 自然不是冻结状态  则显示更新按钮
            $("#submit").attr("disabled", false);//启用状态
        }
    });
    $(function () {
        //常住地址
        $("#resident").citySelect({
            prov: $("#resident1").val(),
            city: $("#resident2").val(),
            dist: $("#resident3").val() == '' ? '1' : $("#resident3").val()
        });
        //路线一
        $("#businessLineFisrt").citySelect({
            prov: $("#businessLineFisrt1").val(),
            city: $("#businessLineFisrt2").val(),
            dist: $("#businessLineFisrt3").val() == '' ? '1' : $("#businessLineFisrt3").val()
        });
        //路线二
        $("#businessLineSecond").citySelect({
            prov: $("#businessLineSecond1").val(),
            city: $("#businessLineSecond2").val(),
            dist: $("#businessLineSecond3").val() == '' ? '1' : $("#businessLineSecond3").val()
        });
        //路线三
        $("#businessLineThird").citySelect({
            prov: $("#businessLineThird1").val(),
            city: $("#businessLineThird2").val(),
            dist: $("#businessLineThird3").val() == '' ? '1' : $("#businessLineThird3").val()
        });
        //路线四
        $("#businessLineForth").citySelect({
            prov: $("#businessLineForth1").val(),
            city: $("#businessLineForth2").val(),
            dist: $("#businessLineForth3").val() == '' ? '1' : $("#businessLineForth3").val()
        });
        //经营路线
        $("#businessAddressCity").citySelect({
            prov: $("#businessAddressCity1").val(),
            city: $("#businessAddressCity2").val(),
            dist: $("#businessAddressCity3").val() == '' ? '1' : $("#businessAddressCity3").val()
        });
//必写
        if ($("#walletId").val() != null && $("#walletId").val() != '' && $("#walletId").val() != 0) {
            $.ajax({
                type: 'post',
                url: getRootPath() + '/memberAction/memberDetailExtend.do?walletId=' + $("#walletId").val(),
                dataType: "json",
                success: function (data) {
                    $("#walletBalance").html(data.walletBalance);
                }
            });
        }
    });
//可以不要了
    /*function photoUrlUpdate(id) {
        var urlId = id;
        var urlChangeFlag = id + "ChangeFlag";
        var urlIdA = id + "A";
        var urlTobeId = id + "Tobe";
        var urlTobe = $(urlTobeId).attr("src");
        $(urlTobeId).parent().parent().css("display", "none");
        $(urlTobeId).attr("src", "");
        $(urlIdA).css("display", "none");
        $(urlId).attr("src", urlTobe);
        $(urlChangeFlag).val("1");
        $(urlId).parent().attr("href", urlTobe);
    }*/
    function update() {
        var bt = $('#submit').button('loading');
        if ($("#resident_2").val() == '') {
            bootbox.alert('常住地必须选二级城市', function (result) {
                bt.button('reset');
            });
            return;
        }
        var paras = {
            id: $("#userId").val(),
            idNo: $("#idNo").val(),
            userInfoVersion: $("#userInfoVersion").val(),
            companyName: $("#companyName").val(),
            businessAddressCity1: $("#businessAddressCity_1").val(),
            businessAddressCity2: $("#businessAddressCity_2").val(),
            businessAddressCity3: $("#businessAddressCity_3").val(),
            resident1: $("#resident_1").val(),
            resident2: $("#resident_2").val(),
            resident3: $("#resident_3").val(),
            telephone: $("#telephone").val(),
            userType: $("#type").val(),
            businessLineFisrt1: $("#businessLineFisrt_1").val(),
            businessLineFisrt2: $("#businessLineFisrt_2").val(),
            businessLineFisrt3: $("#businessLineFisrt_3").val(),

            businessLineFisrtId: $("#businessLineFisrtId").val().replace(/,/g, ""),
            businessLineSecondId: $("#businessLineSecondId").val().replace(/,/g, ""),
            businessLineThirdId: $("#businessLineThirdId").val().replace(/,/g, ""),
            businessLineForthId: $("#businessLineForthId").val().replace(/,/g, ""),

            /* businessPhotoUrl: $("#businessPhotoUrl").attr("src"),
             businessPhotoUrlChangeFlag: $("#businessPhotoUrlChangeFlag").val(),
             businessPhotoUrlTobe: $("#businessPhotoUrlTobe").attr("src"),
             businessPhotoVersion: $("#businessPhotoVersion").val(),
             shopFrontPhotoUrl: $("#shopFrontPhotoUrl").attr("src"),
             shopFrontPhotoUrlChangeFlag: $("#shopFrontPhotoUrlChangeFlag").val(),
             shopFrontPhotoUrlTobe: $("#shopFrontPhotoUrlTobe").attr("src"),
             shopFrontVersion: $("#shopFrontVersion").val(),
             dirverLicenceUrl: $("#dirverLicenceUrl").attr("src"),
             dirverLicenceUrlChangeFlag: $("#dirverLicenceUrlChangeFlag").val(),
             dirverLicenceUrlTobe: $("#dirverLicenceUrlTobe").attr("src"),
             photoInfoDrivingVersion: $("#photoInfoDrivingVersion").val(),
             operatingPermitsPhotoUrl: $("#operatingPermitsPhotoUrl").attr("src"),
             operatingPermitsPhotoUrlTobe: $("#operatingPermitsPhotoUrlTobe").attr("src"),
             operatingPermitsPhotoVersion: $("#operatingPermitsPhotoVersion").val(),
             operatingPermitsPhotoUrlChangeFlag: $("#operatingPermitsPhotoUrlChangeFlag").val(),*/

            businessLineSecond1: $("#businessLineSecond_1").val(),
            businessLineSecond2: $("#businessLineSecond_2").val(),
            businessLineSecond3: $("#businessLineSecond_3").val(),

            businessLineThird1: $("#businessLineThird_1").val(),
            businessLineThird2: $("#businessLineThird_2").val(),
            businessLineThird3: $("#businessLineThird_3").val(),

            businessLineForth1: $("#businessLineForth_1").val(),
            businessLineForth2: $("#businessLineForth_2").val(),
            businessLineForth3: $("#businessLineForth_3").val()
        };

        var url = getRootPath() + '/memberAction/updateUserInfo.do';
        $.ajaxDefaultCall(url, paras, function () {
            window.history.back(-1);
        });
    }
    ;
</script>
<#include "../main/footer.ftl">