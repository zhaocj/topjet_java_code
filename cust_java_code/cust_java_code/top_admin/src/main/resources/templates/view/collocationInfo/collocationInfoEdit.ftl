<#include "../main/top.ftl">
<input type="hidden" id="id" name="id" value="${collocationInfo.id?c}"/>

    <input type="hidden" id="displayNameOlder" name="displayNameOlder" value="${collocationInfo.collocationName}">
    <div class="modal-body  fontsize-14">
        <div class="col-xs-6">
            <div class="form-group">
                <label>名称：</label>
                <input type="text" class="form-control input-sm" maxlength="10" name="collocationName" id="collocationName"
                       value="${collocationInfo.collocationName!'' }">
            </div>
        </div>
        <div class="clearfix"></div>
        <div class="modal-footer margin-top-15">
            <button type="button" class="btn btn-primary center-block" type="button" id="submitButton"
                    onclick="confirmSubmit()">　提　交　
            </button>
        </div>
<#include "../main/footer.ftl">
<script type="text/javascript">
    function confirmSubmit() {

        if(getRealLength($.trim($("#collocationName").val()))>14){
            bootbox.alert({
                size: 'small',
                message: "调研报告名称最多不超过7个字!",
            });
            return;
        }
        var url = getRootPath() + '/collocationInfo/update.do';
        var json = {
                id:${collocationInfo.id},
            collocationName: $('#collocationName').val(),
        }
        window.parent.$.ajaxDefaultCall(url, json, function () {
                window.parent.location.href = getRootPath() + '/view/collocationInfo/list.html?collocationType=${collocationInfo.collocationType }'
        });
    }

    function getRealLength(str) {
        var len = 0;
        for (var i=0; i<str.length; i++) {
            if (str.charCodeAt(i)>127 || str.charCodeAt(i)==94) {
                len += 2;
            } else {
                len ++;
            }
        }
        return len;
    }

</script>
