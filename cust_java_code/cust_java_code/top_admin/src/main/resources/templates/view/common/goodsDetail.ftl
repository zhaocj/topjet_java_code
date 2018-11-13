<#include "../main/top.ftl">
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=9DzOnQ7n1qMwAAZ6SZka1p3z9S0MD2Fa"></script>
<#assign base=request.contextPath />
<div class="contentBox" style="padding:10px; font-size:14px">
    <input value="${data.id!}" type="hidden" name="id" id="id">
    <input value="${data.version!}" type="hidden" name="version" id="version">
    <div class="fixed-table-toolbar">
        <div class="navmenu"><a href="#" onclick="javascript:history.back(-1); return false">货源查询</a> / 货源详情</div>
    </div>

    <table class="table table-bordered">
        <tr class="info">
            <td width="20%">货源号</td>
            <td width="20%">发布人姓名</td>
            <td width="20%">发布人手机号</td>
            <td width="20%">收货人姓名</td>
            <td width="20%">收货人手机号</td>
        </tr>
        <tr>
            <td>${data.orderNo!}</td>
            <td>${data.username!}</td>
            <td>${data.mobile!}</td>
            <td>${data.receiveName!}</td>
            <td>${data.receiveMobile!}</td>
        </tr>

        <tr class="info">
            <td>运费方式</td>
            <td>卸货方式</td>
            <td>提货时间类型</td>
            <td>提货时间</td>
            <td>包装方式</td>
        </tr>
        <tr>
            <td>
            <#if (data.payStyle)?default('-')=='1'>货到付款</#if>
            <#if (data.payStyle)?default('-')=='2'>提付全款<</#if>
            <#if (data.payStyle)?default('-')=='3'>提付部分运费</#if>
            <#if (data.payStyle)?default('-')=='4'>回单付运费</#if>
            </td>
            <td>${data.loadType!}</td>
            <td>
            <#if (data.loadDateType)?default('-')=='0'>具体时间</#if>
            <#if (data.loadDateType)?default('-')=='1'>今定今装</#if>
            <#if (data.loadDateType)?default('-')=='2'>今定明装</#if>
            <#if (data.loadDateType)?default('-')=='3'>随到随走</#if>
            </td>
            <td>
            ${data.loadDate?datetime("yyyy-MM-dd HH:mm:ss")}
            </td>
            <td>
            ${data.packType!}
            </td>
        </tr>
        <tr class="info">
            <td>车型需求</td>
            <td>发货地</td>
            <td>目的地</td>
            <td>数量单位</td>
            <td>备注</td>
        </tr>
        <tr >
            <td>${data.truckType!}</td>
            <td>
            ${data.sendAddress!}
            </td>
            <td>
            ${data.receiveAddress!}
            </td>
            <td>
            <#if (data.quantityType == 1)>${data.quantityMax!}</#if>
            <#if (data.quantityType == 2)>${data.quantityMax!}-${data.quantityMin!}</#if>&nbsp;${data.unit!}
            </td>
            <td>${data.textRemark!}
            <#if (data.photoRemark)?default('') !=''><a href="${data.photoRemark}" title="查看备注图片" target="_black"> <i class="icon-picture"></i></a></#if>
            </td>
        </tr>
    </table>
    <div>
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="#depart" data-toggle="tab">发货人信息</a></li>
            <li role="presentation"><a href="#destination" data-toggle="tab">收货人信息</a></li>

          <#--  <li role="presentation" class="active"><a href="#depart" data-toggle="tab">发货人信息</a></li>
            <li role="presentation"><a href="#destination" data-toggle="tab">收货人信息</a></li>-->
        </ul>
        <div class="tab-content" >

            <div id="depart" class="tab-pane active" role="tabpanel">
                <ul class="formationBox">
                    <li><span>发货人姓名：</span>${data.username!}</li>
                    <li><span>发货人手机号：</span>${data.mobile!}</li>
                    <li><span>发货地址：</span>${data.sendAddress!}</li>
                </ul>
            </div>
            <div id="destination" class="tab-pane" role="tabpanel">
                <ul class="formationBox">
                    <li><span>收货人姓名：</span>${data.receiveName!}</li>
                    <li><span>收货人手机号：</span>${data.receiveMobile!}</li>
                    <li><span>收货地址：</span>${data.receiveAddress!}</li>
                </ul>
            </div>
        </div>

    </div>
</div>

</div>
</div>
</div>
</div>
<#include "../main/footer.ftl">
<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content"></div>
    </div>
</div>
