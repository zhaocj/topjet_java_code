<#include "../main/top.ftl">
<input type="hidden" id="id" name="id" value="${truckLengthBean.id?c}"/>
<form id="myForm">
    <input type="hidden" id="displayNameOlder" name="displayNameOlder" value="${truckLengthBean.displayName}">
    <input type="hidden" id="lengthIdOlder" name="lengthIdOlder" value="${truckLengthBean.length}">
    <input type="hidden" id="validOlder" name="validOlder" value="${truckLengthBean.valid}">
    <div class="modal-body  fontsize-14">
        <div class="col-xs-6">
            <div class="form-group">
                <label>车长显示名称：</label>
                <input type="text" class="form-control input-sm" name="displayName" id="displayName"
                       value="${truckLengthBean.displayName!'' }">
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>车长：</label>
                <input type="text" class="form-control input-sm" name="lengthId" id="lengthId"
                       value="${truckLengthBean.length!'' }" readonly="readonly">
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>是否启用：</label>
                <select id="valid" name="valid" data-toggle="select"
                        class="form-control select select-default select-sm">
                    <option value="">请选择</option>
                    <option value="0"  <#if truckLengthBean.valid == 0>selected</#if>>不可用</option>
                    <option value="1"  <#if truckLengthBean.valid == 1>selected</#if>>可用</option>
                </select>
            </div>
        </div>
        <div class="clearfix"></div>
        <div class="modal-footer margin-top-15">
            <button type="button" class="btn btn-primary center-block" type="submit" id="submitButton"
                    onclick="confirmSubmit()">　提　交　
            </button>
        </div>
</form>
<#include "../main/footer.ftl">
<script type="text/javascript">
    function confirmSubmit() {
        if (IsFormChangeTrue("myForm")) {
            var url = getRootPath() + '/truckLength/update.do';
            var json = {
                id:${truckLengthBean.id},
                displayName: $('#displayName').val(),
                length: $('#length').val(),
                valid: $('#valid').val(),
            }
            window.parent.$.ajaxDefaultCall(url, json, function () {
                window.parent.location.href = getRootPath() + '/view/truckManage/truckLengthList.html'
            });
        }else{
            window.parent.location.href = getRootPath() + '/view/truckManage/truckLengthList.html'
        }
    }
</script>
