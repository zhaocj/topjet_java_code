<#include "../main/top.ftl">
<div class="contentBox" style="padding:10px">
    <div class="fixed-table-toolbar">
        <div class="columns columns-left btn-group pull-right">
            <!--
            不操作“点击生效”，只是保存修改的数据，
            只有点击“点击生效”才可以让APP读取新的数据，
            防步误操作
            修改数据前，按钮是灰色的，数据有变动时按钮变亮
            -->
            <button id="sycId" type="button" class="btn btn-default btn-sm margin-left-10" disabled onclick="toUpdate()">点击生效</button>
        <#--<button id="sycId" type="button" class="btn btn-primary btn-sm margin-left-10"  onclick="toUpdate()">点击生效</button>-->
        </div>
    </div>
    <table class="table table-bordered" id="btnedit">
        <thead>
        <tr>
            <th width="10%">更新日期</th>
            <#--<th width="10%">规则类型</th>-->
            <th width="15%">规则名称</th>
            <th width="25%">规则说明</th>
            <th width="10%">规则积分数</th>
            <th width="10%">每日上限</th>
            <th width="15%">限制次数</th>
            <th width="15%">操作</th>
        </tr>
        </thead>
        <tbody>
        <#list bsmValidList as item>
            <#assign flag = true>
            <#list bsmInvalidList as itemIn>
                <#if item.scoreType == itemIn.scoreType>
                    <#assign flag = false>
                    <tr class="danger abc">
                        <td>${itemIn.updateTime?string("yyyy-MM-dd HH:mm")}</td>
                        <#--<td><#switch itemIn.scoreType>
                            <#case 1>每日登陆<#break>
                            <#case 2>每日签到<#break>
                            <#case 3>实名认证<#break>
                            <#case 4>身份认证<#break>
                            <#case 5>每日接单<#break>
                            <#case 6>评价订单<#break>
                            <#case 7>分享下载<#break>
                            <#case 8>车辆认证<#break>
                            <#case 9>新手引导<#break>
                            <#case 10>每日发货<#break>
                        </#switch></td>-->
                        <td class="textEdit" inputName="ruleName"  olderValue="${item.ruleName}">${item.ruleName}</td>
                        <td class="textEdit" inputName="ruleDescription"  olderValue="${item.ruleDescription}">${item.ruleDescription}</td>
                        <td class="idedit" title="（原${item.everyTimeScore?c})"   inputName="value" olderValue="${itemIn.everyTimeScore?c}"> ${itemIn.everyTimeScore}</td>
                        <td class="idedit" title="（原${item.scoreMax?c})"   inputName="topLimit" olderValue="${itemIn.scoreMax?c}">${itemIn.scoreMax}</td>
                        <td class="idedit" title="（原${item.controlCount?c})"   inputName="controlCount" olderValue="${itemIn.controlCount?c}">${itemIn.controlCount}</td>
                        <td class="idedit" style="display: none"  inputName="type" >${itemIn.scoreType}</td>
                        <td><input type="button" id='${itemIn.id}'  class="btn btn-primary btn-sm" value="编辑"></td>
                    </tr>
                </#if>
            </#list>
            <#if flag>
                <tr>
                    <td>${item.updateTime?string("yyyy-MM-dd HH:mm")}</td>
                    <#--<td><#switch item.scoreType>
                        <#case 1>每日登陆<#break>
                        <#case 2>每日签到<#break>
                        <#case 3>实名认证<#break>
                        <#case 4>身份认证<#break>
                        <#case 5>每日接单<#break>
                        <#case 6>评价订单<#break>
                        <#case 7>分享下载<#break>
                        <#case 8>车辆认证<#break>
                        <#case 9>新手引导<#break>
                        <#case 10>每日发货<#break>
                    </#switch></td>-->
                    <td class="textEdit" inputName="ruleName"  olderValue="${item.ruleName}">${item.ruleName}</td>
                    <td class="textEdit" inputName="ruleDescription"  olderValue="${item.ruleDescription}">${item.ruleDescription}</td>
                    <td class="idedit"   inputName="value" olderValue="${item.everyTimeScore?c}">${item.everyTimeScore}</td>
                    <td class="idedit"   inputName="topLimit" olderValue="${item.scoreMax?c}">${item.scoreMax}</td>
                    <td class="idedit"   inputName="controlCount" olderValue="${item.controlCount?c}">${item.controlCount}</td>
                    <td class="idedit" style="display: none"     inputName="type" >${item.scoreType}</td>
                    <td><input type="button" id='${item.id}'   class="btn btn-primary btn-sm" value="编辑"></td>
                </tr>
            </#if>
        </#list>
        </tbody>
    </table>
</div>
<#include "../main/footer.ftl">
<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content"></div>
    </div>
</div>
<script>

    var buttonFlag = false;
    var id;
    $("#btnedit input:button").click(function() {
        id =$(this).attr("id");
        var str = $(this).val()=="编辑"?"确定":"编辑";
        $(this).val(str);   // 按钮被点击后，在“编辑”和“确定”之间切换
        if($(this).val()=="编辑"){
            $(this).removeClass("btn-info");
            $(this).addClass("btn-primary");
        }else if($(this).val()=="确定"){
            $(this).removeClass("btn-primary");
            $(this).addClass("btn-info")
        };
        var changeFlag = false;
        //修改数字
        $(this).parent().siblings(".idedit").each(function() {  // 获取当前行的其他单元格
            var obj_text = $(this).find("input:text");    // 判断单元格下是否有文本框
            var olderValue = $(this).attr("olderValue");
            if(!obj_text.length) {   // 如果没有文本框，则添加文本框使之可以编辑
                $(this).html("<input type='text' style='width:100px' onkeyup='this.value=this.value.replace(\/\\D\/g, \"\" )'  value='" + $(this).text() + "'>"  );
            }else {   // 如果已经存在文本框，则将其显示为文本框修改的值
                $(this).html(obj_text.val());
                if(obj_text.val() == ""){
                    $(this).html(olderValue);
                }
                var valStr = $.trim(obj_text.val());
                var oldValStr = $.trim(olderValue);
                if(!changeFlag && !isNull(olderValue) && (oldValStr+"" )  !=  (valStr+"")){
                    changeFlag = true;
                }
            }
        });
        //修改签到相关数字
        $(this).parent().siblings(".signidedit").each(function() {  // 获取当前行的其他单元格
            var obj_text = $(this).find("input:text");    // 判断单元格下是否有文本框
            var olderValue = $(this).attr("olderValue");
            var ruleId =id.split("_")[1];
            var signId =id.split("_")[0];
            if(!obj_text.length && (signId ==2  || ruleId ==2 ) ) {   // 如果没有文本框，则添加文本框使之可以编辑
                $(this).html("<input type='text' style='width:100px' onkeyup='this.value=this.value.replace(\/\\D\/g, \"\" )'  value='" + $(this).text() + "'>"  );
            }else {   // 如果已经存在文本框，则将其显示为文本框修改的值
                $(this).html(obj_text.val());
                if(obj_text.val() == ""){
                    $(this).html(olderValue);
                }
                var valStr = $.trim(obj_text.val());
                var oldValStr = $.trim(olderValue);
                if(!changeFlag && !isNull(valStr) && (oldValStr+"" )  !=  (valStr+"")){
                    changeFlag = true;
                }
            }
        });


        //修改文字
        $(this).parent().siblings(".textEdit").each(function() {  // 获取当前行的其他单元格
            var obj_text = $(this).find("textarea");    // 判断单元格下是否有文本框
            var olderValue = $(this).attr("olderValue");
            if(!obj_text.length) {   // 如果没有文本框，则添加文本框使之可以编辑
                $(this).html("<textarea  maxlength='50'>"+$(this).text()+"</textarea>" );
            }else {   // 如果已经存在文本框，则将其显示为文本框修改的值
                $(this).html(obj_text.val());
                if(obj_text.val() == ""){
                    $(this).html(olderValue);
                }
                var valStr = $.trim(obj_text.val());
                var oldValStr = $.trim(olderValue);
                if(!changeFlag  && !isNull(valStr)  && (oldValStr+"" )  !=  (valStr+"")){
                    changeFlag = true;
                }
            }
        });

        if(changeFlag){
            $(this).parent().parent().addClass("danger");
            buttonFlag = true;
        }else{
            if(!$(this).parent().parent().hasClass("abc")){
                $(this).parent().parent().removeClass("danger");
            }
        }


        if($(this).val()=="编辑"){
            if($("#btnedit .danger").not(".abc").length>0 || buttonFlag){
                $("#sycId").removeAttr("disabled");
                $("#sycId").removeClass("btn-default");
                $("#sycId").addClass("btn-primary");
            }else{
                $("#sycId").attr("disabled","disabled");
                $("#sycId").addClass("btn-default");
                $("#sycId").removeClass("btn-primary");
            }
        }
    });


    //确定生效按钮
    function toUpdate(){
        var flag = false;
        $("#btnedit input:button").each(function(){
            var  str = $(this).val()
            if(str == '确定'){
                flag = true;
                return;
            }
        })
        if(!flag){
            if($("#btnedit .danger").length>0){
                var objectList =[]
                $("#btnedit .danger").each(function(){
                    var id = $(this).children(":last").children().attr("id");
                    var signId =id.split("_")[0];
                    var jsonData = {};
                    jsonData['id'] = signId;
                    $(this).children(".idedit").each(function() {  // 获取当前行的其他单元格
                        var inputName = $(this).attr("inputName");
                        var obj_text = $(this).text();    // 获得单元格的值
                        if(obj_text.endWith("%")){
                            var value = obj_text.substring(0,obj_text.length-1);
                            jsonData[inputName] = (Math.round(value * 100)/10000).toFixed(4);
                        }else{
                            jsonData[inputName] = obj_text;
                        }
                    })
                    $(this).children(".textEdit").each(function() {  // 获取当前行的其他单元格
                        var inputName1 = $(this).attr("inputName");
                        var obj_text = $(this).text();    // 获得单元格的值
                        if(obj_text.endWith("%")){
                            var value = obj_text.substring(0,obj_text.length-1);
                            jsonData[inputName1] = (Math.round(value * 100)/10000).toFixed(4);
                        }else{
                            jsonData[inputName1] = obj_text;
                        }
                    })
                    $(this).children(".signidedit").each(function() {  // 获取当前行的其他单元格
                        var inputName2 = $(this).attr("inputName");
                        var obj_text = $(this).text();    // 获得单元格的值
                        if(obj_text.endWith("%")){
                            var value = obj_text.substring(0,obj_text.length-1);
                            jsonData[inputName2] = (Math.round(value * 100)/10000).toFixed(4);
                        }else{
                            jsonData[inputName2] = obj_text;
                        }
                    })
                    objectList.push(jsonData);
                })
                if(!isNull(objectList)){
                    var  url =  getRootPath() + '/scoreNewSetting/updateScoreSetting.do';
                    var sycButton =  $("#sycId");
                    $.ajaxDefaultCall(url,{'dataList':JSON.stringify(objectList)},function(){
                        sycButton.attr("disabled","disabled");
                    });
                }
            }else{
                bootbox.alert({
                    size : 'small',
                    message :'明天0：00将生效',
                });
                return;
            }
        }else{
            bootbox.alert({
                size : 'small',
                message :'请确定所有修改项',
            });
            return;
        }

    }
</script>