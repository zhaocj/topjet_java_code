<#include "../main/top.ftl">
<form id="myForm">
    <input type="hidden" id="id" value="${messageSendModel.id}">
    <div class="modal-body  fontsize-14">
        <div class="col-xs-6">
            <div class="form-group">
                <label>推送方式</label>
                <select id="sendType" name="sendType" data-toggle="select" class="form-control select select-default select-sm">
                    <option value="1" <#if messageSendModel.sendType == 1>selected</#if>>APP推送</option>
                    <option value="2" <#if messageSendModel.sendType == 2>selected</#if>>短信推送</option>
                </select>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>推送范围</label>
                <select id="memberType" name="sendType" data-toggle="select" onchange="forbidden(this.value)" class="form-control select select-default select-sm">
                    <option value="1" <#if messageSendModel.memberType == 1>selected</#if>>所有用户</option>
                    <option value="2" <#if messageSendModel.memberType == 2>selected</#if>>ios、android货主</option>
                    <option value="3" <#if messageSendModel.memberType == 3>selected</#if>>ios货主</option>
                    <option value="4" <#if messageSendModel.memberType == 4>selected</#if>>android货主</option>
                    <option value="5" <#if messageSendModel.memberType == 5>selected</#if>>ios、android司机</option>
                    <option value="6" <#if messageSendModel.memberType == 6>selected</#if>>ios司机</option>
                    <option value="7" <#if messageSendModel.memberType == 7>selected</#if>>android司机</option>
                    <option value="8" <#if messageSendModel.memberType == 8>selected</#if>>单人推送</option>
                </select>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group"  style="display:none;">
                <label>会员注册开始时间</label>
                <input type="text" name="registrationStartTime" id="registrationStartTime" onchange="changeChoice1(this.value)" class="form-control input-sm" value="${(messageSendModel.registrationStartTime?string("yyyy-MM-dd HH:mm"))!''}"  placeholder="XX时间之后注册的用户(默认不限)"><!--不能小于当前时间不能开启-->
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group"  style="display:none;">
                <label>会员注册结束时间</label>
                <input type="text" name="registrationEndTime" id="registrationEndTime" onchange="changeChoice1(this.value)"  class="form-control input-sm"  value="${(messageSendModel.registrationEndTime?string("yyyy-MM-dd HH:mm"))!''}" placeholder="XX时间之后注册的用户(默认不限)"><!--不能小于当前时间不能开启-->
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group"  style="display:none;">
                <label>签收次数</label>
                <input type="text" id="transactionCount" name="transactionCount" onchange="changeChoice1(this.value)"  class="form-control input-sm"  value="${messageSendModel.transactionCount}" placeholder="大于多少签收数(默认不限)">
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group" style="display:none;">
                <label>注册后几天无登陆</label>
                <input type="text" id="unlogDayNum" name="unlogDayNum" value="${messageSendModel.unlogDayNum?default("0")}" onchange="changeChoice(this.value)" class="form-control input-sm"   placeholder="用户注册后几天未登陆">
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>会员手机号</label>
                <input type="text" id="mobile" name="mobile" onblur='checkMobile()' class="form-control input-sm"  value="${messageSendModel.mobile}" placeholder="会员手机号">
            </div>
        </div>
        <div class="col-xs-12">
            <div class="form-group">
                <label>推送详情</label>
                <textarea class="form-control" id="content"  name="content" rows="3"   cols="50" required  placeholder="推送消息内容描述">${messageSendModel.content}</textarea>
            </div>
        </div>
        <div class="clearfix"></div>
        <div class="modal-footer margin-top-15">
            <button type="button" class="btn btn-primary center-block" type="submit" id="submitButton"
                    onclick="confirmSubmit()">　提　交　
            </button>
        </div>
</form>
<#include "../main/footer.ftl">
<script type="text/javascript">
    $(document).ready(function(){
        if($('#memberType').val()!=8){
            $('#mobile').attr("disabled",true);
        }else{
            $('#registrationStartTime').attr("disabled",true);
            $('#registrationEndTime').attr("disabled",true);
            $('#transactionCount').attr("disabled",true);
            $('#unlogDayNum').attr("disabled",true);
        }
    });
    $("#registrationStartTime,#registrationEndTime").datetimepicker({
        format: "yyyy-mm-dd hh:ii",
        todayBtn: true,
        autoclose: true,
        todayHighlight: true,
        showMeridian: true,
        pickerPosition: "bottom-left",
        language: 'zh-CN',//中文，需要引用zh-CN.js包
        startView: 2,//月视图
        minView: 0//日期时间选择器所能够提供的最精确的时间选择视图
    });

    function confirmSubmit() {
        if (IsFormChangeTrue("myForm")) {
            var url = getRootPath() + '/messageSend/update.do';
            var jsonBeginDate = {};
            var jsonEndDate = {};
            if (!isNull($('#registrationStartTime').val())) {
                jsonBeginDate = {"registrationStartTime": StringToDate($('#registrationStartTime').val())};
            }
            if (!isNull($('#registrationEndTime').val())) {
                jsonEndDate = {"registrationEndTime": StringToDate($('#registrationEndTime').val())};
            }
            if($("#memberType").val()==8 && $('#mobile').val().length==0){
                bootbox.alert({
                    size: 'small',
                    message: "请输入用户手机号!",
                });
                bt.button('reset');
                return;
            }

            var json = {
                'id':${messageSendModel.id},
                'version':${messageSendModel.version},
                'sendType': $("#sendType").val(),
                'memberType': $("#memberType").val(),
                'transactionCount': $('#transactionCount').val(),
                'content': $('#content').val(),
                'mobile': $('#mobile').val(),
                'unlogDayNum':$('#unlogDayNum').val(),
            }
            var data = $.extend({}, json, jsonBeginDate, jsonEndDate);
            window.parent.$.ajaxDefaultCall(url, data, function () {
                window.parent.location.href = getRootPath() + '/view/messageSend/list.html'
            });
        }else{
            window.parent.location.href = getRootPath() + '/view/messageSend/list.html'
        }

    }
    function forbidden(value) {
        if(value!=8){
            $('#mobile').attr("disabled",true);
            $('#mobile').val("");
            $('#registrationStartTime').attr("disabled",false);
            $('#registrationEndTime').attr("disabled",false);
            $('#transactionCount').attr("disabled",false);
            $('#unlogDayNum').attr("disabled",false);
        }else{
            $('#mobile').attr("disabled",false);
            $('#registrationStartTime').attr("disabled",true);
            $('#registrationEndTime').attr("disabled",true);
            $('#transactionCount').attr("disabled",true);
            $('#unlogDayNum').attr("disabled",true);
            $('#registrationStartTime').val("");
            $('#registrationEndTime').val("");
            $('#transactionCount').val("");
            $('#unlogDayNum').val("");
        }
    }

    function changeChoice(value){
        if(value == ""){
            $('#registrationStartTime').attr("disabled",false);
            $('#registrationEndTime').attr("disabled",false);
            $('#transactionCount').attr("disabled",false);
        }else{
            $('#mobile').attr("disabled",true);
            $('#registrationStartTime').attr("disabled",true);
            $('#registrationEndTime').attr("disabled",true);
            $('#transactionCount').attr("disabled",true);
            $('#registrationStartTime').val("");
            $('#registrationEndTime').val("");
            $('#transactionCount').val("");
        }
    }

    function changeChoice1(value) {
        var v1 = $('#registrationStartTime').val();
        var v2 = $('#registrationEndTime').val();
        var v3 = $('#transactionCount').val();
        if(v1 =="" && v2=="" && v3==""){
            $('#unlogDayNum').attr("disabled",false);
        }else{
            $('#mobile').attr("disabled",true);
            $('#unlogDayNum').attr("disabled",true);
            $('#unlogDayNum').val("");
            $('#mobile').val("");
        }
    }

    function checkMobile(){
        var mobile=$("#mobile").val()+"";
        if(!(/^1[3|4|5|7|8][0-9]\d{4,8}$/.test($("#mobile").val()))){
            $("#mobile").val("");
            return false;
        }
        $.ajax({
            type: "POST",
            url : getRootPath() + '/stationManage/checkMobile.do',
            data: mobile,
            contentType:"application/json",
            success: function(data){
                if (data.success == "false") {
                    bootbox.alert({
                        size: 'small',
                        message: "该手机号非平台注册手机号，请重新输入!",
                    });
                    $("#mobile").val("");
                    return false;
                }else{
                    $('#userId').val(data.userId);
                }
            }
        })
    }
</script>
