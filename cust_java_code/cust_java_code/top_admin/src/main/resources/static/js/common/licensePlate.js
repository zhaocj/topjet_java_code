var licensePlatelist = [
    {
        "plateone":[
            "","冀", "晋", "辽", "吉", "黑", "苏", "浙", "皖", "闽",
            "赣", "鲁", "豫", "鄂", "湘", "粤", "琼", "川", "贵",
            "云", "陕", "甘", "青", "台", "京", "津", "沪", "渝",
            "桂", "蒙", "藏", "宁", "新", "港", "澳",

        ]
    },
    {
        "platetwo": [
            "","A","B","C","D","E","F","G","H","I","G","K","L","M","N",
            "O","P","Q","R","S","T","U","V","W","X","Y","Z"
        ]
    }
]

var temp_plateone;
var temp_platetwo;

//循环各省车牌号缩写
for(var i=0;i<licensePlatelist[0].plateone.length;i++){
    temp_plateone+="<option value='"+licensePlatelist[0].plateone[i]+"'>"+licensePlatelist[0].plateone[i]+"</option><br/>";
}
//循环字母
for(var j=0;j<licensePlatelist[1].platetwo.length;j++){
    temp_platetwo+="<option value='"+licensePlatelist[1].platetwo[j]+"'>"+licensePlatelist[1].platetwo[j]+"</option><br/>";
}
$("#plateNo1").html(temp_plateone);
$("#plateNo2").html(temp_platetwo);
var plNo = $("#plateNo").val();
if(plNo!=null && plNo!=""){
    var plNo1 = plNo.substring(0, 1);
    var plNo2 = plNo.substring(1, 2);
    var plNo3 = plNo.substring(2, plNo.length);

    $("#plateNo1 option:selected").text(plNo1); //设置默认值
    $("#plateNo2 option:selected").text(plNo2); //设置默认值
    $("#plateNo3").val(plNo3);
}

    