$("#level").change(function(){
	$("#adcode").val("");
	$("#province").empty();
	$("#city").empty();
	var value = $("#level").val();
	if(value==2){
		$("#span").hide();
		$.ajax({
			type : 'post',
			url : getRootPath()+'/cityAction/getProvince.do?level='+1,
			success : function(data) {
				console.log(data);
				var option = "";
				  option += "<option value=" + "" + ">" +'全国' + "</option>";
				$.each(data, function(i, n) {
				    option += "<option value=" + n.adcode + ">" + n.cityName + "</option>";
				});
				$("#province").append(option); //等同于 $(option).appendTo($("#catagery"));
	}
		});
		$("#province").change(function(){
			$("#adcode").val($('#province').val().substr(0,2));
		});	
		
	}
	if(value==3){
		$("#span").show();
		
		$.ajax({
			type : 'post',
			url : getRootPath()+'/cityAction/getProvince.do?level='+1,
			success : function(data) {
				console.log(data);
				var option = "";
				 option += "<option value=" + ""+ ">" +'全国' + "</option>";
				$.each(data, function(i, n) {
				    option += "<option value=" + n.adcode + ">" + n.cityName + "</option>";
				});
				$("#province").append(option); //等同于 $(option).appendTo($("#catagery"));
	}
		
		});
		$("#province").change(function(){
			$("#city").attr("disabled",false);
			$("#city").attr("required",true);
			var adcode=$("#province").val();
			$.ajax({
				type : 'post',
				url : getRootPath()+'/cityAction/getCity.do?adcode='+adcode,
				success : function(data) {
					$("#city").empty();
					console.log(data);
					var cityoption = "";
					 cityoption += "<option value=" + ""+ ">" +'请选择' + "</option>";
					$.each(data, function(i, n) {
						cityoption += "<option value=" + n.adcode + ">" + n.cityName + "</option>";
					});
					$("#city").append(cityoption); //等同于 $(option).appendTo($("#catagery"));
		}
			
			});
		});
		$("#city").change(function(){
			$("#adcode").val($('#city').val().substr(0,4));
		});
	}
	else{
		$("#province").empty();
		$("#city").empty();
	}
});

function submitCreateClick() {
	var bt=$('#myButton').button('loading');
		if(!$.topjectIsValidate('myForm')){
			bt.button('reset');
			return;
		}
	if($("#baiduCityId").val() == ""){
		bootbox.alert({
			size: 'small',
			message: "请填写地图代码!"
		});
		bt.button('reset');
		return;
	}
	if($("#adcode").val().length <= 2){
		bootbox.alert({
			size: 'small',
			message: "城市编号不能少于两位!"
		});
		bt.button('reset');
		return;
	}
	var json = {
		'cityName':$("#cityName").val(),
		'level': $("#level").val(),
		'province': $("#province").val(),
		'city': $("#city").val(),
		'adcode': $("#adcode").val(),
		'flag': $("#flag").val(),
		'citycode': $("#baiduCityId").val(),
		'latitude': $("#latitude").val(),
		'longitude': $("#longitude").val(),
		'zip': $("#zip").val()

	};
	  var url =getRootPath()+'/cityAction/create.do';
	  window.parent.$.ajaxDefaultCall(url, json,function(){$("#myModal").modal("hide");
		  window.location.href =  getRootPath()+'/view/city/list.html';
		  tableRefresh();});
}







