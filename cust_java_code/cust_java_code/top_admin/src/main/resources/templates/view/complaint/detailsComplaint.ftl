<#include "../main/top.ftl">
<div class="contentBox" style="padding:10px; font-size:14px">
    <input value="${complaintDetails.id?c}" type="hidden" id="complaintId">
    <input value="${complaintDetails.version}" type="hidden" id="versionId">
  <div class="fixed-table-toolbar">
    <div class="navmenu"><a href="#" onclick="javascript:history.back(-1); return false">投诉管理</a> / 查看详情</div>
    <div class="columns columns-left btn-group pull-left fontsize-14"><font>处理状态状态:</font> 
      <select id="statusId" name="status" data-toggle="select" class="form-control select select-default select-sm">
          <option value="1" <#if complaintDetails.status == 1>selected</#if> >待处理</option>
          <option value="2" <#if complaintDetails.status == 2>selected</#if>>投诉属实</option>
          <option value="3" <#if complaintDetails.status == 3>selected</#if>>投诉和解</option>
          <option value="4" <#if complaintDetails.status == 4>selected</#if>>虚假投诉</option>
      </select>
    </div>
    <div class="columns columns-left btn-group pull-left margin-right-10 fontsize-14" ><font class="pull-left margin-right-5">　备注:</font>
      <input class="form-control input-sm pull-left" style=" width:300px;" name="remark" id="remarkId" value="${complaintDetails.remark}" placeholder="请输入处理结果说明" type="text">
    </div>
    <div class="columns columns-left btn-group pull-right">
        <#if complaintDetails.status == 1>
          <button type="button" class="btn btn-primary btn-sm" onclick="submitComplaint()">提  交</button>
        </#if>
    </div>
  </div>
  <table class="table table-bordered">
    <tr class="info">
      <td width="25%">投诉人手机号</td>
      <td width="25%">投诉人姓名</td>
      <td width="25%">常用身份</td>
      <td width="25%">投诉人身份证号</td>
    </tr>
    <tr>
      <td>${complaintDetails.mobile}</td>
      <td>${complaintDetails.name}</td>
      <td><#switch complaintDetails.complaintType>
          <#case 1>司机<#break>
          <#case 2>货主<#break>
          <#case 3>信息部<#break>
          <#case 4>物流公司<#break>
      </#switch>
      </td>
          <td>${complaintDetails.complaintCardID}</td>
    </tr>
    <tr class="info">
      <td>被投诉人手机号</td>
      <td>被投诉人姓名</td>
      <td>常用身份</td>
      <td>被投诉人身份证号</td>
    </tr>
    <tr>
      <td>${complaintDetails.complaintedMobile}</td>
      <td>${complaintDetails.complaintedName}</td>
      <td><#switch complaintDetails.complaintedType>
          <#case 1>司机<#break>
          <#case 2>货主<#break>
          <#case 3>信息部<#break>
          <#case 4>物流公司<#break>
      </#switch>
      </td>
        <td>${complaintDetails.complaintedCardID}</td>
    </tr>
    <tr class="danger">
      <td>投诉编号</td>
      <td>投诉时间</td>
      <td>投诉类型</td>
      <td>类型相关</td>
    </tr>
    <tr>
      <td>${complaintDetails.id}</td>
        <td>${complaintDetails.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
      <td>订单</td>
      <td>${complaintDetails.orderSerialNo}</td>
    </tr>
    <tr class="danger">
      <td colspan="4">投诉说明</td>
    </tr>
    <tr>
      <td colspan="4">${complaintDetails.content}</td>
    </tr>
  </table>
  <div id = "operaLog">
      <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="#">操作日志</a></li>
      </ul>
      <div id="tabadmin" class="tab-pane" role="tabpanel">
              <div class="bs-callout bs-callout-danger" id="callout-labels-inline-block"> <code>${(complaintDetails.updateTime?string("yyyy-MM-dd HH:mm:ss"))!""}</code><br>
              ${(complaintDetails.auditName)!""}：${statusName} ${(complaintDetails.remark)!""} </div>
    </div>
    </div>
</div>
<input type="hidden" id="remark" name="remark" value="${complaintDetails.remark}">
</div>
</div>
<#include "../main/footer.ftl">
<div class="modal fade" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content"></div>
  </div>
</div>
<script>
    //页面加载
    $(document).ready(function(){
        if($("#remark").val() == ""){
            $("#tabadmin").hide();
        }else{
            $("#tabadmin").show();
        }
    });
    function submitComplaint() {
        if ($("#remarkId").val()==null ||$("#remarkId").val()=="") {
            bootbox.alert({
                size: 'larger',
                message: '请输入处理结果说明'
            });
            return;
        }
        var url = getRootPath()+'/complaint/dealWithComplaint.do?';
        var dataJson={'id':$("#complaintId").val(),'status':$("#statusId").val(),'remark':$("#remarkId").val(),'version':$("#versionId").val()};
        bootbox.confirm("你确定要修改次状态吗？",function(result){
            if(result){
                $.ajaxDefaultCall(url,dataJson,function(){history.back(-1);});
            }
        })
    }
</script>