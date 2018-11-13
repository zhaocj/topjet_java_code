hojo.provide("icallcenter.callProcessor");
hojo.require("hojo.io.script");

var newWindow;
var remarkWindow;
var xmlHttp;
var timer;//定时器

hojo.declare("icallcenter.callProcessor", null, {
    _phone: null,

    constructor: function (phone) {
        this._phone = phone;
        var evtHandle = this._phone.register("EvtRing", this, "onRing");
        this._phone._handles.push(evtHandle);
        var evtHandle = this._phone.register("EvtHangup", this, "onHangup");
        this._phone._handles.push(evtHandle);
        evtHandle = this._phone.register("EvtDialing", this, "onDialing");
        this._phone._handles.push(evtHandle);
        evtHandle = this._phone.register("EvtPeerStatusChanged", this, "peerStatusChanged");
        this._phone._handles.push(evtHandle);
        evtHandle = this._phone.register("EvtCallStatusChanged", this, "callStatusChanged");
        this._phone._handles.push(evtHandle);
        evtHandle = this._phone.register("EvtConnected", this, "EvtConnected");
        this._phone._handles.push(evtHandle);
        evtHandle = this._phone.register("EvtLogon", this, "EvtLogon");
        this._phone._handles.push(evtHandle);
        evtHandle = this._phone.register("EvtMonitorQueue", this, "EvtMonitorQueue");
        this._phone._handles.push(evtHandle);
    },
    onRing: function (data1) {
        console.info("onRing==================");
        console.dir(data1);
        var callNo = data1.originCallNo;
        hojo.byId("icallcenter.dialout.input").value = callNo;
        var userURL = getRootPath() + '/view/callCenter/bombScreen.html?callNo=' + callNo;

        //打开备注弹框之前 判断 是否有备注弹框存在  若存在  则close
        if (remarkWindow != '' && remarkWindow != undefined && remarkWindow.closed == false ){
            remarkWindow.close();
            remarkWindow='';
            clearInterval(timer);
        }

        if (newWindow != '' && newWindow != undefined && newWindow.closed == false) {
            newWindow.close();
            newWindow='';
        }

        newWindow = window.open(userURL, 'newwindow', 'height=600, width=990, top=200, left=800, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no');
    },


    /**
     * 挂断电话
     * @param data
     */
    onHangup: function (data) {
        console.info("onHangup=============");
        console.log(data);
        var callRemarkURL = getRootPath() + '/view/callCenter/callRemark.html';

        if (newWindow != '' && newWindow != undefined  && newWindow.closed == false) {
            newWindow.close();
            newWindow='';
        }


        //打开备注弹框之前 判断 是否有备注弹框存在  若存在  则close
        if (remarkWindow != '' && remarkWindow != undefined && remarkWindow.closed == false ){
            remarkWindow.close();
            remarkWindow='';
            clearInterval(timer);
        }


        //重新打开一个新的窗口
        remarkWindow = window.open(callRemarkURL, 'remarkwindow', 'height=300, width=400, top=200, left=800, toolbar=no,menubar=no, scrollbars=no, resizable=no,location=no, status=no');

        //定时器 每隔一秒去执行
        timer = setInterval(function () {
            if (remarkWindow == '' || remarkWindow == undefined || remarkWindow.closed == true){
                //检测备注框是否关闭   如果关闭 则调用用法 保存备注以及通话记录
                updateAfterClose(data);
                remarkWindow='';
            }
        }, 1000);
    },

    onDialing: function (data) {//坐席响铃触发
        console.info("onDialing======================");
        console.log(data);
        var callsheetId = data.callSheetId;
        var agent = "";
        var callNo = data.originCallNo;//����
        var calledNo = data.originCalledNo;//����
        var callType = data.callType;
        var status = data.status;
        var ringTime = data.offeringTime;
        var beginTime = "";
        var endTime = "";
        var monitorFilename = "";
        var phoneJson = {
            Command: "Action",
            Action: "Dialing",
            ActionID: "Dialing" + Math.random(),
            CallsheetId: callsheetId,
            CallNo: callNo,
            CalledNo: calledNo,
            CallType: callType,
            RingTime: ringTime,
            Agent: agent,
            Status: status,
            BeginTime: beginTime,
            EndTime: endTime,
            MonitorFilename: monitorFilename
        };
        this.sendAction(phoneJson);
    },

    EvtConnected: function (data) {//接听触发
        console.info("EvtConnected======================")
        var callsheetId = data.callSheetId;
        var agent = "";
        var callNo = data.originCallNo;//����
        var calledNo = data.originCalledNo;//����
        var callType = data.callType;
        var status = data.status;
        var ringTime = data.offeringTime;
        var beginTime = data.beginTime;
        var endTime = "";
        var monitorFilename = "";
        var phoneJson = {
            Command: "Action",
            Action: "Connected",
            ActionID: "Connected" + Math.random(),
            CallsheetId: callsheetId,
            CallNo: callNo,
            CalledNo: calledNo,
            CallType: callType,
            RingTime: ringTime,
            Agent: agent,
            Status: status,
            BeginTime: beginTime,
            EndTime: endTime,
            MonitorFilename: monitorFilename
        };
        this.sendAction(phoneJson);
    },

    EvtLogon: function (data) {
        var status = data;
        var phoneJson = {
            Command: "Action",
            Action: "Logon",
            ActionID: "Logon" + Math.random(),
            Status: status
        };
        this.sendAction(phoneJson);
    },

    peerStatusChanged: function (data) {
//    	var peerStatus = data; 
//	   	var phoneJson = {
//	    		Command: "Action",
//	    		Action: "Peer",
//	    		ActionID: "Peer" + Math.random(),
//	    		Status: peerStatus
//		};
//	   	this.sendAction(phoneJson);
    },

    callStatusChanged: function (data) {
//    	var peerStatus = data; 
//	   	var phoneJson = {
//	    		Command: "Action",
//	    		Action: "Call",
//	    		ActionID: "Call" + Math.random(),
//	    		Status: peerStatus
//		};
//	   	this.sendAction(phoneJson);
    },

    EvtMonitorQueue: function (queueItem) {
        //var isMySelfQueue = false;
        // for(var i in queueItem.members){
        // 	var member = queueItem.members[i];
        // 	if(member == this._phone.sipNo) {
        //������������Լ���
        //          isMySelfQueue = true;
        // 	}
        //  }
        //  if(isMySelfQueue == true) {
        //  	alert(queueItem.queueName);
        //  	alert(queueItem.idleAgentCount);
        //  	alert(queueItem.queueWaitCount);
        //  }
    },

    sendAction: function (json) {
        //hojo.byId("icallcenter.iframe").src="http://localhost:15062/?json=" + hojo.toJson(json) + "&random=" + Math.floor(Math.random()*100000);
    }


});


function updateAfterClose(data) {
    //关闭监控，
     clearInterval(timer);

    var businessType = remarkWindow.document.getElementById("businessType").value;
    var remark = remarkWindow.document.getElementById("callRemark").value;
    var url = getRootPath() + '/callCenter/addCallCenterCallLog.do';
    xmlHttp = new XMLHttpRequest();
    xmlHttp.open("POST", url, true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send('callSheetId=' + data.callSheetId + '&originCallNo=' + data.originCallNo + '&businessType=' + businessType + '&originCalledNo=' + data.originCalledNo
        + '&callType=' + data.callType + '&status=' + data.status + '&ringTime=' + data.ringTime + '&beginTime=' + data.beginTime + '&endTime=' + data.endTime + '&agent=' + data.agent
        + '&queue=' + data.queue + '&queueName=' + data.queueName + '&agentName=' + data.agentName + '&remark=' + remark + '&monitorFilename=' + data.data.MonitorFilename);
    xmlHttp.onreadystatechange = getOkPost;//发送事件后，收到信息了调用函数


    return;
}
function getOkPost() {
    if (xmlHttp.readyState == 1 || xmlHttp.readyState == 2 || xmlHttp.readyState == 3) {
        // 本地提示：加载中/处理中
        console.log("加载中/处理中");

    }
    if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
        var d = xmlHttp.responseText; // 返回值
        // 处理返回值
        // alert(d);
        console.log("处理返回值" + d);
        window.clearInterval(timer)
    }
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