<#include "../main/top.ftl">
<script src="http://webapi.amap.com/maps?v=1.3&key=66d5100afbaea514dc036dce28ed6203"></script>

<#assign base=request.contextPath />
<div class="contentBox" style="padding:10px; font-size:14px">
    <input value="${data.id?c}" type="hidden" name="id" id="id">
    <input value="${data.version}" type="hidden" name="version" id="version">
    <input value="${data.orderStatus}" type="hidden" name="orderStatus" id="orderStatus">
    <input value="${data.driverId}" type="hidden" name="driverId" id="driverId">
    <div class="fixed-table-toolbar">
        <div class="navmenu"><a href="#" onclick="javascript:history.back(-1); return false">订单管理</a> / 订单资料</div>
    </div>

    <table class="table table-bordered">
        <tr class="info">
            <td width="20%">订单号</td>
            <td width="20%">发布时间</td>
            <td width="20%">发布人姓名</td>
            <td width="20%">发布人手机号</td>
            <td width="20%">订单状态</td>
        </tr>
        <tr>
            <td>${data.orderNo!}</td>
            <td>${(data.createDate?datetime("yyyy-MM-dd HH:mm:ss"))!""}</td>
            <td>${data.username!}</td>
            <td>${data.mobile!}</td>
            <td>
             <#switch data.orderStatus>
                 <#case "1">货主取消交易<#break >
                 <#case "2">货主确认成交<#break >
                 <#case "3">待支付定金<#break >
                 <#case "4">待支付运费<#break >
                 <#case "5">提货中<#break >
                 <#case "6">承运中<#break >
                 <#case "7">已承运<#break >
                 <#case "8">货主已评司机未评价<#break >
                 <#case "9">司机已评货主未评价<#break >
                 <#case "10">双方已评价<#break >
                 <#case "11">订单已退款<#break >
             <#--<#case "12">系统到达提货时间撤销<#break >
            <#case "13">货主主动撤销<#break >
            <#case "14">退款导致的撤销<#break >-->
             </#switch>
            </td>
        </tr>

        <tr class="info">
            <td>运费总额</td>
            <td>提付运费</td>
            <td>到付运费</td>
            <td>回单付运费</td>
            <td>定金</td>
        </tr>
        <tr>
            <td>${data.amtSum!}
                <font class="text-warning"><#if data.freightFeeStatus=='1' || data.freightFeeStatus=='2' || data.freightFeeStatus=='3'>（托管）</#if>
                <#if data.freightFeeStatus=='0'>（不托管）</#if></font>
            <#if data.freightFeeStatus=='1'><code title="未托管到平台">未托管</code></#if>
            <#if data.freightFeeStatus=='2'><code title="已托管到平台">未支付</code></#if>
            <#if data.freightFeeStatus=='3'><code title="已支付到车主钱包中">已支付</code></#if>
            <#if <#--data.freightFeeStatus=='1' || -->data.freightFeeStatus=='3' ><code >支付时间：</code>${(data.aheadFeeTime?datetime("yyyy-MM-dd HH:mm:ss"))!""}</#if>
            </td>

            <td>${data.aheadFee!}
                <font class="text-warning"><#if data.aheadFeeStatus=='1' || data.aheadFeeStatus=='2' || data.aheadFeeStatus=='3'>（托管）</#if>
                <#if data.aheadFeeStatus=='0'>（不托管）</#if></font>
            <#if data.aheadFeeStatus=='1'><code title="未托管到平台">未托管</code></#if>
            <#if data.aheadFeeStatus=='2'><code title="已托管到平台">未支付</code></#if>
            <#if data.aheadFeeStatus=='3'><code title="已支付到车主钱包中">已支付</code></#if>
            </td>

            <td>${data.deliveryFee!}
                <font class="text-warning"><#if data.deliveryFeeStatus=='1' || data.deliveryFeeStatus=='2' || data.deliveryFeeStatus=='3'>（托管）</#if>
                <#if data.deliveryFeeStatus=='0'>（不托管）</#if></font>
            <#if data.deliveryFeeStatus=='1'><code title="未托管到平台">未托管</code></#if>
            <#if data.deliveryFeeStatus=='2'><code title="已托管到平台">未支付</code></#if>
            <#if data.deliveryFeeStatus=='3'><code title="已支付到车主钱包中">已支付</code></#if>
            </td>

            <td>${data.backFee!}</td>

            <td>${data.agencyFee!}
            <#if data.agencyStatus=='0'>(不托管)</#if>
            <#if data.agencyStatus=='1'><code title="未托管到平台">未托管</code></#if>
            <#if data.agencyStatus=='2'><code title="已托管到平台"</code>未支付</#if>
            <#if data.agencyStatus=='3'><code title=">已支付到车主钱包中">已支付</code></#if>
            <#if data.agencyStatus=='3' ><code >支付时间：</code>${(data.agencyFeeTime?datetime("yyyy-MM-dd HH:mm:ss"))!""}</#if>
            </td>
        </tr>
        <tr class="info">
            <#--<td>签收时间</td>-->
            <td>发货地</td>
            <td>目的地</td>
        </tr>
        <tr >
          <#--  <td>
                <#if data.signDate??>
                    ${data.signDate?datetime("yyyy-MM-dd HH:mm")}
                </#if>
            </td>-->
            <td>
             ${data.sendAddress!}
            </td>
            <td>
            ${data.receiveAddress!}
            </td>
        </tr>
        <#--<tr class="success">
            <td colspan="1">补帖发货人运费</td>
            <td colspan="4">${data.ownerAmount!"0.00"}</td>

        </tr>
        <tr class="success">
            <td colspan="1">补帖承运人运费</td>
            <td colspan="4">
            ${data.driverAmount!"0.00"}
            </td>
        </tr>-->
    </table>
    <div>
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="#depart" data-toggle="tab">货物信息</a></li>
            <li role="presentation"><a href="#destination" data-toggle="tab">发货人信息</a></li>
            <li role="presentation"><a href="#shouhuoren" data-toggle="tab">收货人信息</a></li>
           <li role="presentation"><a href="#chengyun" data-toggle="tab">承运信息</a></li>
            <li role="presentation"><a href="#runninnTrack" onclick="ditu()" data-toggle="tab">运行轨迹</a></li>
        </ul>
        <div class="tab-content" >
            <div id="depart" class="tab-pane active" role="tabpanel">
                <ul class="formationBox">
                    <li><span>货物名称：</span>${data.category!}</li>
                    <li><span>提货时间类型：</span>
                    <#if (data.loadDateType)?default('-')=='0'>具体时间</#if>
                    <#if (data.loadDateType)?default('-')=='1'>今定今装</#if>
                    <#if (data.loadDateType)?default('-')=='2'>今定明装</#if>
                    <#if (data.loadDateType)?default('-')=='3'>随到随走</#if></li>
                    <li><span>提货时间：</span>${(data.loadDate?datetime("yyyy-MM-dd HH:mm:ss"))!""}</li>
                    <li><span>包装方式：</span>${data.packType!}</li>
                    <li><span>卸货方式：</span>
                        ${data.loadType!}
                    </li>
                    <li><span>数量&nbsp;单位：</span>
                    <#if (data.quantityType)?default('-')==1>${data.quantityMax!}</#if>
                    <#if (data.quantityType)?default('-')==2>${data.quantityMax!}-${data.quantityMin!}</#if>&nbsp;${data.unit!}
                    </li>
                    <li><span>车型需求：</span>${data.truckType!}</li>
                    <!------------备注中的图片和语音，有就显示图标，点击查看或收听，没有就不用显示图标---------->
                    <li><span>备注：</span>${data.textRemark!}
                    <#if (data.photoRemark)?default('') !=''><a href="${data.photoRemark}" title="查看备注图片" target="_black"> <i class="icon-picture"></i></a></#if>
                    <#--<#if (data.audioRemarkUrl)?default('') !=''><a href="${data.audioRemarkUrl}" title="收听备注留言"> <i class="icon-volume-up"></i></a></#if>-->
                    </li>
                </ul>
            </div>

            <div id="destination" class="tab-pane" role="tabpanel">
                <ul class="formationBox">
                    <li><span>发货人姓名：</span>${data.sendName!}</li>
                    <li><span>发货人手机号：</span>${data.sendMobile!}</li>
                    <li><span>发货地址：</span>${data.sendAddress!}</li>
                    <li><span>提货码：</span><code>${data.sendPickupCode!}</code></li>
                </ul>
            </div>
            <div id="shouhuoren" class="tab-pane" role="tabpanel">
                <ul class="formationBox">
                    <li><span>收货人姓名：</span>${data.receiveName!}</li>
                    <li><span>收货人手机号：</span>${data.receiveMobile!}</li>
                    <li><span>收货地址：</span>${data.receiveAddress!}</li>
                    <li><span>收货码：</span><code>${data.receiveUnloadCode!}</code></li>
                </ul>
            </div>
            <div id="chengyun" class="tab-pane " role="tabpanel">
                <ul class="formationBox">
                    <li><span>车主姓名：</span>${data.driverName!}</li>
                    <li><span>手机号：</span>${data.driverMobile!}</li>
                    <li><span>当前位置：</span>${data.driverDetail!}</li>
                    <li><span>运输车辆：</span>${data.truckInfo!}</li>
                </ul>
            </div>
            <div id="runninnTrack" style="height: 900px;width: 100%" class="tab-pane " role="tabpanel">
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
<script>
    function tijiao() {
        var url = getRootPath() + '/comment/update.do?';
        var dataJson = {
            'id': $("#id").val(),
            'version': $("#version").val(),
            'content': $("#content").val(),
            'inTime': $("#inTime").val(),
            'facticity': $("#facticity").val(),
            'attitude': $("#attitude").val()
        };
       /* bootbox.confirm("确认修改评价内容？", function (result) {
            if (result) {
                $.ajaxDefaultCall(url, dataJson, function () {
                    history.back(-1);
                });
            }
        })*/
    }

   function ditu() {
        debugger;
      var orderStatus =$("#orderStatus").val();
      //订单 已签收
      if(orderStatus == 7 ||orderStatus == 8 || orderStatus ==9 ||orderStatus == 10 ){
          var json = {
              id: $('#id').val(),
              driverId:$("#driverId").val().replace(/,/g, "")
          };
          $.ajax({
              type:"POST",
              contentType: "application/x-www-form-urlencoded;charset=utf-8",
              url:getRootPath() + '/order/getRunninnTrack.do',
              data:json,
              success:function(dataList){
                  debugger;
                  var point =  new AMap.LngLat(dataList[0][0].longitude, dataList[0][0].latitude);
                  var endPoint =new AMap.LngLat(dataList[0][1].longitude, dataList[0][1].latitude);
                  var coordinateArr = [];
                  //初始化之后才能操作  2个参数，一个中心点，一个是地图的级别
                  coordinateArr.push(point);//push第一个点

                  //点数小于于18  全部都要
          /*        if(0<dataList[1].length  &&  dataList[1].length <= 18){
                      for(var i =0;i<dataList[1].length;i++){
                          var new_point = new AMap.LngLat(dataList[1][i].longitude, dataList[1][i].latitude);
                          coordinateArr.push(new_point);
                      }
                  }else if(dataList[1].length > 18){
                      for(var i =0;i<18;i++){
                          var new_point = new AMap.LngLat(dataList[1][i].longitude, dataList[1][i].latitude);
                          coordinateArr.push(new_point);
                      }
                  }*/
                  for(var i =0;i<dataList[1].length;i++){
                      var new_point = new AMap.LngLat(dataList[1][i].longitude, dataList[1][i].latitude);
                      coordinateArr.push(new_point);
                  }

                  coordinateArr.push(endPoint);

                  var map = new AMap.Map('runninnTrack', {
                      resizeEnable: true,
                      center: point,//中心点
                      zoom: 12
                  });
                  //标记点
                  var markers = [], positions = [point, endPoint];
                  for (var i = 0, marker; i < positions.length; i++) {
                      marker = new AMap.Marker({
                          map: map,
                          position: positions[i]
                      });
                      markers.push(marker);
                  }
                  var polyline = new AMap.Polyline({
                      path: coordinateArr,          //设置线覆盖物路径
                      strokeColor: "#FF0000", //线颜色
                      strokeOpacity: 0.5,       //线透明度
                      strokeWeight: 2,        //线宽
                      strokeStyle: "solid",   //线样式
                      strokeDasharray: [10, 5] //补充线样式
                  });
                  polyline.setMap(map);

              }
          });

      }else{
           alert("订单已签收才能查看轨迹！！！！");
          return ;
      }

    }




</script>