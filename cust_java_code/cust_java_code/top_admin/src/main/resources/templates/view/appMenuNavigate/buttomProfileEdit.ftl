<#include "../main/top.ftl">

    <form id="myForm">
     <input type="hidden" id="id" name="id" value=" ${(buttomProfile.id)!''}" />
     <input type="hidden" id="titleNameOlder" name="nameOlder" value="${(buttomProfile.name)!''}">
     <input type="hidden" id="codeOlder" name="colorOlder" value="${(buttomProfile.textNormalColor)!''}">
     <input type="hidden" id="iconDownUrl" name="iconDownUrl" value="${(buttomProfile.iconDownUrl)!''}">
     <input type="hidden" id="iconNormalUrl" name="iconNormalUrl" value="${(buttomProfile.iconNormalUrl)!''}">
        <div class="modal-body  fontsize-14" style="height:100%;">
            <div class="col-xs-6">
                <div class="form-group">
                    <label>导航名称</label>
                    <input type="text" id="text" name="text" onkeyup="this.value=this.value.substring(0, 50)" class="form-control input-sm"   value="${buttomProfile.text}" required>
                </div>
            </div>
            <div class="col-xs-6">
                <div class="form-group">
                    <label>类型</label>
                    <select id="appType" name="appType" style="width: 380px;"
                            class="form-control" required>
                        <option value="1" <#if buttomProfile.appType == 1>selected</#if>>安卓司机版</option>
                        <option value="2" <#if buttomProfile.appType == 2>selected</#if>>安卓货主版</option>
                        <option value="3" <#if buttomProfile.appType == 3>selected</#if>>ios司机版</option>
                        <option value="4" <#if buttomProfile.appType == 4>selected</#if>>ios货主版</option>
                    </select>
                </div>
            </div>
          <div class="col-xs-6">
            <div class="form-group">
                <label>显示顺序</label>
                <input type="text" id="orderNum" name="orderNum" class="form-control input-sm" value="${buttomProfile.orderNum}"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" required>
            </div>
          </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>文字默认颜色</label>
                <input type="text" id="textNormalColor" name="textNormalColor" class="form-control input-sm"
                       onkeyup="this.value=this.value.substring(0, 20)"  value="${(buttomProfile.textNormalColor)!''}" required/>
            </div>
        </div>
        <div class="clearfix"></div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>文字选中颜色</label>
                <input type="text" id="textDownColor" name="textDownColor" class="form-control input-sm"
                       onkeyup="this.value=this.value.substring(0, 20)"   value="${(buttomProfile.textDownColor)!''}" required/>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>开始时间</label>
                <input size="16" type="text" value="${buttomProfile.beginDate?string("yyyy-MM-dd HH:mm")}"  id="beginDate" required name="beginDate" placeholder="默认不限" class="form-control input-sm">
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>结束时间</label>
                <input size="16" type="text" value="${buttomProfile.endDate?string("yyyy-MM-dd HH:mm")}"  id="endDate" required
                       name="endDate"
                       placeholder="默认不限" class="form-control input-sm">
            </div>
        </div>
            <div class="clearfix"></div>
        <div class="col-xs-6">
            <div class="form-group">
                <label for="fileId1">导航默认图标</label>
                <div class="input-group">
                    <input id="fileId1" name="fileId1" type="file" onchange="readFile(this,'iconNormalUrl','fileImageId1')">
                    <img id="fileImageId1" alt="图片库不存在此图片，请重新上传图片" src="${(buttomProfile.iconNormalUrl)!''}" width="56" height="56" style="margin-top: 10px;margin-left: 10px!important;" class="topjet-img-size" />
                </div>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label for="fileId2">导航选中图标</label>
                <div class="input-group">
                    <input id="fileId2" name="fileId2" type="file" onchange="readFile(this,'iconDownUrl','fileImageId2')">
                    <img id="fileImageId2" alt="图片库不存在此图片，请重新上传图片" src="${(buttomProfile.iconDownUrl)!''}" width="56" height="56"  style="margin-top: 10px;margin-left: 10px!important;" class="topjet-img-size" />
                </div>
            </div>

        </div>
        </div>
        <div class="clearfix"></div>

        <div class="modal-footer margin-top-15">
            <button type="button" id="myButton" onclick="updateNavitionType(${(buttomProfile.id)!0}/*/!* ,<#--${(buttomProfile.version)!0}-->*!/*/)" class="btn btn-primary center-block">　提　交　</button>
        </div>
        </form>
<#include "../main/footer.ftl">
<script type="text/javascript" src="<@s.url '/js/common/truckType.js'/>"></script>
<script type="text/javascript">
    //页面加载的时候显示颜色
    $(document).ready(function(){
        //颜色插件
        $("#textNormalColor").colorpicker({
            fillcolor:true,
            success:function(o,color){
                $(o).css("textNormalColor",color);
            }
        });
        $("#textDownColor").colorpicker({
            fillcolor:true,
            success:function(o,color){
                $(o).css("textDownColor",color);
            }
        });
    });
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
            var bt = $('#myButton').button('loading');
            if (!$.topjectIsValidate('myForm')) {
                bt.button('reset');
                return;
            }
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

            var json = {
                'text':$("#text").val(),
                'appType':$("#appType").val(),
                'orderNum':$("#orderNum").val(),
                'textNormalColor': $("#textNormalColor").val(),
                'textDownColor': $("#textDownColor").val(),
                'iconNormalUrl': $("#iconNormalUrl").val(),
                'iconDownUrl': $("#iconDownUrl").val(),
                'id':id
                /*'version':version*/
            };
            var data = $.extend({}, json, beginDateData,endDateData);
            var url =getRootPath()+'/appMenuNavigate/update.do';
            window.parent.$.ajaxDefaultCall(url,data,function(){
                window.parent.location.href =  getRootPath()+'/view/appMenuNavigate/listButtomProfile.html'});
        }else {
            window.parent.location.href =  getRootPath()+'/view/appMenuNavigate/listButtomProfile.html';
        }
    }
    $(function () {
        $.topjectValidate('myForm');
    })

</script>
