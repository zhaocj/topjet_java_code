﻿hojo.provide("icallcenter.logon");

icallcenter.logon.startLogon = function (loginName, password, extenType) {
  // 呼叫中心生产配置  对应测试账号是8016@shtj
    var config = {
        Monitor: true,
        proxy_url: "http://61.152.242.50",
        extenType: extenType,
        password: password,
        User: loginName,
		busyType: "0"
    };

  // 呼叫中心测试配置  对应测试账号是8001@shtj1
  /*  var config = {
        Monitor: true,
        proxy_url: "http://121.43.153.58",
        extenType: extenType,
        password: password,
        User: loginName,
        busyType: "0"
    };*/
	icallcenter.logon.initPhone(config);
};

//init softphone
icallcenter.logon.initPhone = function (config) {
    hojo.require("icallcenter.Phone");
    icallcenter.Phone.registerEvent(config);
};

icallcenter.logon.afterPhone = function (phone) {
    if (phone) {
        hojo.require("icallcenter.SoftphoneBar");
        softphoneBar = new icallcenter.SoftphoneBar(phone, "softphonebar");
        hojo.require("icallcenter.callProcessor");
        callProcessor = new icallcenter.callProcessor(phone);
        console.dir(callProcessor)
    }
};

icallcenter.logon.getUrlValue = function (param) {
    var query = window.location.search;
    var iLen = param.length;
    var iStart = query.indexOf(param);
    if (iStart == -1)
        return "";
    iStart += iLen + 1;
    var iEnd = query.indexOf("&", iStart);
    if (iEnd == -1)
        return query.substring(iStart);
    return query.substring(iStart, iEnd);
};


//登出
icallcenter.logon.logout = function(){
   phone.destroy(true);
};