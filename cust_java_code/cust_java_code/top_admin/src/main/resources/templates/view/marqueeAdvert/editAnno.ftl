<#include "../main/top.ftl">
<form id="myForm">
    <input type="hidden" value="${model.id!}" name="id" id="idId">
    <input type="hidden" id="versionId" name="version" value="${model.version}">
    <input type="hidden" id="typeIdId" value="${model.appType}">
    <div class="col-xs-6">
        <div class="form-group">
            <label>公告标题</label>
            <input type="text" name="title" id="title" maxlength="20" required value="${model.title}"
                   onchange="this.value=this.value.substring(0, 20)" onkeyup="this.value=this.value.substring(0, 20)"
                   class="form-control input-sm" placeholder="标题文字不能超过32字符">
        </div>
    </div>
    <div class="col-xs-6">
        <div class="form-group">
            <label>推送范围</label>
            <select id="typeId" name="type" data-toggle="select" required
                    class="form-control select select-default select-sm">
                <option value="1" <#if model.appType == 1>selected</#if>>Android接货版</option>
                <option value="2" <#if model.appType == 2>selected</#if>>Android发货版</option>
                <option value="3" <#if model.appType == 3>selected</#if>>IOS接货版</option>
                <option value="4" <#if model.appType == 4>selected</#if>>IOS发货版</option>
            </select>
        </div>
    </div>
    <div class="col-xs-6">
        <div class="form-group">
            <label>显示顺序</label>
            <input type="text" id="rank" name="rank" value="${model.rank}" class="form-control input-sm" required
                   placeholder="默认为1,显示最前边">
        </div>
    </div>
    <div class="col-xs-6">
        <div class="form-group">
            <label>是否开启</label>
            <select id="valid" name="valid" data-toggle="select" class="form-control select select-default select-sm">
                <option value="1" <#if model.valid == 1>selected</#if>>开启</option>
                <option value="0" <#if model.valid == 0>selected</#if>>关闭</option>
            </select>
        </div>
    </div>
    <div class="col-xs-6">
        <div class="form-group">
            <label>开始日期</label>
            <input type="text" name="beginDate" id="beginDate" value="${model.beginDate?string("yyyy-MM-dd HH:mm")}"
                   required class="form-control input-sm" required placeholder="不能小于当前时间"><!--不能小于当前时间不能开启-->
        </div>
    </div>
    <div class="col-xs-6">
        <div class="form-group">
            <label>结束日期</label>
            <input type="text" name="endDate" id="endDate" value="${model.endDate?string("yyyy-MM-dd HH:mm")}" required
                   class="form-control input-sm" required placeholder="不能小于当前时间"><!--不能小于当前时间不能开启-->
        </div>
    </div>
    <div class="col-xs-12">
        <div class="form-group">
            <label>公告详情</label>
            <textarea class="form-control" id="contentId" required name="content" rows="3" cols="50" required
                      placeholder="相关描述">${model.content}</textarea>
        </div>
    </div>
</form>
<div class="clearfix"></div>
<div class="modal-footer margin-top-15">
    <button type="button" class="btn btn-primary center-block" id="myButton" onclick="tijiao()">　提　交　</button>
</div>
<#include "../main/footer.ftl">
<script type="text/javascript">
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


    function tijiao() {

        var bt = $('#myButton').button('loading');

        /*if (!$.topjectIsValidate('myForm')) {
            bt.button('reset');
            return;
        }*/

        var beginDate = StringToDate($('#beginDate').val());
        var endDate = StringToDate($('#endDate').val());
        if (endDate <= beginDate) {
            bootbox.alert({
                size: 'small',
                message: "结束日期不能小于开始日期!",
            });
            bt.button('reset');
            return false;
        }
        var json = {
            "id": $('#idId').val(),
            "beginDate": StringToDate($('#beginDate').val()),
            "endDate": StringToDate($('#endDate').val()),
            'appType': $('#typeId').val(),
            'type':2,
            'rank': $('#rank').val(),
            'valid': $('#valid').val(),
            'content': $('#contentId').val(),
            'title': $('#title').val(),
            'version': $('#versionId').val()
        };
        var url = getRootPath() + '/marqueeAdvert/saveOrUpdate.do';
        window.parent.$.ajaxDefaultCall(url, json, function () {
            window.parent.location.href = getRootPath() + '/view/marqueeAdvert/list.html?appType=' + $("#typeIdId").val();
        });
    }
    function a() {
        var val = $('#contentIdHidden').val();
        if (!isNull(val)) {
            $('#contentId').val(val);
        }
    }

    $(function () {
        $.topjectValidate('myForm');
    })

</script>
