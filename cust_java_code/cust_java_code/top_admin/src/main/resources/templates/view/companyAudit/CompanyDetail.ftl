<#include "../main/top.ftl">
<input type="hidden" id="userId" name="id" value="${userInfoBean.id?c}"/>
<input type="hidden" id="businessAddressCity1" value="${userInfoBean.businessAddressCity1!""}"/>
<input type="hidden" id="businessAddressCity2" value="${userInfoBean.businessAddressCity2!""}"/>
<input type="hidden" id="businessAddressCity3" value="${userInfoBean.businessAddressCity3!""}"/>
<input type="hidden" id="businessPhotoUrl" value="${userInfoBean.businessPhotoUrl!""}"/>
<input type="hidden" id="shopFrontPhotoUrl" value="${userInfoBean.shopFrontPhotoUrl!""}"/>
<input type="hidden" id="mobile" value="${userInfoBean.mobile! }"/>
<input type="hidden" id="companyStatus" value="${userInfoBean.companyStatus! }"/>
<script type="text/javascript" src="../js/common/jquery.cityselect.js"></script>

<div class="contentBox" style="padding:10px; font-size:14px">
    <div class="fixed-table-toolbar">
        <div class="navmenu"><a href="#" onclick="javascript:history.back(-1); return false">企业认证审核</a> / 维护</div>
        <div class="columns columns-left btn-group pull-right">
            <button type="button" id="submit" class="btn btn-primary btn-sm" onclick="update()">提 交</button>
        </div>
    </div>
    <table class="table table-bordered">
        <tr class="info">
            <td width="20%">企业名称</td>
            <td width="20%">手机号</td>
            <td width="20%">用户姓名</td>
            <td width="20%">信用代码</td>
        </tr>
        <tr>
            <td style="font-size:19px;"><input type="text"  class="form-control input-sm input-width"  id="companyName" style="width: 80%"  value="${userInfoBean.companyName!}"></td>
            <td style="font-size:19px;">${userInfoBean.mobile!}</td>
            <td style="font-size:19px;"><input type="text" id="username" class="form-control input-sm input-width"  style="width: 80%"  value="${userInfoBean.username!}"></td>
            <td style="font-size:19px;"><input type="text" id="creditCode" class="form-control input-sm input-width"  style="width: 80%"  value="${userInfoBean.creditCode!}"></td>
        </tr>
        <tr class="info">
            <td>身份类型</td>
            <td>门头照</td>
            <td>营业执照</td>
            <td>经营地址</td>
        </tr>
        <tr>
            <td style="font-size:22px;">
                <select id="identityType" class="form-control" >
                    <option value="0"  <#if userInfoBean.identityType == 0>selected</#if>>非企业用户</option>
                    <option value="1"  <#if userInfoBean.identityType == 1>selected</#if>>物流公司</option>
                    <option value="2"  <#if userInfoBean.identityType == 2>selected</#if>>发货商家</option>
                </select>
            </td>
            <td class="ppad">
                <#if userInfoBean.shopFrontPhotoUrl??>
                    <div class="input-group">
                        <input id="fileId1" name="fileId1" type="file" onchange="readFile(this,'shopFrontPhotoUrl','fileImageId1')">
                        <img id="fileImageId1" alt="图片库不存在此图片，请重新上传图片"  style="max-width:300px;margin-top: 10px;margin-left: 10px!important;"  src="${(userInfoBean.shopFrontPhotoUrl)!''}"  />
                    </div>
                </#if>
            </td>
            <td class="ppad">
                <#if userInfoBean.businessPhotoUrl??>
                    <div class="input-group">
                        <input id="fileId2" name="fileId2" type="file" onchange="readFile(this,'businessPhotoUrl','fileImageId2')">
                        <img id="fileImageId2" alt="图片库不存在此图片，请重新上传图片"  style="max-width:300px;margin-top: 10px;margin-left: 10px!important;"  src="${(userInfoBean.businessPhotoUrl)!''}"  />
                    </div>
                </#if>
            </td>
            <td style="font-size:16px;" id="businessAddressCity">
                <select id="businessAddressCity_1" placeholder="请选择省" class="prov">
                </select>
                <select id="businessAddressCity_2" placeholder="请选择市" class="city">
                </select>
                <select id="businessAddressCity_3" placeholder="请选区或县" class="dist">
                </select></td>
        </tr>
        <tr class="info">
            <td>固定电话</td>
            <td>备注</td>
            <td colspan="2">经营地址</td>
        </tr>
        <tr>
            <td style="font-size:19px;"><input type="text" id="telephone" class="form-control input-sm input-width"  style="width: 80%" required value=" ${userInfoBean.telephone!}"></td>
            <td style="font-size:19px;"><textarea class="form-control" rows="3"  name="internalRemark" id="internalRemark"  value="${userInfoBean.internalRemark!}" placeholder="输入备注"  maxlength="250">${userInfoBean.internalRemark!}</textarea></td>
            <td style="font-size:19px;" colspan="2">
                <div id="maodian" >
                    <div class="input-group">
                        <input type="text" class="form-control" style="!important;width: 500px" id="suggestId" onfocus="labelMap(this)" placeholder="输入地址获取坐标，才能在地图上显示" >
                        <span class="input-group-btn">
                               <button class="btn btn-default"  type="button" onclick="searchMap($('#suggestId').val())">确认位置</button>
                        </span>
                    </div>
                    <div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;z-index:999999999; background-color:#FF0004"></div>
                    <p class="margin-top-15">
                         <div id="l-map" style="width:100%; height:500px">
                        </div>
                    </p>
                </div>
            </td>
        </tr>
    </table>
  <div>
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="#">操作日志</a></li>
        </ul>
        <div id="tabadmin" class="tab-pane" role="tabpanel">
            <#list UserAuditHistoryList as CompanyCardHistory>
                <div class="bs-callout bs-callout-danger" id="callout-labels-inline-block"> <code>${(CompanyCardHistory.auditCreateTime?string("yyyy-MM-dd HH:mm:ss"))!""}</code><br>
                ${(CompanyCardHistory.auditName)!""}：${type} ${(CompanyCardHistory.remark)!""} </div>
            </#list>

        </div>
    </div>
</div>
</div>
<script src="http://webapi.amap.com/maps?v=1.4.6&key=c15ddd974c43598c11d8b86ef67cc518&plugin=AMap.Autocomplete"></script>
<script type="text/javascript">
    var  businessLongitude;
    var businessLatitude;
    $(function () {
        //定位，载入地图
        var Longitude =${userInfoBean.businessAddressLongitude};
        var Latitude =${userInfoBean.businessAddressLatitude};
        var point =  new AMap.LngLat(Longitude, Latitude);
        if(Longitude=="" || Longitude ==0 ){
            //默认上海徐汇创新大楼
            point =  new AMap.LngLat(121.4009, 31.170783);
            businessLongitude=121.4009;
            businessLatitude = 31.170783;
        }
        locationBusiness(point);


        //经营地址
        $("#businessAddressCity").citySelect({
            prov: $("#businessAddressCity1").val(),
            city: $("#businessAddressCity2").val(),
            dist: $("#businessAddressCity3").val() == '' ? '1' : $("#businessAddressCity3").val()
        });
    });

    //setCenter改变地图中心点、setZoom改变地图级别
    var map =new AMap.Map("l-map", {
        resizeEnable: true,
        zoom: 8
    });
    map.plugin(["AMap.ToolBar"], function() {
        map.addControl(new AMap.ToolBar());
    });

    // 构造函数，提供地理编码或逆地理编码功能  用于地址描述与坐标间的相互转换
    var goolgeGeoc;
    map.plugin(["AMap.Geocoder"], function() {
        goolgeGeoc = new AMap.Geocoder({
            city: "010", //城市，默认：“全国”
            radius: 1000,
            batch: "false"
        });
    });

    var tmpSearchResult="";
    var mapinum = 0;
    function labelMap(){
        if(mapinum==0){
            labelMapStart();
            i=mapinum
        } else{
            return
        }
    }

    function labelMapStart(){
        $("html,body").animate({scrollTop:($("#maodian").offset().top)},400)
    }

    //获取，省，市，区，详细地址
    function addrDetail(gc, point) {
        goolgeGeoc.getAddress(point, function(status,result) {
            if (status === 'complete' && result.info === 'OK') {
                geocoder_CallBack(result);
            }
        });
    }


    //获取，省，市，区，详细地址
    function geocoder_CallBack(data) {
        var address = data.regeocode.formattedAddress; //返回地址描述
        $("#suggestId").val(address);
        tmpSearchResult =address;
    }

    //智能搜索
    var autoSearch;
    map.plugin('AMap.Autocomplete',function(){//回调函数
        debugger;
        //实例化Autocomplete
        var autoOptions = {
            city: "", //城市，默认全国
            input:"suggestId",//使用联想输入的input的id
            location:map
        };
        autoSearch= new AMap.Autocomplete(autoOptions);
        AMap.event.addListener(autoSearch, "select", function(e){
            //alert("----------智能搜索事件！！---------"+e.poi.name);
            var addr = e.poi.name;
            searchMap(addr);

        });
    });

    function  searchMap(addr) {
        //alert("----------searchMap！！---------");
        if(addr.length<3)return;
        if(tmpSearchResult == addr)return;
        addr = addr.replace(/\s/g,"");
        //地点搜索服务，提供某一特定地区的位置查询服务
        AMap.service(["AMap.PlaceSearch"], function() {
            var placeSearch = new AMap.PlaceSearch({ //构造地点查询类
                pageSize: 5,
                pageIndex: 1,
                city: "010", //城市
                map: map
            });
            placeSearch.search(addr,searcheResult)
        });
    }


    function searcheResult(status, result) {
        if (status === 'complete' && result.info === 'OK') {
            placeSearch_CallBack(result);
        }else{
            alert("无法定位到此地址");
            return;
        }
    }

    //回调函数
    function placeSearch_CallBack(data) {
        var poiArr = data.poiList.pois;
        map.clearMap();
        //添加marker
        var marker = new AMap.Marker({
            map: map,
            position: poiArr[0].location
        });
        map.setZoom(8);
        map.setCenter(marker.getPosition());
        map.add(marker);
        marker.setDraggable(true);
        marker.on("dragend",function(e) {
            var mpoint =  new AMap.LngLat(e.lnglat.getLng(), e.lnglat.getLat());
            //记录经纬度
            businessLongitude=e.lnglat.getLng();
            businessLatitude=e.lnglat.getLat();
            addrDetail(goolgeGeoc,mpoint);
        });
    }

    function locationBusiness(point) {//根据经纬度定位
        debugger;
        map.clearMap();
        map.setCenter(point);
        var marker = new AMap.Marker({
            position: point,
            draggable: true,
            map: map
        });
        marker.on("dragend",function(e) {
            var mpoint =  new AMap.LngLat(e.lnglat.getLng(), e.lnglat.getLat());
            //记录经纬度
            businessLongitude=e.lnglat.getLng();
            businessLatitude=e.lnglat.getLat();
            addrDetail(goolgeGeoc,mpoint);
        });
        goolgeGeoc.getAddress(point, function(status,result) {
            if (status === 'complete' && result.info === 'OK') {
                geocoder_CallBack(result);
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
    function update() {
        var companyName =$("#companyName").val()
        if(companyName ==""){
            bootbox.alert({
                size: 'small',
                message: "请输入企业名称",
            });
            return ;
        }
        var creditCode = $("#creditCode").val();
        if(creditCode ==""){
            bootbox.alert({
                size: 'small',
                message: "请输入信用代码",
            });
            return ;
        }
        var businessPhotoUrl = $("#businessPhotoUrl").val();
        if(businessPhotoUrl ==""){
            bootbox.alert({
                size: 'small',
                message: "请上传营业执照",
            });
            return ;
        }

        var bt = $('#submit').button('loading');
        var paras = {
            id: $("#userId").val(),
            companyName:companyName ,
            username: $("#username").val(),
            mobile: $("#mobile").val(),
            creditCode: creditCode,
            identityType: $("#identityType").val(),
            businessPhotoUrl: businessPhotoUrl,
            shopFrontPhotoUrl: $("#shopFrontPhotoUrl").val(),
            businessAddressCity1: $("#businessAddressCity_1").val(),
            businessAddressCity2: $("#businessAddressCity_2").val(),
            businessAddressCity3: $("#businessAddressCity_3").val(),
            telephone: $("#telephone").val(),
            internalRemark: $("#internalRemark").val(),
            businessAddress: $("#suggestId").val(),
            businessAddressLongitude: businessLongitude,
            businessAddressLatitude: businessLatitude,
            companyStatus:$("#companyStatus").val()
        };

        var url = getRootPath() + '/companyAudit/updateDetail.do';

        $.ajaxDefaultCall(url, paras, function () {
            window.history.back(-1);
        });
      }
    ;
</script>
<#include "../main/footer.ftl">
