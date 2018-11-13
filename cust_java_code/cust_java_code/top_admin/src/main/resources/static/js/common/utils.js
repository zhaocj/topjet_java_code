function strTrim(str) {
    if (isNull(str)) {
        return "";
    }
    return trim(str);
}

function jsonFormart() {
	var paramlength = arguments.length;
	if(paramlength>0){
		var resultJson = $.parseJSON(JSON.stringify($(arguments[0]).serializeObject()));
		for(var i =1; i<paramlength;i++){
			resultJson = $.extend(resultJson,arguments[i]);
		}
		return resultJson;
	}
}

function trim(str) {
    str = str.replace(/^\s+|\s+$/g, "");
    return str;
}

function isNull(str) {
    if (str == "" || str == null || str == "undefined" || str == "null" || str.length == 0) return true;
    var regu = "^[ ]+$";
    var re = new RegExp(regu);
    return re.test(str);
}
String.prototype.endWith=function(endStr){
	var d=this.length-endStr.length;
	return (d>=0&&this.lastIndexOf(endStr)==d)
}

function getDate() {
    var now = new Date();
    return now.getFullYear() + "-" + ((now.getMonth() + 1) < 10 ? "0" : "") + (now.getMonth() + 1) + "-" + (now.getDate() < 10 ? "0" : "") + now.getDate();

    /*	var myDate = new Date();
     var dateStr=	myDate.toLocaleDateString().replace("/", "-");
     dateStr=dateStr.replace("/", "-");
     return dateStr;*/
}
/*获得大于当前日期2天的时间*/
function getDate1() {
    var now = new Date();
    now.setDate(now.getDate() + 2);
    return now.getFullYear() + "-" + ((now.getMonth() + 1) < 10 ? "0" : "") + (now.getMonth() + 1) + "-" + (now.getDate() < 10 ? "0" : "") + now.getDate();
}
/*获得小于当前日期2天的时间*/
function getDate2() {
	var now = new Date();
	now.setDate(now.getDate() - 2);
	return now.getFullYear() + "-" + ((now.getMonth() + 1) < 10 ? "0" : "") + (now.getMonth() + 1) + "-" + (now.getDate() < 10 ? "0" : "") + now.getDate();
}
/*获得小于当前日期7天的时间*/
function getDate7() {
	var now = new Date();
	now.setDate(now.getDate() - 7);
	return now.getFullYear() + "-" + ((now.getMonth() + 1) < 10 ? "0" : "") + (now.getMonth() + 1) + "-" + (now.getDate() < 10 ? "0" : "") + now.getDate();
}

/*获得大于当前日期10天的时间*/
function getDate10() {
    var now = new Date();
    now.setDate(now.getDate() + 10);
    return now.getFullYear() + "-" + ((now.getMonth() + 1) < 10 ? "0" : "") + (now.getMonth() + 1) + "-" + (now.getDate() < 10 ? "0" : "") + now.getDate();
}

function formatDates(value,format){
	if(isNull(value)){
		return "";
	}
	var date=new Date(value);
	  var paddNum = function(num){
	    num += "";
	    return num.replace(/^(\d)$/,"0$1");
	  }
	  //指定格式字符
	  var cfg = {
	     yyyy : date.getFullYear() //年 : 4位
	    ,yy : date.getFullYear().toString().substring(2)//年 : 2位
	    ,M  : date.getMonth() + 1  //月 : 如果1位的时候不补0
	    ,MM : paddNum(date.getMonth() + 1) //月 : 如果1位的时候补0
	    ,d  : date.getDate()   //日 : 如果1位的时候不补0
	    ,dd : paddNum(date.getDate())//日 : 如果1位的时候补0
	    ,hh : date.getHours()  //时
	    ,mm : date.getMinutes() //分
	    ,ss : date.getSeconds() //秒
	  }
	  format || (format = "yyyy-MM-dd hh:mm:ss");
	  return format.replace(/([a-z])(\1)*/ig,function(m){return cfg[m];});
	} 

function getRootPath() {
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName);
}

/**
 * 字符串date格式化
 * @param str
 * @returns {Date}
 */
function StringToDate(str){
	str = str.replace(/-/g,"/");
	var strDate = new Date(str );
	return strDate;
}
/**
 * 表单数据是否有改动
 * @param str
 * @returns {ture or false}
 */
function IsFormChangeTrue(id){
	var dateObject = $("#"+id).serializeObject();
	var dateArray = $("#"+id).serializeArray();
	var isChangeFlag = false;
	var thisName = "";
	$.each(dateArray,function(){
		thisName = this.name + "Older";
		if(this.name.indexOf("Older")<0){
			if(this.value != dateObject[thisName]){
				isChangeFlag = true;
				return ;
			}
		}
	});
	return isChangeFlag;
}

/**
 * 将表单数据转变成object对象
 * @returns {Object}
 */
$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
}
function setCookie(c_name,value,expiredays)
{
	var exdate=new Date()
	exdate.setDate(exdate.getDate()+expiredays)
	document.cookie=c_name+ "=" +escape(value)+
		((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
}


function getCookie(c_name)
{
	if (document.cookie.length>0)
	{
		var c_start=document.cookie.indexOf(c_name + "=")
		if (c_start!=-1)
		{
			c_start=c_start + c_name.length+1
			var c_end=document.cookie.indexOf(";",c_start)
			if (c_end==-1) c_end=document.cookie.length
			return unescape(document.cookie.substring(c_start,c_end))
		}
	}
	return ""
}

function removeCookie(name){
	/* -1 天后过期即删除 */
	setCookie(name, 1, -1);
}

//自适应高度方法
function auto() {
	$("#contentmenu").css("height", window_h = $(window).height() - 53);
	if ($(".iframeId").length > 0) {
		$(".iframeId").css("height", window_h = $(window).height() - 98);
	}
}
$(window).resize(function() {
	auto()
});

//用户详情
function modifiedInfo(id,title,reditRemark) {
	window.location.href = getRootPath() + '/memberAction/memberDetail.do?id=' + id+'&title='+title+'&reditRemark='+reditRemark;
	/*var url = getRootPath() + '/memberAction/memberDetail.do';
	var json = {
		id:id,
		title:title
	}
	$.ajaxDefaultCall(url,json);*/
};
//订单详情
function getCommentDetails(id,title) {
	window.location.href = getRootPath() + '/order/detail.do?id=' + id+'&title='+title;
}
//货源详情
function getCommentGoodsDetails(id,title) {
    window.location.href = getRootPath() + '/goods/goodsdetail.do?id=' + id+'&title='+title;
}
//车辆详情
function getTruckDetails(id,title) {
	window.location.href = getRootPath() + '/truck/edit.do?id=' + id+'&title='+title;
}

//用户订单列表
function getOrderList(mobile) {
    window.location.href = getRootPath() + '/view/orderInfo/userOrderList.html?mobile=' + mobile;
}

//BASE64编码解码
var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
var base64DecodeChars = new Array(
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
    52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
    -1,  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14,
    15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
    -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
    41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1);

function base64encode(str) {
    var out, i, len;
    var c1, c2, c3;

    len = str.length;
    i = 0;
    out = "";
    while(i < len) {
        c1 = str.charCodeAt(i++) & 0xff;
        if(i == len)
        {
            out += base64EncodeChars.charAt(c1 >> 2);
            out += base64EncodeChars.charAt((c1 & 0x3) << 4);
            out += "==";
            break;
        }
        c2 = str.charCodeAt(i++);
        if(i == len)
        {
            out += base64EncodeChars.charAt(c1 >> 2);
            out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
            out += base64EncodeChars.charAt((c2 & 0xF) << 2);
            out += "=";
            break;
        }
        c3 = str.charCodeAt(i++);
        out += base64EncodeChars.charAt(c1 >> 2);
        out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
        out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >>6));
        out += base64EncodeChars.charAt(c3 & 0x3F);
    }
    return out;
}

function base64decode(str) {
    var c1, c2, c3, c4;
    var i, len, out;

    len = str.length;
    i = 0;
    out = "";
    while(i < len) {
		/* c1 */
        do {
            c1 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
        } while(i < len && c1 == -1);
        if(c1 == -1)
            break;

		/* c2 */
        do {
            c2 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
        } while(i < len && c2 == -1);
        if(c2 == -1)
            break;

        out += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));

		/* c3 */
        do {
            c3 = str.charCodeAt(i++) & 0xff;
            if(c3 == 61)
                return out;
            c3 = base64DecodeChars[c3];
        } while(i < len && c3 == -1);
        if(c3 == -1)
            break;

        out += String.fromCharCode(((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));

		/* c4 */
        do {
            c4 = str.charCodeAt(i++) & 0xff;
            if(c4 == 61)
                return out;
            c4 = base64DecodeChars[c4];
        } while(i < len && c4 == -1);
        if(c4 == -1)
            break;
        out += String.fromCharCode(((c3 & 0x03) << 6) | c4);
    }
    return out;
}
