<#include "../main/top.ftl">

<form id="myForm">
    <input type="hidden" id="id" name="id" value=" ${(centerProfile.id)!''}" />
    <input type="hidden" id="titleOlder" name="titleOlder" value="${(centerProfile.title)!''}">
    <input type="hidden" id="iconUrl" name="iconUrl" value="${(centerProfile.iconUrl)!''}">
    <div class="modal-body  fontsize-14" style="height:100%;">
        <div class="col-xs-6">
            <div class="form-group">
                <label>导航名称</label>
                <input type="text" id="title" name="title" onkeyup="this.value=this.value.substring(0, 50)" class="form-control input-sm"   value="${centerProfile.title}" required>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>类型</label>
                <select id="appType" name="appType" style="width: 380px;"
                        class="form-control" required>
                    <option value="1" <#if centerProfile.appType == 1>selected</#if>>安卓司机版</option>
                    <option value="2" <#if centerProfile.appType == 2>selected</#if>>安卓货主版</option>
                    <option value="3" <#if centerProfile.appType == 3>selected</#if>>ios司机版</option>
                    <option value="4" <#if centerProfile.appType == 4>selected</#if>>ios货主版</option>
                </select>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>显示顺序</label>
                <input type="text" id="orderNum" name="orderNum" class="form-control input-sm" value="${centerProfile.orderNum}"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" required>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>小标题</label>
                <input type="text" id="content" name="content" class="form-control input-sm"
                       onkeyup="this.value=this.value.substring(0, 20)"  value="${(centerProfile.content)!''}" required/>
            </div>
        </div>
        <div class="clearfix"></div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>链接</label>
                <input type="text" id="link" name="link" class="form-control input-sm"
                       onkeyup="this.value=this.value.substring(0, 200)"   value="${(centerProfile.link)!''}" required/>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label for="fileId1">图片</label>
                <div class="input-group">
                    <input id="fileId1" name="fileId1" type="file" onchange="readFile(this,'iconUrl','fileImageId1')">
                    <img id="fileImageId1" alt="图片库不存在此图片，请重新上传图片" src="${(centerProfile.iconUrl)!''}" width="56" height="56" style="margin-top: 10px;margin-left: 10px!important;" class="topjet-img-size" />
                </div>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>开始时间</label>
                <input size="16" type="text" value="${centerProfile.beginDate?string("yyyy-MM-dd HH:mm")}"  id="beginDate" required name="beginDate" placeholder="默认不限" class="form-control input-sm">
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>结束时间</label>
                <input size="16" type="text" value="${centerProfile.endDate?string("yyyy-MM-dd HH:mm")}"  id="endDate" required
                       name="endDate"
                       placeholder="默认不限" class="form-control input-sm">
            </div>
        </div>
    </div>
    <div class="clearfix">
    </div>
    <div class="modal-footer margin-top-15">
        <button type="button" id="myButton" onclick="updateNavitionType(${(centerProfile.id)!0}/*/!* ,<#--${(buttomProfile.version)!0}-->*!/*/)" class="btn btn-primary center-block">　提　交　</button>
    </div>
</form>
<#include "../main/footer.ftl">
<script type="text/javascript" src="<@s.url '/js/common/truckType.js'/>"></script>
<script type="text/javascript">

    $("#beginDate,#endDate").datetimepicker({
        format: "yyyy-mm-dd hh:ii:ss",
        todayBtn: true,
        autoclose: true,
        todayHighlight: true,
        showMeridian: true,
        pickerPosition: "bottom-left",
        language: 'zh-CN',//中文，需要引用zh-CN.js包
        startView: 2,//月视图
        minView: 0//日期时间选择器所能够提供的最精确的时间选择视图
    });

    function readFile(str, fileIds, fileImageId) {
        var file = str.files[0];
        if (!/image\/\w+/.test(file.type)) {
            bootbox.alert({
                size: 'small',
                message: "只能上传图片，请重新上传!",
            });
            return false;
        }
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function (e) {
            var imge = e.target.result;
            $('#' + fileIds).val(imge);
            $("#" + fileImageId).attr("src", this.result);
        };
    };
    function updateNavitionType(id/*,version*/) {
        if(IsFormChangeTrue("myForm")){

          /*  var bt = $('#myButton').button('loading');
            if (!$.topjectIsValidate('myForm')) {
                bt.button('reset');
                return;
            }*/
            var json = {
                'title':$("#title").val(),
                'appType':$("#appType").val(),
                'orderNum':$("#orderNum").val(),
                'content': $("#content").val(),
                'link': $("#link").val(),
                'iconUrl': $("#iconUrl").val(),
                'id':id
                /*'version':version*/
            };

            var beginDate = $("#beginDate").val();
            var endDate = $("#endDate").val();

            if(endDate<=beginDate){
                bootbox.alert({
                    size : 'small',
                    message :"结束日期不能小于开始日期!",
                });
                $('#endDate').val("");
                return false;
            }
            var beginDateData = {};
            var endDateData = {};
            if (!isNull(beginDate)) {
                beginDate = StringToDate(beginDate);
                beginDateData = {'beginDate': beginDate};
            }
            if (!isNull(endDate)) {
                endDate = StringToDate(endDate);
                endDateData = { 'endDate': endDate};
            }


            var url =getRootPath()+'/appMenuNavigate/updateCenterProfile.do';
            var data = $.extend({}, json, beginDateData,endDateData);
            window.parent.$.ajaxDefaultCall(url,data,function(){
                window.parent.location.href =  getRootPath()+'/view/appMenuNavigate/listCenterProfile.html'});
        }else {
            window.parent.location.href =  getRootPath()+'/view/appMenuNavigate/listCenterProfile.html';
        }
    }
    $(function () {
        $.topjectValidate('myForm');
    })

</script>
