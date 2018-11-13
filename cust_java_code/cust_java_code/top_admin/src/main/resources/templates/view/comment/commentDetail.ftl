<#include "../main/top.ftl">
<div class="contentBox" style="padding:10px; font-size:14px">
    <input value="${data.id}" type="hidden" name="id" id="id">
    <input value="${data.version}" type="hidden" name="version" id="version">
  <div class="fixed-table-toolbar">
    <div class="navmenu"><a href="#" onclick="javascript:history.back(-1); return false">评价管理</a> / 详情</div>
    <div class="columns columns-left btn-group pull-left margin-right-10 fontsize-14" ><font class="pull-left margin-right-5">　备注:</font>
      <input class="form-control input-sm pull-left" style=" width:300px;" name="operatorRemark" id="operatorRemarkId" value="${data.operatorRemark!}" placeholder="请输入修改原因" type="text">
    </div>
    <div class="columns columns-left btn-group pull-right">
      <button type="button" class="btn btn-primary btn-sm" onclick="tijiao()">提  交</button>
    </div>
  </div>

  <table class="table table-bordered">
    <tr class="info">
      <td width="25%">订单号</td>
      <td width="25%">评价人姓名</td>
      <td width="25%">评价人手机号</td>
      <td width="25%">所用身份</td>
    </tr>
    <tr>
      <td>${data.orderNo!}</td>
      <td>${data.commentName!}</td>
        <td>${data.commentMobile!}</td>
      <td>
<#--      <#if data.commentType="1">
          司机
      <#else >货主
      </#if>-->

      <#switch data.commentType!>
          <#case "1">司机<#break>
          <#case "2">货主<#break>
      </#switch>
      </td>

    </tr>
    <tr class="info">
      <td>评价时间</td>
      <td>被评价人姓名</td>
      <td>被评价人手机号</td>
      <td>所用身份</td>
    </tr>
    <tr>
      <td>${data.createTime!}</td>
      <td>${data.commentedName!}</td>
        <td>${data.commentedMobile!}</td>
      <td><#switch data.commentedType!>
          <#case "1">司机<#break>
          <#case "2">货主<#break>
      </#switch>
      </td>
    </tr>
    <tr class="danger">
      <td>综合得分</td>
    </tr>
    <tr>
      <td>${data.total}</td>
    </tr>
      <tr class="danger">
          <td colspan="2">评价内容</td>
      </tr>
      <tr>
          <td  colspan="2">
              <textarea name="content" id="content" class="fontsize-14" style="margin-top: 0px; margin-bottom: 0px; height: 61px;">${data.content}</textarea>
          </td>
      </tr>
    <tr class="danger">
        <td colspan="2">上传的图片</td>
    </tr>
      <tr>
          <td  colspan="2">
            <#--  <#list data as listpic>
                  <a href="${listpic.picture1}" target="_blank"><img src="${listpic.picture1}" width="50" height="88"></a>
              </#list>-->
            <#if data.picture1??><a href="${data.picture1}" target="_blank"><img src="${data.picture1}" width="50" height="88"></a></#if>
            <#if data.picture2??><a href="${data.picture2}" target="_blank"><img src="${data.picture2}" width="50" height="88"></a></#if>
            <#if data.picture3??><a href="${data.picture3}" target="_blank"><img src="${data.picture3}" width="50" height="88"></a></#if>
            <#if data.picture4??><a href="${data.picture4}" target="_blank"><img src="${data.picture4}" width="50" height="88"></a></#if>
          </td>
      </tr>

  </table>
  <#--<div>
      <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="#">操作日志</a></li>
      </ul>
      <div id="tabadmin" class="tab-pane" role="tabpanel">
      <div class="bs-callout bs-callout-danger" id="callout-labels-inline-block"> <code>2016-6-17 12:52</code><br>
          彭佳军：修改评价内容 修改原因(非法词组) </div>
      <div class="bs-callout bs-callout-danger" id="callout-labels-inline-block"> <code>2016-6-17 12:36</code><br>
          彭佳军：车型车长 </div>
    </div>
    </div>-->
</div>
</div>
</div>
<#include "../main/footer.ftl">
<div class="modal fade" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content"></div>
  </div>
</div>
<script>
    function tijiao() {
        if ($("#operatorRemarkId").val()==null ||$("#operatorRemarkId").val()=="") {
            bootbox.alert({
                size: 'larger',
                message: '请输入修改原因'
            });
            return;
        }
        var url = getRootPath()+'/comment/update.do?';
        var dataJson={'id':$("#id").val(),'version':$("#version").val(),'content':$("#content").val(),'operatorRemark':$("#operatorRemarkId").val()};
        bootbox.confirm("确认修改评价内容？",function(result){
            if(result){
                $.ajaxDefaultCall(url,dataJson,function(){history.back(-1);});
            }
        })
    }
</script>