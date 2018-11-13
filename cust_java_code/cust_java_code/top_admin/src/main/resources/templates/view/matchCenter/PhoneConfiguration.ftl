<#include "../main/top.ftl">
<script type="text/javascript" src="<@s.url '/js/common/modal.js'/>"></script>
<div class="contentBox" style="padding:10px">
    <div class="fixed-table-toolbar">
    </div>
    <table class="table table-bordered" id="btnedit">
        <thead>
        <tr>
            <th style="width:120px">电话</th>
            <th style="width:120px">操作</th>
        </tr>
        </thead>
        <tr>
            <#if (dataList?size>0)>
                <td width="10px" id=duration class="idedit">${dataList.collocationName} </td>
                <td width="10px"><input type="button" class="btn btn-primary btn-sm" onclick="edit(${dataList.collocationName})" value="编辑"></td>
            <#else>
                <td width="10px" id=duration class="idedit"> </td>
                <td width="10px"><input type="button" class="btn btn-primary btn-sm" onclick="edit()" value="编辑"></td>
            </#if>

        </tr>
    </table>
</div>
<div id='modalEdit' class='modal fade' tabindex='-1' role='dialog' aria-labelledby='myLargeModalLabel' aria-hidden="true">
    <div class='modal-dialog'>
        <div class='modal-content'>
            <div class='modal-header'>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class='modal-title' id='myModalLabel'>编辑</h4>
                <div class='modal-body'>
                    <iframe id='editIframeId' scrolling='no' onLoad='setIframeSizeMax()'  frameborder='0' src='' style="width: 845px !important;height: AUTO !important;MIN-HEIGHT: 210px !important;"></iframe>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "../main/footer.ftl">

<script type="text/javascript">

    function edit(collocationName){
        var url = getRootPath()+'/view/matchCenter/editPhone.html?collocationName='+collocationName;
        modalEdit(url,'匹配中心电话','lg');
    }
</script>
