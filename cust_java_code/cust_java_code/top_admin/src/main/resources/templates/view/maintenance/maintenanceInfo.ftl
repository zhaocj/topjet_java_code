<#include "../main/top.ftl">
<form id="myForm">
    <div class="fixed-table-toolbar">
        <div class="navmenu"><a href="#" onclick="back()" ></a>
            <a href="#" onclick="javascript:history.back(1); return false">维护公告管理</a>/ 修改内容
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
            <input type="text" name="startTime" id="startTime" value="${model.startTime?string("yyyy-MM-dd HH:mm")}"
                   required class="form-control input-sm" required placeholder="不能小于当前时间"><!--不能小于当前时间不能开启-->
        </div>
    </div>
    <div class="col-xs-6">
        <div class="form-group">
            <label>结束日期</label>
            <input type="text" name="endTime" id="endTime" value="${model.endTime?string("yyyy-MM-dd HH:mm")}" required
                   class="form-control input-sm" required placeholder="不能小于当前时间"><!--不能小于当前时间不能开启-->
        </div>
    </div>
    <div class="col-xs-6">
        <div class="form-group">
            <label>公告标题</label>
            <input type="text" name="title" id="title" maxlength="20" required value="${model.title}"
                   onchange="this.value=this.value.substring(0, 20)" onkeyup="this.value=this.value.substring(0, 20)"
                   class="form-control input-sm" placeholder="标题文字不能超过32字符">
        </div>
    </div>
    <div class="col-xs-12">
        <div class="form-group">
            <label>公告内容</label>
            <textarea class="form-control" id="text" required name="text" rows="3" cols="50" required
                      placeholder="相关描述">${model.text}</textarea>
        </div>
    </div>
    <div class="col-xs-12">
        <div class="form-group">
            <label>备注</label>
            <textarea class="form-control" id="remark" required name="remark" rows="3" cols="50" required
                      placeholder="相关描述">${model.remark}</textarea>
        </div>
    </div>

    <input type="hidden" id="id" name="id" value="${model.id}">
   <#-- <input type="hidden" id="version" name="version" value="${model.version}">-->
</form>
<div class="clearfix"></div>
<div class="modal-footer margin-top-15">
    <button type="button" class="btn btn-primary center-block" id="myButton" onclick="submitBtn()">　提　交　</button>
</div>
<#include "../main/footer.ftl">
<script type="text/javascript">

    function back() {
        window.location.href=getRootPath()+"/view/maintenance/list.html"
    }

//时间插件
    $("#startTime,#endTime").datetimepicker({
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


    function submitBtn() {
        var bt = $('#myButton').button('loading');
        if (!$.topjectIsValidate('myForm')) {
            bt.button('reset');
            return;
        }

        var startTime = StringToDate($('#startTime').val());
        var endTime = StringToDate($('#endTime').val());
        if (endTime <= startTime) {
            bootbox.alert({
                size: 'small',
                message: "结束日期不能小于开始日期!",
            });
            bt.button('reset');
            return false;
        }

        var json = {
            "id": $('#id').val(),
            "startTime": StringToDate($('#startTime').val()),
            "endTime": StringToDate($('#endTime').val()),
            /*'appType': $('#appType').val(),*/
            'rank': $('#rank').val(),
            'valid': $('#valid').val(),
            'text': $('#text').val(),
            'title': $('#title').val(),
            'remark': $('#remark').val()
        };
        var url = getRootPath() + '/maintenance/updeteMaintenance.do';
        window.$.ajaxDefaultCall(url, json, function () {
            window.location.href = getRootPath() + '/view/maintenance/list.html';
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
