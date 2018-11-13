
var map = new BMap.Map("l-map", {
    enableMapClick: false
});
map.centerAndZoom("上海市徐汇区",12); // 初始化地图,设置城市和地图级别
var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
map.addControl(top_left_navigation);
var gc = new BMap.Geocoder();
var mapinum = 0;
// 显示地图层
function labelMap(){if(mapinum==0){labelMapStart();i=mapinum}else{return}};
function labelMapStart(){$("html,body").animate({scrollTop:($("#maodian").offset().top)},400)};
// 智能搜索
function G(id){return document.getElementById(id)}var ac=new BMap.Autocomplete({"input":"suggestId","location":map});
ac.addEventListener("onhighlight",function(e){var str="";var _value=e.fromitem.value;var value="";if(e.fromitem.index>-1){value=_value.province+_value.city+_value.district+_value.street+_value.business}str="FromItem<br />index = "+e.fromitem.index+"<br />value = "+value;value="";if(e.toitem.index>-1){_value=e.toitem.value;value=_value.province+_value.city+_value.district+_value.street+_value.business}str+="<br />ToItem<br />index = "+e.toitem.index+"<br />value = "+value;G("searchResultPanel").innerHTML=str});
var myValue;
ac.addEventListener("onconfirm",function(e){var _value=e.item.value;myValue=_value.province+_value.city+_value.district+_value.street+_value.business;G("searchResultPanel").innerHTML="onconfirm<br />index = "+e.item.index+"<br />myValue = "+myValue;setPlace()});
function setPlace() {
    map.clearOverlays(); //清除地图上所有覆盖物
    function myFun(rs) {
        var pp = local.getResults().getPoi(0).point;
        //alert("坐标:"+pp.lng + "," + pp.lat);
        breakinput(gc, pp);
        map.centerAndZoom(pp, 17);
        var marker = new BMap.Marker(pp);
        map.addOverlay(marker);
        marker.enableDragging();
        marker.addEventListener("dragend",
            function(e) {
              //  alert("坐标:"+e.point.lng + ", " + e.point.lat);
                breakinput(gc, e.point);
            })
    }
    var local = new BMap.LocalSearch(map, {onSearchComplete: myFun});
    local.search(myValue);
}
//获取，省，市，区，详细地址
function breakinput(gc, point) {
    gc.getLocation(point,
        function(rs) {
            var addComp = rs.addressComponents;
            var provinceName=addComp.province;
            var cityName=addComp.city;
            var districtName=addComp.district;
            //alert("省："+provinceName+" 市:"+cityName+" 区:"+districtName);
            $("#longitude").val(rs.point.lng);
            $("#latitude").val(rs.point.lat);
            $("#provinceName").val(provinceName);
            $("#cityName").val(cityName);
            $("#address").val(rs.address);
            $("#suggestId").val(addComp.city+' '+addComp.district+' '+addComp.street + ' '+addComp.streetNumber);
        });
}