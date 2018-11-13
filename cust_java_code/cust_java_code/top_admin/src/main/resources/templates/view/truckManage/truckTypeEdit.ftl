<#include "../main/top.ftl">
<input type="hidden" id="id" name="id" value="${td.id?c}" />

    <form id="myForm">
            <input type="hidden" id="displayNameOlder" name="displayNameOlder" value="${td.displayName}">
            <input type="hidden" id="codeOlder" name="codeOlder" value="${td.code}">
            <input type="hidden" id="validOlder" name="validOlder" value="${td.valid}">
            <input type="hidden" id="truckOrderOlder" name="truckOrderOlder" value="${td.truckOrder}">
          <#--  <input type="hidden" id="iconUrlOlder" name="iconUrlOlder" value="${td.iconUrl}">
            <input type="hidden" id="iconUrl" name="iconUrl" value="${td.iconUrl}">-->
        <div class="modal-body  fontsize-14">
            <div class="col-xs-6">
                <div class="form-group">
                    <label>车型名称</label>
                    <input type="text" id="displayName" name="displayName" onkeyup="this.value=this.value.substring(0, 20)" class="form-control input-sm"  onblur="checkDisplayName()" value="${td.displayName}" required>
                </div>
            </div>
            <div class="col-xs-6">
                <div class="form-group">
                    <label>车型代码</label>
                    <input type="text" id="code" name="code" class="form-control input-sm" onkeyup="this.value=this.value.substring(0, 20)" onblur="checkCode()"  value="${td.code}" required>
                </div>
            </div>
            <div class="col-xs-6">
                <div class="form-group">
                    <label>是否可用</label>
                    <select id="valid" name="valid"
                            class="form-control" required>
                        <option value="">请选择</option>
                        <option value="0" <#if td.valid == 0>selected</#if>>不可用</option>
                        <option value="1" <#if td.valid == 1>selected</#if>>可用</option>
                    </select>
                </div>
            </div>
            <div class="col-xs-6">
                <div class="form-group">
                    <label>排序</label>
                    <input type="text" id="truckOrder" name="truckOrder" class="form-control input-sm" value="${td.truckOrder}"  onblur="checkTruckOrder()" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" required>
                </div>
            </div>
           <#-- <div class="col-xs-6">
                <div class="form-group">
                    <label for="fileId1">上传车辆图标</label>
                    <div class="input-group">
                        <input id="fileId1" name="fileId1" type="file" onchange="readFile(this,'iconUrl','fileImageId1')">
                        <img id="fileImageId1" alt="图片库不存在此图片，请重新上传图片" src="${td.iconUrl}" required width="280" height="300"
                             class="topjet-img-size"/>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>-->
        </div>
            <div class="clearfix">

            </div>
            <div class="modal-footer margin-top-15">
                <button type="button" id="myButton" onclick="updateTruckType(${td.id})" class="btn btn-primary center-block">　提　交　</button>
           </div>
    </form>
<#include "../main/footer.ftl">
<script type="text/javascript" src="<@s.url '/js/common/truckType.js'/>"></script>

	<script type="text/javascript">
        function checkCode(){
            var code = $("#code").val();
            var regexp =  new RegExp("^[A-Za-z]+$");
            if(regexp.test(code)){
                $("#code").val(code.toUpperCase());
                var url = getRootPath()+'/truckType/checkCode.do';
                code =$("#code").val();

                if(code==$("#codeOlder").val()){
                    return ;
                }
                $.ajax({
                    type:"POST",
                    contentType: "application/x-www-form-urlencoded;charset=utf-8",
                    url:url,
                    data:"code="+code,
                    success:function(data){
                        if(data==false){
                            bootbox.alert({
                                size: 'small',
                                message: "重复的车型代码，请重新输入!",
                            });
                            $("#code").val("");
                        }
                    }
                });
                return true;
            }else {
                bootbox.alert({
                    size: 'small',
                    message: "只能输入字母，请重输!",
                });
                $("#code").val("");
                return false;
            }

        }

        function checkDisplayName() {
            var displayName =$("#displayName").val();
            var url = getRootPath()+'/truckType/checkDisplayName.do';
            if(displayName==$("#displayNameOlder").val()){
                return ;
            }
            $.ajax({
                type:"POST",
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                url:url,
                data:"displayName="+displayName,
                success:function(data){
                    if(data==false){
                        bootbox.alert({
                            size: 'small',
                            message: "重复的车型名称，请重新输入!",
                        });
                        $("#displayName").val("");
                    }
                }
            });
        }

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

        function updateTruckType(id) {
           if(IsFormChangeTrue("myForm")){
               var bt = $('#myButton').button('loading');
               if (!$.topjectIsValidate('myForm')) {
                   bt.button('reset');
                   return;
               }
               var json = {
                   'displayName':$("#displayName").val(),
                   'valid': $("#valid").val(),
                   'code': $("#code").val(),
                   'truckOrder':$("#truckOrder").val(),
                  // 'iconUrl': $("#iconUrl").val(),
                   'id':id
                  // 'version':version
               }
               var url =getRootPath()+'/truckType/update.do';
               window.parent.$.ajaxDefaultCall(url,json,function(){
                   window.parent.location.href =  getRootPath()+'/view/truckManage/truckTypeList.html'});
           }else {
                  window.parent.location.href =  getRootPath()+'/view/truckManage/truckTypeList.html';
           }

        }

        $(function () {
            $.topjectValidate('myForm');
        })

	</script>
