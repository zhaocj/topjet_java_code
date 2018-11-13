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
            <th width="13%">补帖项</th>
            <th width="7%">补帖对象</th>
            <th width="7%">条件</th>
            <th width="10%">每次补帖金额</th>
            <th width="10%">补贴下限</th>
            <th width="10%">补贴上限</th>
            <th width="6%">操作</th>
        </tr>
        </thead>
        <tbody>

        <#list bsmValidList as item>
            <#assign flag = true>
            <#list bsmInvalidList as itemIn>
                <#if item.bonusType == itemIn.bonusType>
                    <#assign flag = false>
                    <tr class="danger abc">
                        <td><#switch itemIn.bonusType>
                            <#case 1>运费托管补贴<#break>
                            <#case 2>用户首单交易成功<#break>
                            <#case 3>每月相同用户<#break>
                        </#switch></td>
                        <td><#switch itemIn.bonusTarget>
                            <#case 1>所有用户<#break>
                            <#case 2>承运方<#break>
                            <#case 3>发货方<#break>
                            <#case 4>推荐人<#break>
                        </#switch></td>
                        <td>订单承运结束</td>
                        <td class="idedit"  style="display: none"  inputName="bonusType" >${itemIn.bonusType}</td>
                        <#if itemIn.everyTimeAmount != 0>
                            <#if itemIn.amountType == 2>
                                <td class="idedit" inputName="everyTimeAmount" title="（原${item.everyTimeAmount?string.percent})" olderValue="${itemIn.everyTimeAmount*100}" >${itemIn.everyTimeAmount*100}<font>%</font></td>
                                <#else> <td class="idedit" inputName="everyTimeAmount" title="（原${item.everyTimeAmount})" olderValue="${itemIn.everyTimeAmount}" >${itemIn.everyTimeAmount}</td>
                             </#if>
                        <#else>
                            <td >-</td>
                        </#if>
                        <td <#if itemIn.bonusScopeMin != 0>class="idedit" inputName="bonusScopeMin" title="（原${item.bonusScopeMin})" olderValue="${itemIn.bonusScopeMin}" </#if>><#if itemIn.bonusScopeMin != 0>${itemIn.bonusScopeMin}<#else>-</#if></td>
                        <td <#if itemIn.bonusScopeMax != 0>class="idedit" inputName="bonusScopeMax" title="（原${item.bonusScopeMax})" olderValue="${itemIn.bonusScopeMax}" </#if>><#if itemIn.bonusScopeMax != 0>${itemIn.bonusScopeMax}<#else>-</#if></td>
                        <td><input type="button" id="${itemIn.id!}"  class="btn btn-primary btn-sm" value="编辑"></td>
                    </tr>
                </#if>
            </#list>
            <#if flag>
            <tr>
                    <td><#switch item.bonusType>
                        <#case 1>运费托管补贴<#break>
                        <#case 2>用户首单交易成功<#break>
                        <#case 3>每月相同用户<#break>
                    </#switch></td>
                    <td><#switch item.bonusTarget>
                        <#case 1>所有用户<#break>
                        <#case 2>承运方<#break>
                        <#case 3>发货方<#break>
                        <#case 4>推荐人<#break>
                    </#switch></td>
                    <td>订单承运结束</td>
                    <td class="idedit"  style="display: none"  inputName="bonusType" >${item.bonusType}</td>
                    <#if item.everyTimeAmount != 0>
                        <#if item.amountType == 2>
                            <td class="idedit" inputName="everyTimeAmount"  olderValue="${item.everyTimeAmount*100}" >${item.everyTimeAmount*100}<font>%</font></td>
                        <#else> <td class="idedit" inputName="everyTimeAmount"  olderValue="${item.everyTimeAmount}" >${item.everyTimeAmount}</td>
                        </#if>
                    <#else>
                        <td >-</td>
                    </#if>
                    <td <#if item.bonusScopeMin != 0>class="idedit" inputName="bonusScopeMin" olderValue="${item.bonusScopeMin}" </#if>><#if item.bonusScopeMin != 0>${item.bonusScopeMin}<#else>-</#if></td>
                    <td <#if item.bonusScopeMax != 0>class="idedit" inputName="bonusScopeMax" olderValue="${item.bonusScopeMax}" </#if>><#if item.bonusScopeMax != 0>${item.bonusScopeMax}<#else>-</#if></td>
                    <td><input type="button" id="${item.id!}"  class="btn btn-primary btn-sm" value="编辑"></td>
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
    $("#btnedit input:button").click(function() {
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
        $(this).parent().siblings(".idedit").each(function() {  // 获取当前行的其他单元格
            var obj_text = $(this).find("input:text");    // 判断单元格下是否有文本框
            var olderValue = $(this).attr("olderValue");
            var amountType = $(this).attr("amountType");
            if(!obj_text.length) {   // 如果没有文本框，则添加文本框使之可以编辑
                if($(this).find("font").length>0){
                    $(this).children("font").remove();
                    $(this).html("<input type='text'  onblur='checkValue(this)' style='width:100px' value='"+$(this).text()+"'><font>%</font>");
                }else{
                    $(this).html("<input type='text'  onblur='checkValue1(this)' onkeyup='this.value=this.value.replace(\/\\D\/g, \"\" )' style='width:100px' value='"+$(this).text()+"'>");
                }
            }else {   // 如果已经存在文本框，则将其显示为文本框修改的值
                if($(this).find("font").length>0){
                    if(obj_text.val() == ""){
                        $(this).html(olderValue+"<font>%</font>");
                    }else{
                        $(this).html(obj_text.val()+"<font>%</font>");
                    }
                }else{
                    if(obj_text.val() == ""){
                        $(this).html(olderValue);
                    }else{
                        $(this).html(obj_text.val());
                    }
                }

                if(!changeFlag && !isNull(olderValue) && olderValue != obj_text.val()){
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
    function checkValue(obj){
        if (!(/^(([1-9][0-9]*)|((([1-9][0-9]*)|0)\.[0-9]{1,2}))$/.test(obj.value)) ) {
            bootbox.alert({
                size: 'larger',
                message: '只能输入整数或小数点后两位！',
            });
            $(obj).val("");
            return;
        }

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
     function toUpdate(){
         var flag = false;
         $("#btnedit input:button").each(function(){
             var  str = $(this).val()
             if(str == '确定'){
                 flag = true;
                 return;
             }
         })
         if(!flag) {
             if ($("#btnedit .danger").length > 0) {
                 var objectList = []
                 $("#btnedit .danger").each(function () {
                     var id = $(this).children(":last").children().attr("id");
                     var jsonData = {};
                     jsonData['id'] = id;
                     $(this).children(".idedit").each(function () {  // 获取当前行的其他单元格
                         var inputName = $(this).attr("inputName");
                         var obj_text = $(this).text();    // 获得单元格的值
                         if (obj_text.endWith("%")) {
                             var value = obj_text.substring(0, obj_text.length - 1);
                             jsonData[inputName] = (Math.round(value * 100) / 10000).toFixed(4);
                         } else {
                             jsonData[inputName] = obj_text;
                         }
                     })
                     objectList.push(jsonData);
                 })
                 if (!isNull(objectList)) {
                     var url = getRootPath() + '/bonusSetting/addOrUpdateBonusSetting.do';
                     var sycButton = $("#sycId");
                     $.ajaxDefaultCall(url, {'dataList': JSON.stringify(objectList)}, function () {
                         sycButton.attr("disabled", "disabled");
                     });
                 }
             } else {
                 bootbox.alert({
                     size: 'small',
                     message: '明天0：00将生效',
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