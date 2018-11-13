var truckTypeJson = [];
var truckLengthJson = [];
var truckTypeOption ="";
var truckLengthOption ="";
$(function () {
    $.ajax({
        type:"POST",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        url:getRootPath()+'/commonAction/getTruckData.do',
        data:[],
        dataType:"json",
        cache:false,
        traditional:true,
        success:function(list){
            if(list != null && list.length>0){
                $.each(list[0],function (index,data2) {
                    truckTypeJson.push({'id':data2.id,'code':data2.code,'displayName':data2.displayName});
                });
                $.each(list[1],function (index,data1) {
                    truckLengthJson.push({'id':data1.id,'length':data1.length,'displayName':data1.displayName});
                });

                $.each(truckTypeJson,function (indx,length1) {
                    truckTypeOption+="<option value='"+length1['id']+"'>"+length1['displayName']+"</option>";
                });
                $.each(truckLengthJson,function (indx,length) {
                    truckLengthOption+="<option value='"+length['id']+"'>"+length['displayName']+"</option>";

                });

            }
            $('#TruckTypedisplayName').append(truckTypeOption);
            $('#truckLength').append(truckLengthOption);
        }
    });
})