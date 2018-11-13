<#include "../main/top.ftl">
<script type="text/javascript" src="<@s.url '/js/bootbox.min.js'/>"></script>
<div class="contentBox" style="padding:10px; font-size:14px">
  <div class="fixed-table-toolbar">
      <input value="${Refund.id!}" type="hidden" id="refundId">
      <input value="${Refund.refundMoney!}" type="hidden" id="refundMoney">
      <input value="${Refund.version!}" type="hidden" id="version">
      <input value="${Refund.orderId!}" type="hidden" id="orderId">
      <input value="${Refund.ownerId!}" type="hidden" id="ownerId">
      <input value="${Refund.orderFrozenState!}" type="hidden" id="orderFrozenState">
      <input value="${Refund.billNo!}" type="hidden" id="billNo">
    <div class="navmenu"><a href="#" onclick="javascript:history.back(-1); return false;">退款申请 </a> / 查看详情</div>
    <div class="columns columns-left btn-group pull-left fontsize-14"><font>处理状态:</font> 
      <select id="csStatusId" name="csStatus" data-toggle="select" class="form-control select select-default select-sm">
        <#if Refund.csStatus == 0><option value="0" > 无客服处理状态</option></#if>
        <option value="3" <#if Refund.csStatus == 3>selected</#if>>同意退款</option>	<!-------选择完2、3提交后，状态变成不可选择选择和提交------->
        <option value="4" <#if Refund.csStatus == 4>selected</#if>>关闭退款</option>
      </select>
    </div>
    <div class="columns columns-left btn-group pull-left margin-right-10 fontsize-14" ><font class="pull-left margin-right-5">　备注:</font>
      <input class="form-control input-sm pull-left" style=" width:300px;"  <#if Refund.csStatus != 1 && Refund.csStatus != 2>disabled</#if>  placeholder="请输入处理结果说明" type="text" id="remarkId" name="remark" value="${Refund.remark}">
    </div>
    <div class="columns columns-left btn-group pull-right">
        <#if Refund.csStatus == 1 || Refund.csStatus == 2 >
            <#if Refund.rfOrderStatus == Refund.oiOrderStatus>
              <button type="button" class="btn btn-primary btn-sm" onclick="submitRefund()">提  交</button>
            </#if>
        </#if>
    </div>
  </div>
  <table class="table table-bordered">
    <tr class="info">
      <td width="25%">发货人姓名</td>
      <td width="25%">发货人手机号</td>
      <td width="25%">承运人姓名</td>
      <td width="25%">承运人收机号</td>
    </tr>
    <tr>
      <#if Refund.triggerId == Refund.driverId>
          <td><a href="#">${Refund.oppositeName!}</a></td>
          <td>${Refund.oppositMobile!}</td>
          <td><a href="#">${Refund.triggerName!}</a><code>发起退款</code></td>
          <td>${Refund.triggerMobile}</td>
          <#else>
              <td><a href="#">${Refund.triggerName!}</a><code>发起退款</code></td>
              <td>${Refund.triggerMobile!}</td>
              <td><a href="#">${Refund.oppositeName!}</a></td>
              <td>${Refund.oppositMobile!}</td>
      </#if>


    </tr>
    <tr class="info">
      <td>订单号</td>
      <td>订单状态</td>
      <td>托管运费金额</td>
      <td>托管定金金额</td>
    </tr>
    <tr>
      <td><a href="#">${Refund.orderNo!}</a></td>
      <td>提货中</td>
      <td>${Refund.freightFee?string.currency}</td>
      <td>${Refund.agencyFee?string.currency}</td>
    </tr>
    <tr class="danger">
      <td colspan="4">退款原因</td>
    </tr>
    <tr>
      <td colspan="4">
      <#--<#switch Refund.reason>-->
        <#--<#case 1>双方协商  ${Refund.userRemark}<#break>-->
        <#--<#case 2>没有按时提货  ${Refund.userRemark}<#break>-->
        <#--<#case 3>找错车了  ${Refund.userRemark}<#break>-->
        <#--<#case 4>货物描述有误  ${Refund.userRemark}<#break>-->
        <#--<#case 5>货物不存在  ${Refund.userRemark} <#break>-->
        <#--<#case 6>${Refund.userRemark}<#break>-->
      <#--</#switch>-->
          ${Refund.reason!} ${Refund.userRemark!}
      </td>
    </tr>
     <tr>
             <#if (provePhotoList??)>
                 <#list provePhotoList as phl>
                     <td>
                         <div class="mag">
                             <a href="${phl.pictureUrl!'#'}" target="_blank"><img data-toggle="magnify" src="${phl.pictureUrl?default("")}" class="img-thumbnail img-responsive" style="max-width:200px" ></a>
                         </div>
                     </td>
                 </#list>
             <#else>
             <td colspan="4">
             </td>
         </#if>
     </tr>
      <tr class="danger">
          <td colspan="4">反驳原因</td>
      </tr>
      <tr>
          <td colspan="4">
          ${Refund.repulseReason!} ${Refund.repulseRemark!}
          <#--<#switch Refund.repulseReason>-->
              <#--<#case 1>双方协商  ${Refund.repulseRemark}<#break>-->
              <#--<#case 2>没有按时提货  ${Refund.repulseRemark}<#break>-->
              <#--<#case 3>找错车了  ${Refund.repulseRemark}<#break>-->
              <#--<#case 4>货物描述有误  ${Refund.repulseRemark}<#break>-->
              <#--<#case 5>货物不存在  ${Refund.repulseRemark} <#break>-->
              <#--<#case 6>${Refund.repulseRemark}<#break>-->
          <#--</#switch>-->
          </td>
      </tr>
      <tr>
      <#if (refutePhotoList??)>
          <#list refutePhotoList as phl>
              <td>
                  <div class="mag">
                      <a href="${phl.pictureUrl!'#'}" target="_blank"><img data-toggle="magnify" src="${phl.pictureUrl?default("")}" class="img-thumbnail img-responsive" style="max-width:200px" ></a>
                  </div>
              </td>
          </#list>
      <#else>
          <td colspan="4">
          </td>
      </#if>
      </tr>
  </table>
  <#--<div>
      <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="#">操作日志</a></li>
      </ul>
      <div id="tabadmin" class="tab-pane" role="tabpanel">
      <div class="bs-callout bs-callout-danger" id="callout-labels-inline-block"> <code>2016-6-17 12:52</code><br>
        侯小梅：已退款 </div>
      <div class="bs-callout bs-callout-danger" id="callout-labels-inline-block"> <code>2016-6-17 12:36</code><br>
        彭佳军：处理中 备注(司机电话没有打通) </div>
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
    function submitRefund() {
        var orderFrozenState = $("#orderFrozenState").val();
        if(orderFrozenState==1){
            bootbox.alert({
                size : 'small',
                message :"该订单处于冻结状态，无法操作！",
            });
            return;
        }
        var csStatus = $("#csStatusId").val();
        var url = getRootPath()+'/refund/agreeRefund.do?';
        var remark = "确定同意此退款吗？";
        if(csStatus == 4){
            url = getRootPath()+'/refund/updateRefund.do?';
            remark = "确定要关闭此退款吗？";
        }
        var dataJson={'id':$("#refundId").val().replace(/,/g,""),'csStatus':csStatus,'remark':$("#remarkId").val(),'refundMoney':$("#refundMoney").val(),'billNo':$("#billNo").val(),'ownerId':$("#ownerId").val().replace(/,/g,""),'orderId':$("#orderId").val().replace(/,/g,""),'version':$("#version").val()};
        bootbox.confirm(remark,function(result){
            if(result){
                $.ajaxDefaultCall(url,dataJson,function(){history.back(-1);});
               /* $.ajax({
                    type:"POST",
                    contentType: "application/x-www-form-urlencoded;charset=utf-8",
                    url:url,
                    data:dataJson,
                    dataType:"json",
                    cache:false,
                    success:function(data){
                        if(data != null){
                            alert(data.msg);
                            this.history.back(-1);
                        }
                    },
                    error:function()
                    {
                        alert("网络异常");
                    }
                });*/
            }
        })
    }
</script>