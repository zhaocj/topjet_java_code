<#include "../main/top.ftl">

<div class="contentBox" style="padding:10px">
    <div class="fixed-table-toolbar">
        <#--<div class="navmenu"><a href="#" onclick="javascript:history.back(-2); return false">定时活动管理</a> /
        <#switch aim.appType>
            <#case "4"> <a href="#" onclick="javascript:history.back(-1); return false">IOS发货版</a>/ 修改内容<#break>
            <#case "3"> <a href="#" onclick="javascript:history.back(-1); return false">IOS接货版</a>/ 修改内容<#break>
            <#case "2"><a href="#" onclick="javascript:history.back(-1); return false">Android发货版</a>/ 修改内容<#break>
            <#case "1"><a href="#" onclick="javascript:history.back(-1); return false">Android接货版</a>/ 修改内容<#break>
        </#switch>
        </div>-->
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
                        <label>活动页面</label>
                            <textarea id="showPage" name="showPage" type="text" class="form-control input-sm" disabled >${aim.showPage}</textarea>
                </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group">
                        <label>活动城市</label>
                        <textarea id="city" name="city" type="text" class="form-control input-sm" disabled >${aim.city}</textarea>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group">
                        <label>开始时间</label>
                        <input size="16" type="text" id="beginDate" name="beginDate" value="${aim.beginDate?string("yyyy-MM-dd HH:mm:ss")}" placeholder="默认不限"
                               class="form-control input-sm ">
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group">
                        <label>结束时间</label>
                        <input size="16" type="text" id="endDate" name="endDate" value="${aim.endDate?string("yyyy-MM-dd HH:mm:ss")}" placeholder="默认不限"
                               class="form-control input-sm ">
                    </div>
                </div>
               <#-- <div class="col-xs-6">
                    <div class="form-group">
                        <label>图标开启时间</label>
                        <input size="16" type="text" value="${aim.iconBenginDate?string("yyyy-MM-dd HH:mm:ss")}" id="iconBeginDate" required
                               name="iconBeginDate"
                               placeholder="默认不限" class="form-control input-sm ">
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group">
                        <label>图标关闭时间</label>
                        <input size="16" type="text" value="${aim.iconEndDate?string("yyyy-MM-dd HH:mm:ss")}" id="iconEndDate" required
                               name="iconEndDate"
                               placeholder="默认不限" class="form-control input-sm ">
                    </div>
                </div>-->

                <div class="col-xs-6">
                    <div class="form-group">
                        <label>活动标题</label>
                        <input type="text" name="title" id="title" class="form-control" value="${aim.title}">
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group">
                        <label>活动链接</label>
                        <input type="text" name="activityURL" id="activityURL" class="form-control" value="${aim.redirectURL}">
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="form-group">
                        <label>上传图片</label>
                        <div class="input-group">
                            <input id="fileId1" type="file"      onchange="readFile(this,'picture','fileImageId1')">
                            <img id="fileImageId1" src="${aim.pictureUrl}" width="300" height="300" class="topjet-img-size"/>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
        </form>
    </div>
</div>
<input type="hidden" id="picture" name="picture" value="${aim.pictureUrl}">
<input type="hidden" id="id" name="id" value="${aim.id}">



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

   /* $("#iconBeginDate,#iconEndDate").datetimepicker({
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
*/

    function validDate(){
        var beginDate = StringToDate($('#beginDate').val());
        var endDate = StringToDate($('#endDate').val());
        /*var iconBeginDate = StringToDate($('#iconBeginDate').val());
        var iconEndDate = StringToDate($('#iconEndDate').val());*/
        if(endDate<=beginDate){
            bootbox.alert({
                size : 'small',
                message :"结束日期不能小于开始日期!",
            });
            $('#endDate').val($("endDateOlder").val());
            return false;
        }
       /* if (iconEndDate <= iconBeginDate) {
            bootbox.alert({
                size: 'small',
                message: "关闭时间不能小于开启日期!",
            });
            $('#iconEndDate').val($("iconEndDate").val());
            return false;
        }*/
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
       /* var iconBeginDate = StringToDate($('#iconBeginDate').val());
        var iconEndDate = StringToDate($('#iconEndDate').val());*/
        if(endDate<beginDate){
            bootbox.alert({
                size : 'small',
                message :"结束日期不能小于开始日期!",
            });
            bt.button('reset');
            return false;
        }
        /*if (iconEndDate <= iconBeginDate) {
            bootbox.alert({
                size: 'small',
                message: "关闭时间不能小于开启日期!",
            });
            $('#iconEndDate').val($("iconEndDate").val());
            return false;
        }*/
        var jsonBeginDate = {};
        var jsonEndDate = {};
      /*  var jsoniconEndDate = {};
        var jsoniconBeginDate = {};*/
        if (!isNull($('#beginDate').val())) {
            jsonBeginDate = {"beginDate": StringToDate($('#beginDate').val())};
        }
        if (!isNull($('#endDate').val())) {
            jsonEndDate = {"endDate": StringToDate($('#endDate').val())};
        }
        /*if (!isNull($('#iconEndDate').val())) {
            jsoniconEndDate = {"iconEndDate": StringToDate($('#iconEndDate').val())};
        }
        if (!isNull($('#iconBeginDate').val())) {
            jsoniconBeginDate = {"iconBenginDate": StringToDate($('#iconBeginDate').val())};
        }*/
        var json = {
            'id':$("#id").val(),
            'redirectURL':$("#activityURL").val(),
            'title':$("#title").val(),
            'pictureUrl':$("#picture").val()
        }
        var data = $.extend({}, json, jsonBeginDate, jsonEndDate);
        var url = getRootPath()+'/regularActivity/updateRegularActivities.do';
        window.parent.$.ajaxDefaultCall(url,data,function () {
            window.parent.location.reload();
        });
    }



</script>


<#--<script type="text/javascript" src="../js/jedate/jquery-1.7.2.js"></script>-->
<#--<script type="text/javascript" src="../js/jedate/jquery.jedate.js"></script>-->
<#--<link type="text/css" rel="stylesheet" href="../js/jedate/skin/jedate.css">-->
<#--<script type="text/javascript">-->

    <#--var cancel ={-->
        <#--minDate:'2015-06-16',-->
        <#--maxDate:'2017-12-16'-->
    <#--}-->
    <#--var opts = $.extend({-->
        <#--type:"je",-->
        <#--minDate:undefined,-->
        <#--maxDate:undefined-->
    <#--},cancel);-->
    <#--if (opts.type == "je") {-->
        <#--$("#optsdate").jeDate({-->
            <#--isinitVal: true,-->
            <#--festival: true,-->
            <#--trigger: "click mouseenter focus",-->
            <#--isTime:false,-->
            <#--ishmsVal: false,-->
            <#--minDate: opts.minDate,-->
            <#--maxDate: opts.maxDate,-->
            <#--format: "YYYY-MM-DD hh:mm",-->
            <#--zIndex: 3000,-->
            <#--okfun:function (elem,val) {-->
                <#--alert(elem)-->
            <#--}-->
        <#--})-->
    <#--}-->

    <#--//实现日期选择联动-->
    <#--var start = {-->
        <#--format: 'YYYY-MM-DD',-->
        <#--minDate: '2014-06-16 23:59:59', //设定最小日期为当前日期-->
        <#--//festival:true,-->
        <#--maxDate: $.nowDate({DD:0}), //最大日期-->
        <#--choosefun: function(elem,datas){-->
            <#--end.minDate = datas; //开始日选好后，重置结束日的最小日期-->
            <#--endDates();-->
        <#--},-->
        <#--okfun:function (elem,datas) {-->
            <#--alert(datas)-->
        <#--}-->
    <#--};-->
    <#--var end = {-->
        <#--format: 'YYYY年MM月DD日',-->
        <#--minDate: $.nowDate({DD:0}), //设定最小日期为当前日期-->
        <#--//festival:true,-->
        <#--maxDate: '2099-06-16 23:59:59', //最大日期-->
        <#--choosefun: function(elem,datas){-->
            <#--start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期-->
        <#--}-->
    <#--};-->
    <#--function endDates() {-->
        <#--end.trigger = false;-->
        <#--$("#inpend").jeDate(end);-->
    <#--}-->
    <#--$("#inpstart").jeDate(start);-->
    <#--$("#inpend").jeDate(end);-->

    <#--$("#iconBeginDate").jeDate({-->
        <#--isinitVal:true,-->
        <#--festival:true,-->
        <#--ishmsVal:false,-->
        <#--minDate: '2016-06-16 23:59:59',-->
        <#--maxDate: $.nowDate({DD:0}),-->
        <#--format:"hh:zz",-->
        <#--zIndex:3000,-->
    <#--})-->

    <#--$("#iconEndDate").jeDate({-->
        <#--isinitVal:true,-->
        <#--festival:true,-->
        <#--ishmsVal:false,-->
        <#--minDate: '2016-06-16 23:59:59',-->
        <#--maxDate: $.nowDate({DD:0}),-->
        <#--format:"hh:zz",-->
        <#--zIndex:3000,-->
    <#--})-->

<#--</script>-->
<#include "../main/footer.ftl">