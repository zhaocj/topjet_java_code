<#include "../main/top.ftl">
<div class="contentBox" style="padding:10px">
    <div class="fixed-table-toolbar">
        <div class="navmenu"><a href="#" onclick="javascript:history.back(-2); return false">广告管理</a> /
        <#switch aim.appType>
            <#case 3> <a href="#" onclick="javascript:history.back(-1); return false">IOS接货版</a>/ 修改内容<#break>
            <#case 1><a href="#" onclick="javascript:history.back(-1); return false">Android接货版</a>/ 修改内容<#break>
        </#switch>
        </div>
        <div class="columns columns-left btn-group pull-right">
            <button type="button" id="myButton" class="btn btn-primary btn-sm margin-left-10" onclick="update()">提交修改</button>
        </div>
    </div>
    <div class="clearfix line1px"></div>
    <div class="container-fluid">
        <form id="myForm">
            <div class="row">
                <div class="col-xs-6">
                    <div class="form-group">
                        <label>广告位置</label>
                        <input type="text" name="indexNumber" id="indexNumber" onblur='checkValue1(this)' class="form-control" value="${aim.indexNumber}">
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group">
                        <label>web标题</label>
                        <input type="text" name="webTitle" id="webTitle" class="form-control" value="${aim.webTitle}">
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group">
                        <label>开始时间</label>
                        <input size="16" type="text" id="beginDate" name="beginDate" value="${aim.beginDate?string("yyyy-MM-dd HH:mm")}" placeholder="默认不限"
                               class="form-control input-sm ">
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group">
                        <label>结束时间</label>
                        <input size="16" type="text" id="endDate" name="endDate" value="${aim.endDate?string("yyyy-MM-dd HH:mm")}" placeholder="默认不限"
                               class="form-control input-sm ">
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group">
                        <label>状态</label>
                        <select id="valid" name="valid" data-toggle="select"
                                class="form-control select select-default select-sm select2-offscreen" tabindex="-1"
                                title="">
                            <option value="1" <#if aim.valid == 1>selected</#if>>开启</option>
                            <option value="0" <#if aim.valid == 0>selected</#if>>关闭</option>
                        </select>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group">
                        <label>链接</label>
                        <input type="text" name="turnURL" id="turnURL" class="form-control" value="${aim.turnURL}">
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group">
                        <label>上传图片</label>
                        <div class="input-group">
                            <input id="fileId1" type="file"      onchange="readFile(this,'pictureURLId','fileImageId1')">
                            <img id="fileImageId1" src="${aim.pictureUrl!}" width="300" height="300" class="topjet-img-size"/>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
        </form>
    </div>
</div>
<input type="hidden" id="pictureURLId" name="pictureURL" value="${aim.pictureKey!}">
<input type="hidden" id="id" name="id" value="${aim.id?c}">
<input type="hidden" id="appType" name="appType" value="${aim.appType}">
<script type="text/javascript">
    $(function(){
        $.topjectValidate('myForm');
        if(typeof FileReader==='undefined'){
            bootbox.alert({
                size : 'small',
                message :"浏览器不支持图片预览，请更换浏览器!",
            });
        }
    });


    $("#beginDate,#endDate").datetimepicker({
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

    function validDate(){
        var beginDate = StringToDate($('#beginDate').val());
        var endDate = StringToDate($('#endDate').val());
        if(endDate<=beginDate){
            bootbox.alert({
                size : 'small',
                message :"结束日期不能小于开始日期!",
            });
            $('#endDate').val($("endDateOlder").val());
            return false;
        }
    }
    function readFile(str,fileIds,fileImageId){
        var file = str.files[0];
        if(!/image\/\w+/.test(file.type)){
            bootbox.alert({
                size : 'small',
                message :"只能上传图片，请从新上传!",
            });
            return false;
        }
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function(e){
            var imge=e.target.result;
            $('#'+fileIds).val(imge);
            $("#"+fileImageId).attr("src",this.result);

        };
    }
    function update(){
        var bt=$('#myButton').button('loading');
        if(!$.topjectIsValidate('myForm')){
            bt.button('reset');
            return;
        }
        var beginDate = StringToDate($('#beginDate').val());
        var endDate = StringToDate($('#endDate').val());
        if(endDate<beginDate){
            bootbox.alert({
                size : 'small',
                message :"结束日期不能小于开始日期!",
            });
            bt.button('reset');
            return false;
        }
        var jsonBeginDate={};
        var jsonEndDate={};
        if(!isNull($('#beginDate').val())){
            jsonBeginDate = {"beginDate":StringToDate($('#beginDate').val())} ;
        }
        if(!isNull($('#endDate').val())){
            jsonEndDate= {"endDate":StringToDate($('#endDate').val())} ;
        }
        var json = {
            'id':$("#id").val(),
            'valid':$("#valid").val(),
            'turnURL':$("#turnURL").val(),
            'pictureKey':$("#pictureURLId").val(),
            'webTitle':$("#webTitle").val(),
            'appType':$("#appType").val(),
            'indexNumber':$('#indexNumber').val(),
        }
        var data = $.extend({},json,jsonBeginDate,jsonEndDate);
        var url = getRootPath()+'/orderListAdvertAction/update.do';
        window.parent.$.ajaxDefaultCall(url,data,function () {
            window.history.back();
        });
    }

    function checkValue1(obj){
        if ( !(/^[1-9]\d*|0$/.test(obj.value)) ) {
            bootbox.alert({
                size: 'larger',
                message: '请输入整数！',
            });
            $(obj).val("");
            return;
        }
    }



</script>
<#include "../main/footer.ftl">