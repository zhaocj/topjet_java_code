<#include "../main/top.ftl">
<script type="text/javascript" src="<@s.url '/js/common/jquery.cityselect.js'/>"></script>
<script type="text/javascript" src="<@s.url '/js/common/jquery.rotate.min.js'/>"></script>
<script type="text/javascript" src="<@s.url '/js/common/IDCardValidate.js'/>"></script>


<input type="hidden" id="userId" name="id" value="${userInfoBean.id?c}"/>
<input type="hidden" id="resident1" value="${userInfoBean.resident1!""}"/>
<input type="hidden" id="resident2" value="${userInfoBean.resident2!""}"/>
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

<input type="hidden" id="businessAddressCity1" value="${userInfoBean.businessAddressCity1!""}"/>
<input type="hidden" id="businessAddressCity2" value="${userInfoBean.businessAddressCity2!""}"/>
<input type="hidden" id="businessAddressCity3" value="${userInfoBean.businessAddressCity3!""}"/>

<input type="hidden" id="shopFrontVersion" name="shopFrontVersion" value="${userInfoBean.shopFrontVersion!""}"/>
<input type="hidden" id="operatingPermitsPhotoVersion" name="operatingPermitsPhotoVersion"  value="${userInfoBean.operatingPermitsPhotoVersion!""}"/>
<input type="hidden" id="businessPhotoVersion" name="businessPhotoVersion" value="${userInfoBean.businessPhotoVersion!""}"/>
<input type="hidden" id="photoInfoHeadVersion" name="photoInfoHeadVersion"  value="${userInfoBean.photoInfoHeadVersion!""}"/>
<input type="hidden" id="photoInfoIdNoVersion" name="photoInfoIdNoVersion" value="${userInfoBean.photoInfoIdNoVersion!""}"/>
<input type="hidden" id="userStatus" name="userStatus" value="${userInfoBean.status!""}"/>
<input type="hidden" id="reditRemark" name="reditRemark" value="${userInfoBean.reditRemark!""}"/>
<input type="hidden" id="auditFlag" name="auditFlag" value=""/>  <#--1表达实名认证通过，0表示失败-->

<div class="contentBox" style="padding:10px; font-size:14px">
    <form id="myForm">
    <div class="fixed-table-toolbar">
        <div class="navmenu"><a href="#" onclick="javascript:history.back(-1); return false">实名认证审核</a> / 审核资料</div>
        <div class="columns columns-left btn-group pull-left fontsize-14"><font>审核状态:</font>
            <!--只有超级管理员可以选择其它部门，部门主管添加用户，默认为本部门，不可修改-->
            <select id="selectAuditResult" name="selectAuditResult" data-toggle="select"
                    class="form-control select select-default select-sm select2-offscreen" tabindex="-1"   title="">
                <option value="2">审核通过</option>
                <option value="4">审核驳回</option>
            </select>
        </div>
        <div id="divSelectAuditResultMsg" class="columns columns-left btn-group pull-left fontsize-14"><font>原因:</font>
            <select id="selectAuditResultMsg" name="selectAuditResultMsg" data-toggle="select"
                    class="form-control select select-default select-sm select2-offscreen" style="width:350px"
                    tabindex="-1" title="">
                <option value="">请选择原因</option>
                <option value="身份证不清晰">身份证不清晰</option>
                <option value="头像不清晰(请提交正面清晰的生活照头像)">头像不清晰(请提交正面清晰的生活照头像)</option>
                <option value="请着装拍摄生活照头像">请着装拍摄生活照头像</option>
                <option value="该身份证已被注册">该身份证已被注册</option>
                <option value="库中无此号码">库中无此号码</option>
                <option value="非现场拍摄证件">非现场拍摄证件</option>
                <option value="拍摄生活照头像时请勿佩戴墨镜">拍摄生活照头像时请勿佩戴墨镜</option>
                <option value="请拍摄完整的身份证照片">请拍摄完整的身份证照片</option>
                <option value="请拍摄肩部以上头像照片">请拍摄肩部以上头像照片</option>
                <option value="请提交竖立生活照头像">请提交竖立生活照头像</option>
                <option value="">其它(客服手动填写)</option>
            </select>
        </div>
        <div id="divOtherReason" class="columns columns-left btn-group pull-left margin-right-10 fontsize-14">
            <input class="form-control input-sm pull-left" id="otherReason" style=" width:200px;" placeholder="其它原因"
                   type="text">
        </div>
        <div class="columns columns-left btn-group pull-right">
            <button type="button" id="submit" class="btn btn-primary btn-sm">提 交</button>
        </div>
    </div>
    <table class="table table-bordered">
        <tbody>
        <tr class="info">
            <td width="15%">姓名</td>
            <td width="15%">手机号</td>
            <td width="30%">身份证号</td>
            <td width="20%">身份</td>
        </tr>
        <tr>
            <td style="font-size:16px;font-weight: bold"><input type="text" id="name" style="width: 50%" required value="${userInfoBean.username }"><span id="setAge" >${userInfoBean.age }</span><span >岁</span></td>
            <td style="font-size:16px;"><input type="text" id="mobile" value="${userInfoBean.mobile }" readonly></td>
            <td style="font-size:16px;font-weight: bold"><input style="width:50%" type="text"  required id="idNo" value="${userInfoBean.idNo}" onblur="calculateAge()">　
                <button id="verifica"  onclick="verfiedId()"  type="button">实名认证
                </button>
                <font id="statusTitle">未认证</font>
            </td>
            <td style="font-size:16px;"><select id="type">
                <option value="1" style="color:#ad1b22" <#if userInfoBean.userType == 1>selected</#if>>司机/车主</option>
                <option value="2" style="color:#00ee00"  <#if userInfoBean.userType == 2>selected</#if>>货主</option>
            </select></td>
        </tr>

        <tr class="info">
            <td colspan="2">头像图片</td>
            <td colspan="2">身份证正面</td>
            <#--<td colspan="1">身份证反面</td>-->
        </tr>
        <tr>
            <td colspan="2">
            <#if userInfoBean.headPhotoUrl??>
            <div class="mag">
                <a id="headPhotoUrl" href="${userInfoBean.headPhotoUrl}" target="_blank"><img id="headPhotoUrl_img" data-toggle="magnify" src="${userInfoBean.headPhotoUrl}"
                                              alt="..."
                                              class="img-thumbnail img-responsive"
                                              style="max-width:300px"></a>
                </div>
                <br>
                <div class="btn-group margin-top-15" role="group">
                    <a type="headPhotoUrl" direct="left" class="btn btn-default btn-group-sm" title="逆时针旋转90度">
                        <li class="icon-undo"></li>
                    </a>
                    <a type="headPhotoUrl" direct="right" class="btn btn-default btn-group-sm" title="顺时针旋转90度">
                        <li class="icon-repeat"></li>
                    </a>
                </div>
            </#if>
            </td>

            <td colspan="2">
            <#if userInfoBean.idNoPhotoUrl??>
               <div class="mag">
                  <a id="idNoPhotoUrl" href="${userInfoBean.idNoPhotoUrl}" target="_blank"><img id="idNoPhotoUrl_img" data-toggle="magnify" src="${userInfoBean.idNoPhotoUrl}"
                                              alt="..."
                                              class="img-thumbnail img-responsive"
                                              style="max-width:300px"></a>
                </div>
                <br>
                <div class="btn-group margin-top-15" role="group">
                    <a type="idNoPhotoUrl" direct="left" class="btn btn-default btn-group-sm" title="逆时针旋转90度">
                        <li class="icon-undo"></li>
                    </a>
                    <a type="idNoPhotoUrl" direct="right" class="btn btn-default btn-group-sm" title="顺时针旋转90度">
                        <li class="icon-repeat"></li>
                    </a>
                </div>
            </#if>
            </td>
        </tr>
        </tbody>
    </table>
        <div id = "operaLog">
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="#">操作日志</a></li>
            </ul>
            <div id="tabadmin" class="tab-pane" role="tabpanel">
                <#list memberDetails as memberHistory>
                    <div class="bs-callout bs-callout-danger" id="callout-labels-inline-block"> <code>${(memberHistory.auditCreateTime?string("yyyy-MM-dd HH:mm:ss"))!""}</code><br>
                    ${(memberHistory.auditName)!""}：${type} ${(memberHistory.remark)!""} </div>
                </#list>

            </div>
        </div>
        <input type="hidden" id="status" name="status" value="${userInfoBean.useStatus}">
    </form>
</div>
<script type="text/javascript">
    //页面加载
  /*  $(document).ready(function(){
        if($("#status").val() == 1){
            $("#operaLog").hide();
        }else{
            $("#operaLog").show();
        }
    });*/
    $(function () {
        $("#divSelectAuditResultMsg").hide();
        $("#divOtherReason").show();
        $("#otherReason").val("审核通过");
        <!--证件图片翻转Begin-->
        var ammeter = 0;
        $("a").click(function () {
            if ($(this).attr("direct") == "left") {
                ammeter = ammeter - 90;
            }
            if ($(this).attr("direct") == "right") {
                ammeter = ammeter + 90;
            }
            $("#" + $(this).attr("type") + "_img").rotate(ammeter);
            $("#" + $(this).attr("type") + "_img").next().rotate(ammeter);
        });
        <!--证件图片翻转End-->

        //提交接口
        $("#submit").click(function () {
            var bt = $('#submit').button('loading');
            if ($("#selectAuditResult").val() == "4" && $("#selectAuditResultMsg").val() == "" && $("#otherReason").val() == "") {
                bootbox.alert({
                    size: 'small',
                    message: "请选择驳回的原因或者填写审核原因",
                });
                bt.button('reset');
                return false;
            }


            var idNo = $("#idNo").val();
            if($("#selectAuditResult").val()=="2"){

                var result = getIdCardInfo(idNo);
                if (result.isTrue != true) {
                    bootbox.alert({
                        size: 'small',
                        message: "不是有效的身份证号码",
                    });
                    bt.button('reset');
                    return;
                }

                if($("#auditFlag").val()!="1"){
                    bootbox.alert({
                        size: 'small',
                        message: "没有验证通过的身份证禁止审核通过!",
                    });
                    bt.button('reset');
                    return false;
                }
                var idNophotoUrl = $("#idNoPhotoUrl_img").attr("src");
              /*  if (idNophotoUrl == undefined || idNophotoUrl == "") {
                    bootbox.alert('请上传身份证', function(result){
                        bt.button('reset');
                    });
                    return ;
                }*/
//                var idNoNegativePhotoUrl = $("#idNoNegativePhotoUrl_img").attr("src");
//                if (idNoNegativePhotoUrl == undefined || idNoNegativePhotoUrl == "") {
//                    bootbox.alert('请上传身份证', function(result){
//                        bt.button('reset');
//                    });
//                    return ;
//                }
                var headPhotoUrl = $("#headPhotoUrl_img").attr("src");
               /* if (headPhotoUrl == undefined ||  headPhotoUrl == "") {
                    bootbox.alert('请上传头像',function(result){
                        bt.button('reset');
                    });
                    return ;
                }
*/
                /*if(($("#businessLineFisrt_1").val() != '' && $("#businessLineFisrt_2").val() =='') || ($("#businessLineSecond_1").val() != '' && $("#businessLineSecond_2").val() =='') || ($("#businessLineThird_1").val() != '' && $("#businessLineThird_2").val() =='') || ($("#businessLineForth_1").val() != '' && $("#businessLineForth_2").val() =='')){
                    bootbox.alert('经营地址至少选择二级城市或不填',function(result){
                        bt.button('reset');
                    });
                    return ;
                }*/

                if ($("#type").val() == '1') {
                    if (userAge < 20 || userAge > 60) {
                        bootbox.alert({
                            size: 'small',
                            message: "司机必须为20-60周岁",
                        });
                        bt.button('reset');
                        return;
                    }
                } else if ($("#type").val() == '2') {
                   // var msg = ($("#type").val() == '2')?"货主必须为18-65周岁":(($("#type").val() == '3')?"信息部人员必须为18-65周岁":"物流公司人员必须为18-65周岁");
                    if (userAge < 18 || userAge > 65) {
                        bootbox.alert({
                            size: 'small',
                            message: "货主必须为18-65周岁",
                        });
                        bt.button('reset');
                        return;
                    }
                }else if($("#type").val() == '3' || $("#type").val() == '4'){
                        bootbox.alert({
                            size: 'small',
                            message: "用户类型只有司机或车主",
                        });
                        bt.button('reset');
                        return;
                }
            }/*else{
                if(idNo!=""){
                    var result = getIdCardInfo(idNo);
                    if (result.isTrue != true) {
                        bootbox.alert({
                            size: 'small',
                            message: "不是有效的身份证号码",
                        });
                        bt.button('reset');
                        return;
                    }
                }
            }*/


            var paras = getParas();
            var url = getRootPath() + '/memberAudit/updateUserInfo.do';
           /* $.ajaxDefaultCall(url,paras,function () {
                location.href = "../view/memberAudit/list.html"
            });*/
            $.ajax({
                type:"POST",
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                url:url,
                data:paras,
                dataType:"json",
                success:function(data){
                    alert(data.msg);
                    location.href = "../view/memberAudit/list.html";
                }
            });
        });
        $("#selectAuditResult").change(function () {
            if ($(this).val() == "2") {
                $("#divSelectAuditResultMsg").hide();
                $("#divOtherReason").show();
                $("#selectAuditResultMsg").val("");
                $("#otherReason").val("审核通过");
            }else {
                $("#divSelectAuditResultMsg").show();
                $("#otherReason").val("");
                if($("#selectAuditResultMsg").val()==""){
                    $("#divOtherReason").show();
                }else{
                    $("#divOtherReason").hide();
                }
            }
        });
        $("#selectAuditResultMsg").change(function () {
            if ($(this).val() != "" && $("#selectAuditResult").val() == "4")  {
                $("#divOtherReason").hide();
            }else {
                $("#divOtherReason").show();
            }
        });
    });
    //验证身份证

    var userAge;
    function calculateAge() {
        var idNo = $("#idNo").val();
        var birthDate = idNo.substring(6,10);
        var year=new Date().getFullYear();
        var age=year - birthDate;
        if(age>1000){
            age = 0;
        }
        userAge = parseInt(age);
        $("#setAge").text(age);
    };

    function verfiedId() {
        calculateAge();
        var bt = $('#verifica').button('loading');
        var idNo = $("#idNo").val();
        var result = getIdCardInfo(idNo);

        if ($("#name").val()=='') {
            bootbox.alert({
                size: 'small',
                message: "请填写用户名",
            });
            bt.button('reset');
            return;
        }
        if ($("#type").val() == '1') {
            if (userAge < 20 || userAge > 60) {
                bootbox.alert({
                    size: 'small',
                    message: "司机必须为20-60周岁",
                });
                bt.button('reset');
                return;
            }
        } else if ($("#type").val() == '2') {
           // var msg = ($("#type").val() == '2')?"货主必须为18-65周岁":(($("#type").val() == '3')?"信息部人员必须为18-65周岁":"物流公司人员必须为18-65周岁");
            if (userAge < 18 || userAge > 65) {
                bootbox.alert({
                    size: 'small',
                    message: "货主必须为18-65周岁",
                });
                bt.button('reset');
                return;
            }
        }else if($("#type").val() == '3' || $("#type").val() == '4'){
            bootbox.alert({
                size: 'small',
                message: "用户类型只有司机或车主",
            });
            bt.button('reset');
            return;
        }

        if (result.isTrue != true) {
            bootbox.alert({
                size: 'small',
                message: "不是有效的身份证号码",
            });
            bt.button('reset');
            return;
        }

        var url = getRootPath() + '/memberAudit/verifyIdNo.do';
        var paras = getParas();
        $.ajaxDefaultCall(url,paras,function (data) {
            if (data.status == "02") {
                $("#statusTitle").text("已通过").removeClass("text-danger").addClass("text-success");
                $("#auditFlag").val('1');
            }else{
                $("#statusTitle").text("未认证").removeClass("text-success").addClass("text-danger");
                $("#auditFlag").val('0');
            }
            bt.button('reset');
        });
    }
    //组织参数
    function getParas() {
        var paras = {
            auditResult:$("#selectAuditResult").val(),
            auditResultMsg: $("#selectAuditResultMsg").val()+$("#otherReason").val(),
            id: $("#userId").val(),
            mobile: $("#mobile").val(),
            username: $("#name").val(),
            idNo: $("#idNo").val(),
            userType: $("#type").val(),
            userStatusOlder: $("#userStatus").val(),
//            userInfoVersion: $("#userInfoVersion").val(),
            photoInfoIdNoVersion: $("#photoInfoIdNoVersion").val(),
            photoInfoHeadVersion: $("#photoInfoHeadVersion").val()
        };
        return paras;
    };
</script>
<#include "../main/footer.ftl">