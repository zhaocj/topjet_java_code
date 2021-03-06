hojo.provide("icallcenter.SoftphoneBar");
hojo.require("icallcenter.Phone");
hojo.require("icallcenter.hojotools");
hojo.declare("icallcenter.SoftphoneBar", null, {
    constructor: function (a, b) {
        this._phone = a;
        this._srcNodeRef = hojo.byId(b);
        this._srcNodePeerStateRef = hojo.byId(b + ".peerState");
        this._srcNodePeerTimeStateRef = hojo.byId(b + ".peerTimeState");
        this._srcNodeCallTimeStateRef = hojo.byId(b + ".callTimeState");
        this._srcNodeSelectStateRef = hojo.byId(b + ".select");
        this._srcNodeSelectMenuRef = hojo.byId(b + ".selectMenu");
        this._srcNodePhoneNumRef = hojo.byId(b + ".softPhoneNum");
        this._callStateDescription["stInnerDialing"] = "\u547c\u53eb\u4e2d";
        this._callStateDescription["stInnerTalking"] = "\u5185\u90e8\u901a\u8bdd";
        this._callStateDescription["stInvalid"] = "\u7a7a\u95f2";
        this._callStateDescription["stAbate"] = "\u5931\u6548";
        this._callStateDescription["stBelling"] = "\u6765\u7535\u632f\u94c3";
        this._callStateDescription["stTalking"] = "\u666e\u901a\u901a\u8bdd";
        this._callStateDescription["stListening"] = "\u76d1\u542c\u632f\u94c3";
        this._callStateDescription["stListened"] = "\u76d1\u542c\u4e2d";
        this._callStateDescription["stDialing"] = "\u547c\u53eb\u4e2d";
        this._callStateDescription["stDialTalking"] = "\u5916\u547c\u901a\u8bdd";
        this._callStateDescription["stHold"] = "\u4fdd\u6301";
        this._callStateDescription["stInnerBelling"] = "\u6765\u7535\u632f\u94c3";
        this._callStateDescription["stThreeWayTalking"] = "\u4e09\u65b9\u901a\u8bdd";
        this._callStateColor["0"] = "#41b754";
        this._callStateColor["1"] = "#ff6400";
        this._callStateColor["2"] = "#ff6400";
        this._callStateColor["99"] = "#ff6400";
        this._callStateColor["call"] = "#FF33FF";
        this._phone.register("EvtPeerToolBarChange", this, "onPeerToolBarStateChanged");
        this._phone.register("EvtCallToolBarChange", this, "onCallToolBarStateChanged");
        this._phone.register("ivrMenuTransfering", this, "_softphonebar_ivrMenuTransfering");
        this._phone.register("transfering", this, "_softphonebar_transfering");
        this._phone.register("EvtBarChange", this, "_render")
    },
    _callStateColor: [],
    _phone: null,
    _srcNodeRef: null,
    _srcNodePeerStateRef: null,
    _srcNodePeerTimeStateRef: null,
    _srcNodeCallTimeStateRef: null,
    _srcNodeSelectStateRef: null,
    _srcNodeSelectMenuRef: null,
    _srcNodePhoneNumRef: null,
    _callStateDescription: [],
    _peerCalculagraph: null,
    _peerSecond: "1",
    _peerMinute: "0",
    _peerHour: "0",
    sip_stInvalid: ["DialEnable", "HangupDisable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable", "IdleEnable", "RestDisable", "BusyDisable"],
    sip_stBusy: ["DialEnable", "HangupDisable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable", "IdleDisable", "RestDisable", "BusyEnable"],
    sip_stRest: ["DialEnable", "HangupDisable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable", "IdleDisable", "RestEnable", "BusyDisable"],
    sip_stDialing: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    sip_stDialTalking: ["DialDisable", "HangupEnable", "HoldEnable", "ThreeWayCallDisable", "TransferEnable", "ConsultEnable"],
    sip_stHold: ["DialDisable", "HangupDisable", "HoldGetEnable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    sip_stAbate: ["DialDisable", "HangupDisable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    sip_stInnerDialing: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    sip_stInnerTalking: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    sip_stBelling: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    sip_stTalking: ["DialDisable", "HangupEnable", "HoldEnable", "ThreeWayCallDisable", "TransferEnable", "ConsultEnable"],
    sip_stListening: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    sip_stListened: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    sip_stInnerBelling: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    sip_stThreeWayTalking: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    sip_stConcultTalking: ["DialDisable", "HangupEnable", "ConsultThreeWayCallEnable", "ConsultTransferEnable", "StopConsultEnable"],
    sip_stSystemBusy: ["DialEnable", "HangupDisable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable", "IdleDisable", "RestDisable", "BusyEnable"],
    gateway_stInvalid: ["DialEnable", "HangupDisable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable", "IdleEnable", "RestDisable", "BusyDisable"],
    gateway_stBusy: ["DialEnable", "HangupDisable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable", "IdleDisable", "RestDisable", "BusyEnable"],
    gateway_stRest: ["DialEnable", "HangupDisable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable", "IdleDisable", "RestEnable", "BusyDisable"],
    gateway_stDialing: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    gateway_stDialTalking: ["DialDisable", "HangupEnable", "HoldEnable", "ThreeWayCallDisable", "TransferEnable", "ConsultEnable"],
    gateway_stHold: ["DialDisable", "HangupDisable", "HoldGetEnable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    gateway_stAbate: ["DialDisable", "HangupDisable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    gateway_stInnerDialing: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    gateway_stInnerTalking: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    gateway_stBelling: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    gateway_stTalking: ["DialDisable", "HangupEnable", "HoldEnable", "ThreeWayCallDisable", "TransferEnable", "ConsultEnable"],
    gateway_stListening: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    gateway_stListened: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    gateway_stInnerBelling: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    gateway_stThreeWayTalking: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    gateway_stConcultTalking: ["DialDisable", "HangupEnable", "ConsultThreeWayCallEnable", "ConsultTransferEnable", "StopConsultEnable"],
    gateway_stSystemBusy: ["DialEnable", "HangupDisable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable", "IdleDisable", "RestDisable", "BusyEnable"],
    Local_stInvalid: ["DialEnable", "HangupDisable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable", "IdleEnable", "RestDisable", "BusyDisable"],
    Local_stBusy: ["DialEnable", "HangupDisable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable", "IdleDisable", "RestDisable", "BusyEnable"],
    Local_stRest: ["DialEnable", "HangupDisable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable", "IdleDisable", "RestEnable", "BusyDisable"],
    Local_stDialing: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    Local_stDialTalking: ["DialDisable", "HangupEnable", "HoldEnable", "ThreeWayCallDisable", "TransferEnable", "ConsultEnable"],
    Local_stHold: ["DialDisable", "HangupDisable", "HoldGetEnable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    Local_stAbate: ["DialDisable", "HangupDisable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    Local_stInnerDialing: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    Local_stInnerTalking: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    Local_stBelling: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    Local_stTalking: ["DialDisable", "HangupEnable", "HoldEnable", "ThreeWayCallDisable", "TransferEnable", "ConsultEnable"],
    Local_stListening: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    Local_stListened: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    Local_stInnerBelling: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    Local_stThreeWayTalking: ["DialDisable", "HangupEnable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable"],
    Local_stConcultTalking: ["DialDisable", "HangupEnable", "ConsultThreeWayCallEnable", "ConsultTransferEnable", "StopConsultEnable"],
    Local_stSystemBusy: ["DialEnable", "HangupDisable", "HoldDisable", "ThreeWayCallDisable", "TransferDisable", "ConsultDisable", "IdleDisable", "RestDisable", "BusyEnable"],
    onPeerToolBarStateChanged: function (c) {
        if (this._phone._peerState._curPeerStateKey == "99") {
            if (this._phone.autoBusyTime != "0") {
                if (this._phone.autoBusyTime == undefined) {
                    return
                }
                ;
                var d = this._phone._peerState._callStateValue[this._phone._peerState._curPeerStateKey];
                this.onChangeState(d);
                this.onAutoBusyTime(this._phone.autoBusyTime);
                return
            }
        }
        ;
        var d = this._phone._peerState._callStateValue[this._phone._peerState._curPeerStateKey];
        this.onChangeState(d);
        if (this._srcNodePeerStateRef != null) {
            this._srcNodePeerStateRef.innerHTML = "<span style=color:" + this._callStateColor[this._phone._peerState._curPeerStateKey] + ">" + this._phone._peerState._get(c).value + "</span>";
            if (c != "99") {
                hojo.publish("EvtPeerStatusChanged", [c])
            }
        }
        ;
        if (this._srcNodePeerTimeStateRef != null) {
            this._showTimer()
        }
    },
    onChangeState: function (d) {
        var g = "sip";
        var f = this._phone.extenType;
        if (f == "gateway" || f == "phone") {
            g = "gateway"
        } else {
            if (f == "Local") {
                g = "Local"
            }
        }
        ;
        var e = g + "_" + d;
        this._render(e)
    },
    onCallToolBarStateChanged: function (d) {
        this.onChangeState(d);
        if (this._srcNodePeerStateRef != null && d != "stInvalid") {
            this._srcNodePeerStateRef.innerHTML = "<span style=color:" + this._callStateColor["call"] + ">" + this._callStateDescription[d] + "</span>";
            hojo.publish("EvtCallStatusChanged", [d])
        }
        ;
        if (this._srcNodePeerTimeStateRef != null) {
            this._showTimer()
        }
        ;
        var h = hojo.byId("peerStatus");
        if (h == null) {
            return
        }
        ;
        if (d == "stInvalid") {
            hojo.byId("icallcenter.dialout.input").disabled = false;
            this.onPeerToolBarStateChanged(this._phone._peerState._curPeerStateKey)
        } else {
            hojo.byId("icallcenter.dialout.input").disabled = true
        }
    },
    _showTimer: function () {
        var i = this;
        i._peerSecond = "1";
        i._peerMinute = "0";
        i._peerHour = "0";
        if (i._peerCalculagraph != null) {
            window.clearInterval(i._peerCalculagraph)
        }
        ;
        i._peerCalculagraph = window.setInterval(function () {
            i._srcNodePeerTimeStateRef.innerHTML = ((i._peerHour < 10) ? ("0" + i._peerHour) : i._peerHour) + ":" + ((i._peerMinute < 10) ? ("0" + i._peerMinute) : i._peerMinute) + ":" + ((i._peerSecond < 10) ? ("0" + i._peerSecond) : i._peerSecond);
            i._peerSecond++;
            if (i._peerSecond == 60) {
                i._peerMinute++;
                i._peerSecond = 0
            }
            ;
            if (i._peerMinute == 60) {
                i._peerHour++;
                i._peerMinute = 0
            }
        }, 1000)
    },
    _render: function (d) {
        var i = this;
        hojo.query("a", this._srcNodeRef).forEach(function (j) {
            if (hojo.indexOf(i[d], j.id) < 0) {
                j.style.display = "none"
            } else {
                j.style.display = ""
            }
        })
    },
    onAutoBusyTime: function (k) {
        if (this._srcNodePeerStateRef != null) {
            this._srcNodePeerStateRef.innerHTML = this._phone._peerState._get("99").value
        }
        ;
        var i = this;
        if (i._peerCalculagraph != null) {
            window.clearInterval(i._peerCalculagraph)
        }
        ;
        i._peerCalculagraph = window.setInterval(function () {
            if (k >= 60 * 60) {
                i._peerHour = parseInt(k / (60 * 60));
                i._peerMinute = parseInt((k - i._peerHour * (60 * 60)) / (60));
                i._peerSecond = k - i._peerHour * (60 * 60) - i._peerMinute * (60)
            } else {
                if (k >= 60 && (k < 60 * 60)) {
                    i._peerHour = "0";
                    i._peerMinute = parseInt(k / 60);
                    i._peerSecond = k - i._peerMinute * 60
                } else {
                    if (k < 60) {
                        i._peerHour = "0";
                        i._peerMinute = "0";
                        i._peerSecond = k
                    }
                }
            }
            ;
            if (i._peerHour < 0) {
                i._peerHour = 0
            }
            ;
            if (i._peerMinute < 0) {
                i._peerMinute = 0
            }
            ;
            if (i._peerSecond < 0) {
                i._peerSecond = 0
            }
            ;
            i._srcNodePeerTimeStateRef.innerHTML = ((i._peerHour < 10) ? ("0" + i._peerHour) : i._peerHour) + ":" + ((i._peerMinute < 10) ? ("0" + i._peerMinute) : i._peerMinute) + ":" + ((i._peerSecond < 10) ? ("0" + i._peerSecond) : i._peerSecond);
            k--
        }, 1000)
    },
    _softphonebar_ivrMenuTransfering: function (l) {
        console.log(l);
        if (l.Event == "IvrMenuEnd") {
            if (l.VAL_OF_IVR_MENU) {
                var m = l.VAL_OF_IVR_MENU;
                if (m == "DTSF") {
                    m = "\u8f6c\u7b2c\u4e09\u65b9\u7cfb\u7edf\u5931\u8d25\uff01";
                    console.log(m);
                    icallcenter.hojotools.showSucc(m)
                } else {
                    console.log(m);
                    icallcenter.hojotools.showSucc(m)
                }
            }
        }
    },
    _softphonebar_transfering: function (l) {
        icallcenter.hojotools.close("softphonebar");
        if (l.Event == "TransferSuccess") {//转接成功
            icallcenter.hojotools.showSucc("\u8f6c\u63a5\u6210\u529f")
        } else {
            if (l.Event == "TransferFailed") {//转接失败
                icallcenter.hojotools.error("\u8f6c\u63a5\u5931\u8d25")
            }
        }
    },
    dialout: function (n) {
        if (/^\d+$/.test(n)) {
            this._phone.dialout(n);
            return true
        } else {
            icallcenter.hojotools.error("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u7535\u8bdd\u53f7\u7801");
            return false
        }
    },
    toTransfer: function () {
        icallcenter.hojotools.input("transfer")
    },
    toConsult: function () {
        icallcenter.hojotools.input("consult")
    },
    exTransfer: function (n) {

        if (/^\d+$/.test(n)) {
            icallcenter.hojotools.close("softphonebar");
            phone.transfer("9" + n, "external", {});
            if (n.length <= 5) {
                n = n.substr(1);
                icallcenter.hojotools.softphonebar_showTranster("\u5de5\u53f7 " + n + " ")
            } else {
                icallcenter.hojotools.softphonebar_showTranster(n + " ")
            }
        } else {

            icallcenter.hojotools.error("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u7535\u8bdd\u53f7\u7801")
        }
    },
    exConsult: function (n) {
        if (/^\d+$/.test(n)) {
            icallcenter.hojotools.close("softphonebar");
            phone.consult("9" + n, "external")
        } else {
            icallcenter.hojotools.error("\u8bf7\u8f93\u5165\u6b63\u786e\u7684\u7535\u8bdd\u53f7\u7801")
        }
    }
})