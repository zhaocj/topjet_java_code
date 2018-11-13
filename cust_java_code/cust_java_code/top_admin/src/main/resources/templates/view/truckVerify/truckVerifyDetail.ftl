<#include "../main/top.ftl">
<div class="contentBox" style="padding:10px; font-size:14px">
    <input value="${data.id?c}" type="hidden" name="id" id="id">
    <#--<input value="${data.version}" type="hidden" name="version" id="version">-->
    <#--<input value="${data.truckTypeId}" type="hidden" name="truckTypeId" id="truckTypeId">-->
    <input value="${data.plateNo}" type="hidden" name="plateNo" id="plateNo">
    <div class="fixed-table-toolbar">
        <div class="navmenu"><a href="#" onclick="javascript:history.back(-1); return false">车辆认证审核</a> / 查看资料</div>
        <div class="columns columns-left btn-group pull-left"><font>　审核状态:</font>
            <select name="auditStatus" data-toggle="select" class="form-control select select-default select-sm" id="auditStatus">
              <#--  <option value="">选择审核结果</option>
            <#if data.auditStatus??>-->
                <option value="2" <#if data.auditStatus==2>selected</#if>>认证通过</option>
                <option value="4" <#if data.auditStatus==4>selected</#if>>认证失败</option>
           <#-- <#else>
                <option value="2">认证通过</option>
                <option value="4">认证失败</option>
            </#if>-->
            </select>
        </div>
        <div class="columns columns-left btn-group pull-left" style="display:none;" id="reasonBox1">
            <font class="margin-right-5">原因:</font>
            <select id="reasonSelect" data-toggle="select" class="form-control select select-default select-sm" style="width: 250px;">
                <option value="">请选择原因</option>
                <option value="行驶证过期">行驶证过期</option>
                <option value="请提交行驶证正副本">请提交行驶证正副本</option>
                <option value="行驶证正副本不一致">行驶证正副本不一致</option>
                <option value="行驶证车牌号与填写不一致">行驶证车牌号与填写不一致</option>
                <option value="非现场拍摄证件">非现场拍摄证件</option>
                <option value="">其它(客服手动填写)</option>
            </select>
        </div>
        <div class="columns columns-left btn-group  margin-right-10 fontsize-14">
            <input class="form-control input-sm pull-left" style=" width:300px;" name="operatorRemark"
                   id="operatorRemark" placeholder="其它原因" value="${data.operatorRemark!}" type="text">
        </div>

        <div class="columns columns-left btn-group pull-right">
            <#if data.auditStatus == 3>
                <button type="button" class="btn btn-primary btn-sm" onclick="tijiao()">提交</button>
            </#if>
        </div>
    </div>

    <table class="table table-bordered">
        <tr class="info">
            <td width="25%">车主姓名</td>
            <td width="25%">手机号</td>
            <td width="25%">账号注册日期</td>
            <td width="25%">车辆提交审核日期</td>
        </tr>
        <tr>
            <td>${data.name!""}</td>
            <td>${data.mobile!""}</td></td>
            <td>${data.userCreateTime!""}
            <td>${data.truckCreateTime!""}</td>
        </tr>
        <tr class="info">
            <td>车牌号</td>
            <td>车牌颜色</td>
            <#--<td>车龄</td>-->
            <td>车型车长</td>
        </tr>
        <tr>
            <td>

                <select name="plateNo1" id="plateNo1">
                    <option value="">请选择</option>
                </select>
                -
                <select name="plateNo2" id="plateNo2">
                    <option value="">请选择</option>
                </select>
                -
                <input type="text" name="plateNo3" id="plateNo3">
            </td>
            <td>
                <select name="truckColor" id="truckColor">
                    <option value="1" <#if data.truckColor=='1'>selected</#if>>蓝牌</option>
                    <option value="2" <#if data.truckColor=='2'>selected</#if>>黄牌</option>
                </select>
            </td>
           <#-- <td>
                <input type="text" name="truckAge" id="truckAge" placeholder="请输入车龄" value="${data.truckAge}"
                       onblur="valid()">
            </td>-->
            <td>
                <select name="TruckTypedisplayName" id="TruckTypedisplayName" >
                    <option value="">请选择车型</option>
                </select>
                -
                <select name="truckLength" id="truckLength">
                    <option value="">请选择车长</option>
                </select>
            </td>
        </tr>
        <tr class="info">
            <td colspan="2">车头照图片</td>
            <td colspan="2">行驶证图片</td>
        </tr>

        <tr>
            <td >
                <#if data.truckPhotoUrl??>
                <div class="mag">
                    <a href="${data.truckPhotoUrl}" target="_blank"><img data-toggle="magnify"
                        src="${data.truckPhotoUrl}" id="truckPhotoUrl_img" height="270px" width="270px">
                    </a>
                </div>
                </#if>
                    <br>
                    <div class="btn-group margin-top-15" role="group">
                        <a class="btn btn-default btn-group-sm" title="逆时针旋转90度" type="truckPhotoUrl" direct="left">
                            <li class="icon-undo"></li>
                        </a>
                        <a class="btn btn-default btn-group-sm " title="顺时针旋转90度" type="truckPhotoUrl" direct="right">
                            <li class="icon-repeat"></li>
                        </a>
                    </div>
            </td>
            <td></td>
            <td >
                <#if data.drivingLicensePhotoUrl??>
                    <div class="mag">
                        <a href="${data.drivingLicensePhotoUrl}" target="_blank"><img data-toggle="magnify"
                         src="${data.drivingLicensePhotoUrl}" id="drivingLicensePhotoUrl_img" height="270px" width="270px">
                        </a>
                    </div>
                </#if>
                    <br>
                    <div class="btn-group margin-top-15" role="group">
                        <a class="btn btn-default btn-group-sm" title="逆时针旋转90度" type="drivingLicensePhotoUrl"
                           direct="left">
                            <li class="icon-undo"></li>
                        </a>
                        <a class="btn btn-default btn-group-sm " title="顺时针旋转90度" type="drivingLicensePhotoUrl"
                           direct="right">
                            <li class="icon-repeat"></li>
                        </a>
                    </div>
            </td>
        </tr>
</tr>
    </table>
   <#-- <div>
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="#">操作日志</a></li>
        </ul>
        <div id="tabadmin" class="tab-pane" role="tabpanel">
            <div class="bs-callout bs-callout-danger" id="callout-labels-inline-block"><code>2016-6-17 12:52</code><br>
                彭佳军：修改评价内容 修改原因(非法词组)
            </div>
            <div class="bs-callout bs-callout-danger" id="callout-labels-inline-block"><code>2016-6-17 12:36</code><br>
                彭佳军：车型车长
            </div>
        </div>
    </div>-->
</div>
</div>
</div>
<#include "../main/footer.ftl">
<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content"></div>
    </div>
</div>
<script type="text/javascript" src="../js/common/jquery.rotate.min.js"></script>
<script type="text/javascript" src="../js/common/licensePlate.js"></script>
<script>
    var truckTypeJson = [];
    var truckLengthJson = [];
    var truckTypeOption ="";
    var truckLengthOption ="";
    var typeId = ${data.truckType};
    var lengthId = ${data.truckLength};
    $(function () {
        $.ajax({
            type:"POST",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            url:getRootPath()+'/commonAction/getTruckData.do',
            data:[],
            dataType:"json",
            cache:false,
            traditional:true,
            success:function(list){
                if(list != null && list.length>0){
                    $.each(list[0],function (index,data2) {
                        truckTypeJson.push({'id':data2.id,'code':data2.code,'displayName':data2.displayName});
                    });
                    $.each(list[1],function (index,data1) {
                        truckLengthJson.push({'id':data1.id,'length':data1.length,'displayName':data1.displayName});
                    });

                    $.each(truckTypeJson,function (indx,length1) {
                        if(typeId == length1['id']){
                            truckTypeOption+="<option value='"+length1['id']+"' selected>"+length1['displayName']+"</option>";
                        }else{
                            truckTypeOption+="<option value='"+length1['id']+"'>"+length1['displayName']+"</option>";
                        }
                    });
                    $.each(truckLengthJson,function (indx,length) {
                        if(lengthId==length['id']){
                            truckLengthOption+="<option value='"+length['id']+"'  selected>"+length['displayName']+"</option>";
                        }else{
                            truckLengthOption+="<option value='"+length['id']+"'>"+length['displayName']+"</option>";
                        }
                    });

                }
                $('#TruckTypedisplayName').append(truckTypeOption);
                $('#truckLength').append(truckLengthOption);
            }
        });
    })
    //页面加载
    $(document).ready(function(){
        if($("#auditStatus").val() == 2){
            $("#operatorRemark").val("认证通过");
        }else{
            $("#operatorRemark").val("");
        }
    });
    var ammeter = 0;
    $("a").click(function () {
        if ($(this).attr("direct") == "left") {
            ammeter = ammeter - 90;
        }
        if ($(this).attr("direct") == "right") {
            ammeter = ammeter + 90;
        }
        $("#" + $(this).attr("type") + "_img").rotate(ammeter);
    });

    $("#auditStatus").change(function () {
        if ($(this).val() == "2") {
            $("#operatorRemark").val("认证通过");
            $("#reasonBox1").hide();
        }else {
            $("#reasonBox1").show();
            $("#operatorRemark").val($("#reasonSelect").val());
        }
    });

    $("#reasonSelect").change(function () {
        $("#operatorRemark").val($(this).val());
    });

    function valid() {
        if (!(/^[0-9][0-9]?$/.test($("#truckAge").val()))) {
            bootbox.alert({
                size: 'larger',
                message: '车龄在0—99年之间',
            });
            $("#truckAge").val("");
            return;
        }
    }

    function tijiao() {
        if ($("#auditStatus").val()==null ||$("#auditStatus").val()=="") {
            bootbox.alert({
                size: 'larger',
                message: '请选择审核状态'
            });
            return;
        }
       /* if($("#auditStatus").val() == 4){
            bootbox.alert({
                size: 'larger',
                message: '认证失败请输入原因'
            });
            return;
        }*/
        var reasonStr = $.trim($("#operatorRemark").val());
        if (reasonStr==null||reasonStr=="") {
            bootbox.alert({
                size: 'larger',
                message: '请输入修改原因'
            });
            return;
        }
        if ($("#plateNo3").val()==null ||$("#plateNo3").val()=="") {
            bootbox.alert({
                size: 'larger',
                message: '车牌号不能为空',
            });
            $("#plateNo3").val("");
            return;
        }
        if ($("#plateNo3").val().length>6 ) {
            bootbox.alert({
                size: 'larger',
                message: '车牌号在6位数之间',
            });
            $("#plateNo3").val("");
            return;
        }
        /*if (!(/^[0-9][0-9]?$/.test($("#truckAge").val()))) {
            bootbox.alert({
                size: 'larger',
                message: '车龄在0—99年之间',
            });
            $("#truckAge").val("");
            return;
        }*/
        var x = $("#id").val().split(',');
        var id = parseFloat(x.join(""));
        var plNo = $("#plateNo1 option:selected").text() + $("#plateNo2 option:selected").text() + $("#plateNo3").val();
        var url = getRootPath() + '/truckVerify/updateTruckVerify.do?';
        var dataJson = {
            'id': id,
            /*'version': $("#version").val(),*/
            'auditStatus': $("#auditStatus").val(),
            'operatorRemark': $("#operatorRemark").val(),
            'plateNo': plNo,
            'truckColor': $("#truckColor").val(),
//            'truckAge': $("#truckAge").val(),
            "truckType":$("#TruckTypedisplayName").val(),
            "truckLength":$("#truckLength").val()
        };
        bootbox.confirm("确认修改车辆认证内容？", function (result) {
            if (result) {
                $.ajaxDefaultCall(url, dataJson, function () {
                    history.back(-1);
                });
            }
        })
    }
</script>