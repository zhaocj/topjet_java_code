<#include "../../main/top.ftl">
<form id ="myForm">
    <input name="type" type="hidden" value="${appUpgradeInfoModel.type}">
    <input name="id" id = "id" type="hidden" value="${appUpgradeInfoModel.id}">
    <div class="modal-body  fontsize-14">
        <div class="col-xs-6">
            <div class="form-group">
                <label for="type">系统类型</label>
                <select id="type"  disabled data-toggle="select" class="form-control select select-default select-sm" required>
                    <option value="1" <#if appUpgradeInfoModel.type == '1'>selected</#if>>安卓司机版</option>
                    <option value="2" <#if appUpgradeInfoModel.type == '2'>selected</#if> >安卓货主版</option>
                    <option value="3" <#if appUpgradeInfoModel.type == '3'>selected</#if>>IOS司机版</option>
                    <option value="4" <#if appUpgradeInfoModel.type == '4'>selected</#if>>IOS货主版</option>
                </select>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label for="isForced">是否强制更新</label>
                <select id="isForced" name="isForced"  data-toggle="select" class="form-control select select-default select-sm" required>
                    <option value="">请选择</option>
                    <option value="1" <#if appUpgradeInfoModel.isForced == 1>selected</#if>>是</option>
                    <option value="0" <#if appUpgradeInfoModel.isForced == 0>selected</#if>>否</option>
                </select>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>系统版本号</label>
                <input type="text" id="systemVersion" name="systemVersion" value="${appUpgradeInfoModel.systemVersion}" class="form-control input-sm" placeholder="请输入系统版本号" required>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>内部版本号</label>
                <input type="text" id="innoVersion" name="innoVersion" value="${appUpgradeInfoModel.innoVersion!}"  class="form-control input-sm"  placeholder="请输入内部版本号" required>
            </div>
        </div>
        <div class="col-xs-12">
            <div class="form-group">
                <label>下载地址</label>
                <input type="text" id="downloadAddress" name="downloadAddress" value="${appUpgradeInfoModel.downloadAddress}" class="form-control input-sm"  placeholder="请输入下载地址" required>
            </div>
        </div>
        <div class="col-xs-12">
            <div class="form-group">
                <label >升级相关描述</label>
                <textarea id="description" name="description" class="form-control" rows="3" placeholder="相关描述">${appUpgradeInfoModel.description}</textarea>
            </div>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="modal-footer margin-top-15">
        <button type="button" id="myButton" class="btn btn-primary center-block" onclick="upGrade()">　提　交　</button>
    </div>
</form>
<#include "../../main/footer.ftl">

<script type="text/javascript">
    function upGrade(){
        var bt=$('#myButton').button('loading');
        if(!$.topjectIsValidate('myForm')){
            bt.button('reset');
            return;
        }
        var paras = {
            id:$("#id").val(),
            type: $("#type").val(),
            isForced: $("#isForced").val(),
            systemVersion: $("#systemVersion").val(),
            innoVersion: $("#innoVersion").val(),
            downloadAddress: $("#downloadAddress").val(),
            description: $("#description").val()
        };
        var url = getRootPath() + '/appUpgrade/saveOrUpdate.do';
        $.ajaxDefaultCall(url,paras,
         function(){
             window.parent.location.href = getRootPath()+'/view/sysAdmin/sysAppUpgrade/list.html'
                })
    }
    $(function (){
        $.topjectValidate('myForm');
    })
</script>
</body>
</html>