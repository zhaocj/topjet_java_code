﻿hojo.provide("icallcenter.Phone");
hojo.require("hojo.io.script");
hojo.require("icallcenter.hojotools");
hojo.require("icallcenter.stateElement.base");
hojo.require("icallcenter.stateElement.hold");
hojo.require("icallcenter.stateElement.invalid");
hojo.require("icallcenter.stateElement.abate");
hojo.require("icallcenter.stateElement.peerState");
hojo.require("icallcenter.stateElement.link.consultationLink");
hojo.require("icallcenter.stateElement.link.innerLink");
hojo.require("icallcenter.stateElement.link.dialoutLink");
hojo.require("icallcenter.stateElement.link.listenLink");
hojo.require("icallcenter.stateElement.link.normalLink");
hojo.require("icallcenter.stateElement.link.threeWayCallLink");
hojo.require("icallcenter.stateElement.ring.innerRing");
hojo.require("icallcenter.stateElement.ring.normalRing");
hojo.require("icallcenter.stateElement.ring.listenRing");
hojo.require("icallcenter.stateElement.ringring.consultationRinging");
hojo.require("icallcenter.stateElement.ringring.innerRinging");
hojo.require("icallcenter.stateElement.ringring.normalRinging");
hojo.declare("icallcenter.Phone", null, {
    _base: null,
    _peerState: null,
    _invalid: null,
    _abate: null,
    _hold: null,
    _consultationLink: null,
    _innerLink: null,
    _dialoutLink: null,
    _listenLink: null,
    _normalLink: null,
    _threeWayCallLink: null,
    _innerRing: null,
    _normalRing: null,
    _listenRing: null,
    _consultationRinging: null,
    _innerRinging: null,
    _normalRinging: null,
    monitorPeers: [],
    monitorQueues: [],
    monitorServiceNos: [],
    accountCalls: null,
    _isWaitingEvent: false,
    _destroyed: false,
    callObject: {},
    _curChannel: null,
    _otherChannel: null,
    _callId: null,
    iccCount: 0,
    dialoutData: null,
    _isInvestigatting: false,
    _isLooting: false,
    _isCallingOut: false,
    _isSettingbusy: false,
    _isRing: false,
    _handles: [],
    constructor: function (a) {
        var b = this.register("EvtAutoBusyTime", this, "onAutoBusyTimeChanged");
        this._handles.push(b);
        this._loadConfig(a);
        this._evtXhr = hojo._xhrObj();
        this._waitEvent()
    },
    destroy: function (d) {
        if (!this._destroyed) {
            for (var c in this._handles) {
                this.unregister(this._handles[c])
            }
            ;
            this._stopEvent();
            this._exit(this.loginName, d);
            this._destroyed = true
        }
    },
    _loadConfig: function (a) {
        for (var c in a) {
            this[c] = a[c]
        }
    },
    _stopEvent: function () {
        if (this._evtXhr) {
            this._evtXhr.abort();
            this._evtXhr = null
        }
    },
    onRing: function (f, g) {
        this.oldInboundId = this._inboundId;
        console.debug("\u63a7\u4ef6onRing\u8fd4\u56de[callId:%s,remoteId:%s]", f, g);
        this._inboundId = f
    },
    onRemoteRing: function (f, g) {
        console.debug("\u63a7\u4ef6onRemoteRing\u8fd4\u56de[callId:%s,remoteId:%s]", f, g);
        this._outboundId = f
    },
    onCalloutFail: function (f, h) {
        console.debug("\u63a7\u4ef6CalloutFail\u8fd4\u56de[callId:%s,cause:%s,inboundid:%s]", f, h, this._inboundId);
        if (this.state == "stConsulting") {
            if (this._inboundId) {
                sipPhone.ChangeCurrentCall(this._inboundId);
                this._changeState("stTalking")
            }
        }
    },
    onRegister: function (j) {
    },
    register: function (k, m, l) {
        return hojo.subscribe(k, m, l)
    },
    unregister: function (n) {
        hojo.unsubscribe(n)
    },
    playSound: function () {
        try {
            document.soundPlayer.play()
        } catch (e) {
            console.debug(e)
        }
    },
    stopSound: function () {
        try {
            document.soundPlayer.stop()
        } catch (e) {
            console.debug(e)
        }
    },
    _exit: function (o, d) {
        var r = "./login.html";
        if (d == undefined) {
            d = true
        }
        ;
        var s = this;
        if (!o) {
            o = s.loginName
        }
        ;
        var t = this.proxy_url;
        var q = false;
        var p = {
            Command: "Action",
            Action: "Logoff",
            SessionID: s.uniqueId,
            ActionID: "Logoff" + Math.random(),
            QueueRemove: d,
            User: s.userId,
            PBX: s.pbx_in_ip,
            Account: s.accountId
        };
        hojo.io.script.get({
            url: t,
            content: {json: hojo.toJson(p)},
            callbackParamName: "callbackName",
            timeout: 15000,
            preventCache: true,
            load: function (v, u) {
                console.debug("\u9000\u51fa\u6210\u529f");
                console.dir(v);
                console.debug("\u6e05\u9664\u4e8b\u4ef6XHR");
                window.location = r
            },
            error: function (v, u) {
                console.debug("\u6ce8\u9500\u8fd4\u56de\u9519\u8bef");
                console.dir(v);
                q = true;
                console.debug("\u6e05\u9664\u4e8b\u4ef6XHR");
                window.location = r
            }
        })
    },
    _changeState: function (w) {
        console.debug("\u6539\u53d8\u72b6\u6001[%s]", w);
        hojo.publish("EvtBarChange", [this.extenType + "_" + w])
    },
    _eventHandler: function (x) {
        try {
            var s = this;
            hojo.forEach(x, function (y) {
                if (s._base == null) {
                    s._base = new icallcenter.stateElement.base(s)
                }
                ;
                s._base._switchState(y)
            })
        } catch (e) {
            console.dir(e)
        }
    },
    _waitEvent: function () {
        if (this._isWaitingEvent) {
            return
        }
        ;
        if (this._evtXhr == null) {
            return
        }
        ;
        this._isWaitingEvent = true;
        var s = this;
        var t = this.proxy_url;
        var p = {
            Command: "Action",
            Action: "GetState",
            ActionID: "GetState" + Math.random(),
            SessionID: this.uniqueId,
            User: this.userId
        };
        hojo.io.script.get({
            url: t,
            content: {json: hojo.toJson(p)},
            callbackParamName: "callbackName",
            timeout: 15000,
            preventCache: true,
            load: function (v, u) {
                iccCount = 0;
                s.display("");
                if (!v) {
                    return
                }
                ;
                var B = v;
                var z = B.Response;
                if (!z) {
                    z = B
                }
                ;
                if (z.Succeed && !z.HasEvent) {
                } else {
                    if (!z.Succeed) {
                        console.debug("\u4f1a\u8bdd\u5931\u6548");
                        console.dir(z);
                        s._isWaitingEvent = false;
                        if (z.Expired) {
                            console.debug("\u5f00\u59cb\u81ea\u52a8\u91cd\u8fde");
                            s._relogin();
                            return
                        } else {
                            console.debug("\u8fd4\u56de\u9519\u8bef!")
                        }
                    } else {
                        if (z.Kick) {
                            var A = "";
                            if (z.Comments) {
                                A = z.Comments
                            }
                            ;
                            if (A == "ukick" || A == "ekick") {
                                icallcenter.hojotools.error("\u60a8\u5f53\u524d\u7684\u4f1a\u8bdd\u5df2\u7ecf\u5931\u6548,\u5bfc\u81f4\u8be5\u95ee\u9898\u7684\u539f\u56e0\u662f\u522b\u7684\u5ea7\u5e2d\u4f7f\u7528\u76f8\u540c\u7684\u5e10\u53f7\uff08\u6216\u76f8\u540c\u7684\u5206\u673a\uff09\u767b\u5f55\u4e86")
                            } else {
                                icallcenter.hojotools.error("\u60a8\u5f53\u524d\u7684\u4f1a\u8bdd\u5df2\u7ecf\u5931\u6548,\u5bfc\u81f4\u8be5\u95ee\u9898\u7684\u539f\u56e0\u53ef\u80fd\u662f\u88ab\u7ba1\u7406\u5458\u5f3a\u5236\u7b7e\u51fa")
                            }
                            ;
                            console.debug(z.Message);
                            window.location = "./notify.html";
                            s.destroy();
                            return
                        } else {
                            var C = B.Event;
                            if (C != null) {
                                s._eventHandler(C)
                            }
                        }
                    }
                }
                ;
                s._isWaitingEvent = false;
                s._waitEvent()
            },
            error: function (v, u) {
                s._isWaitingEvent = false;
                window.setTimeout(function () {
                    iccCount++;
                    if (iccCount > 3) {
                        s.display("\u8fde\u63a5\u670d\u52a1\u5668\u8d85\u65f6\uff0c\u53ef\u80fd\u662f\u60a8\u7684\u7f51\u7edc\u95ee\u9898\uff0c\u6b63\u5728\u5c1d\u8bd5\u91cd\u65b0\u8fde\u63a5...")
                    }
                    ;
                    s._waitEvent()
                }, 1000);
                return
            }
        })
    },
    display: function (D) {
        var E = hojo.byId("netMessage");
        if (E) {
            E.innerHTML = D
        }
    },
    _relogin: function () {
        if (this._isRelogin) {
            return
        }
        ;
        var s = this;
        s._isRelogin = true;
        var p = {
            Command: "Action",
            Action: "Login",
            ActionID: "Login" + Math.random(),
            ExtenType: s.extenType,
            User: s.loginName,
            Password: s.password,
            AutoBusy: false,
            Monitor: s.Monitor
        };
        hojo.io.script.get({
            url: s.proxy_url,
            content: {json: hojo.toJson(p)},
            callbackParamName: "callbackName",
            timeout: 15000,
            preventCache: true,
            load: function (v, u) {
                var z = v;
                console.dir(z);
                if (!z.Succeed) {
                    var F = z.Result;
                    if (F) {
                        if (F == 601) {
                            icallcenter.hojotools.error("\u60a8\u7684\u8d26\u6237\u901a\u8bdd\u5ea7\u5e2d\u767b\u5f55\u6570\u5df2\u8fbe\u6700\u5927\u6216\u8005\u5df2\u7ecf\u5230\u671f,\u8bf7\u4f7f\u7528\u65e0\u901a\u8bdd\u65b9\u5f0f\u767b\u5f55\u6216\u8054\u7cfb\u7ba1\u7406\u5458")
                        } else {
                            if (F == 602) {
                                icallcenter.hojotools.error("\u60a8\u7684\u8d26\u6237\u65e0\u901a\u8bdd\u5ea7\u5e2d\u767b\u5f55\u6570\u5df2\u8fbe\u6700\u5927\u6216\u8005\u5df2\u7ecf\u5230\u671f,\u8bf7\u4f7f\u7528\u8f6f\u7535\u8bdd/\u7f51\u5173/\u76f4\u7ebf\u65b9\u5f0f\u767b\u5f55\u6216\u8054\u7cfb\u7ba1\u7406\u5458")
                            } else {
                                icallcenter.hojotools.error("\u767b\u5f55\u5931\u8d25" + F + ",\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458")
                            }
                        }
                    } else {
                        icallcenter.hojotools.error("\u60a8\u5f53\u524d\u7684\u4f1a\u8bdd\u5df2\u7ecf\u5931\u6548,\u5bfc\u81f4\u8be5\u95ee\u9898\u7684\u539f\u56e0\u53ef\u80fd\u662f\u88ab\u7ba1\u7406\u5458\u5f3a\u5236\u7b7e\u51fa")
                    }
                    ;
                    window.location = "./notify.html";
                    s.destroy()
                } else {
                    if (z.SessionID) {
                        console.debug("\u91cd\u65b0\u767b\u5f55\u6210\u529f");
                        s.uniqueId = z.SessionID;
                        var G = new Date();
                        var H = G.valueOf();
                        s.currentServerTime = H - z.Timestamp * 1000;
                        if (s._peerState._curPeerStateKey == "0") {
                            s.setBusy(false, s._peerState._curPeerStateKey)
                        } else {
                            if (s._peerState._curPeerStateKey != "99") {
                                s.setBusy(true, s._peerState._curPeerStateKey)
                            }
                        }
                        ;
                        s._waitEvent()
                    } else {
                        console.debug("\u767b\u5f55\u6210\u529f,\u4f46\u6ca1\u6709sessionid")
                    }
                }
                ;
                s._isRelogin = false
            },
            error: function (v, u) {
                console.debug("ACTION\u8fd4\u56de\u9519\u8bef[%s]", v)
            }
        })
    },
    dialout: function (K) {
        console.debug("\u547c\u53eb\uff1a" + K);
        var s = this;
        var I = "";
        if (K.length < 5) {
            var J = this._base._getUserViaExten(K);
            if (!J) {
                K = "9" + K;
                I = "dialout";
                s.callObject.originCallNo = s.didNum;
                s.callObject.originCalledNo = K
            } else {
                I = "inner"
            }
        } else {
            K = "9" + K;
            I = "dialout";
            s.callObject.originCallNo = s.didNum;
            s.callObject.originCalledNo = K
        }
        ;
        this._sendAction("Originate", {
            Channel: "SIP/" + s.sipNo,
            Context: s.accountId,
            Exten: K,
            Priority: "1",
            UserID: s.userId,
            Timeout: 60000,
            Async: "true",
            CallType: I
        }, function (v, u) {
            var L = v;
            console.dir(v);
            if (L.Succeed) {
                console.debug("\u5916\u547c[%s]\u6210\u529f", K)
            } else {
                console.debug("\u5916\u547c\u5931\u8d25");
                console.dir(L);
                if (L.Expired) {
                    s._relogin()
                }
            }
        })
    },
    batchDialout: function (K, Q) {
        console.debug("\u6279\u91cf\u547c\u53eb\uff1a" + K);
        var s = this;
        if (K && Q) {
            var P = Q.context;
            var R = Q.type;
            var O = Q.callbackSuccFun;
            var M = Q.callbackFailFun;
            var N = Q.callbackObj;
            this._sendAction("BatchDialout", {
                BatchDialoutType: R,
                Context: P,
                CallNumber: K,
                Timeout: 120000,
                Async: "true"
            }, function (v, u) {
                var L = v;
                if (L.Succeed) {
                    console.debug("\u6279\u91cf\u5916\u547c[%s]\u6210\u529f", K);
                    O.call(N, v.Message)
                } else {
                    M.call(N, 0)
                }
            }, function (v, u) {
                console.debug("\u6279\u91cf\u5916\u547c[%s]\u5931\u8d25", K);
                M.call(N, 0)
            })
        } else {
            console.debug("\u6279\u91cf\u5916\u547c\u53f7\u7801\u4e0d\u5b58\u5728\u3002")
        }
    },
    playDTMF: function (S) {
        console.debug("play DTMF[%s]", S);
        sipPhone.PlayDTMF(S)
    },
    answer: function () {
    },
    hangup: function () {
        var s = this;
        console.debug("\u6302\u65ad\u7535\u8bdd\uff1a" + s.extenType);
        this._sendAction("Hangup", {Channel: s._curChannel}, function (v, u) {
            var L = v;
            console.dir(L);
            if (L.Succeed) {
                console.debug("\u6302\u673a\u52a8\u4f5c\u6210\u529f")
            } else {
                console.debug("\u6302\u673a\u52a8\u4f5c\u5931\u8d25");
                s.callObject = {};
                if (L.Expired) {
                    s._relogin()
                }
            }
        })
    },
    hold: function () {
        var s = this;
        this._sendAction("Hold", {Channel: s._otherChannel, UserID: s.userId, Async: "true"}, function (v, u) {
            var L = v;
            console.dir(v);
            if (L.Succeed) {
                console.debug("\u4fdd\u6301\u6210\u529f");
                s._stateBeforeHold = s._base._curCallState._callState;
                s._changeState("stHold")
            } else {
                console.debug("\u4fdd\u6301[%s]\u5931\u8d25" + v.Message, s._curChannel);
                if (L.Expired) {
                    s._relogin()
                }
            }
        })
    },
    unhold: function () {
        var s = this;
        this._sendAction("Unhold", {Channel: s._otherChannel, UserID: s.userId, Async: "true"}, function (v, u) {
            var L = v;
            console.dir(v);
            if (L.Succeed) {
                console.debug("\u53d6\u6d88\u4fdd\u6301\u6210\u529f");
                s._changeState(s._stateBeforeHold)
            } else {
                console.debug("\u53d6\u6d88\u4fdd\u6301[%s]\u5931\u8d25" + v.Message, s._curChannel);
                if (L.Expired) {
                    s._relogin()
                }
            }
        })
    },
    investigate: function () {
        if (this._isInvestigatting) {
            return
        } else {
            this._isInvestigatting = true
        }
        ;
        console.info("\u8f6c\u6ee1\u610f\u5ea6\u8c03\u67e5");
        console.dir(this.callObject);
        var s = this;
        var T = s.userId;
        var P = s.accountId + "-investigate";
        s._sendAction("Transfer", {
            Exten: "s",
            Channel: s._otherChannel,
            CallType: "investigate",
            CallType: "investigate",
            UserID: T,
            Context: P
        }, function (v, u) {
            console.dir(v);
            var L = v;
            if (L.Succeed) {
                console.debug("\u8f6c\u6ee1\u610f\u5ea6\u8c03\u67e5\u6210\u529f")
            } else {
                console.debug("\u8f6c\u6ee1\u610f\u5ea6\u8c03\u67e5\u5931\u8d25");
                s._isInvestigatting = false;
                if (L.Expired) {
                    s._relogin()
                }
            }
        });
        this._isInvestigatting = false
    },
    transfer: function (V, X, Z) {
        var s = this;
        console.info("\u7535\u8bdd\u8f6c\u63a5[%s:%s]", V, X);
        var W = s.callObject.originCallNo;
        if (V.substr(0, 1) == "9" && X == "external") {
            if (V.length <= 5) {
                V = V.substr(1);
                var J = this._base._getUserViaExten(V);
                if (!J) {
                    V = "9" + V
                }
            }
        }
        ;
        var Y = hojo.objectToQuery(Z);
        console.debug(Y);
        var P = "";
        P = this.accountId;
        var ba;
        var U;
        if (s.callObject && s.callObject.data) {
            ba = s.callObject.data.workSheetId;
            U = s.callObject.data.customerId
        }
        ;
        s._sendAction("Transfer", {
            WorkSheetID: ba ? ba : "",
            CustomerID: U ? U : "",
            Exten: V,
            UserID: s.userId,
            Channel: s._otherChannel,
            ExtraChannel: s._curChannel,
            Context: P
        }, function (v, u) {
            console.dir(v);
            var L = v;
            if (L.Succeed) {
            } else {
                console.debug("\u8f6c\u63a5\u5931\u8d25-" + L.Message);
                var D = "";
                if (L.Message == "310") {
                    D = "\u672a\u914d\u7f6e\u5916\u547c\u7ebf\u8def"
                } else {
                    if (L.Message == "311") {
                        D = "\u8f6c\u63a5\u7684\u7528\u6237\u5fd9"
                    } else {
                        if (L.Message == "312") {
                            D = "\u8f6c\u63a5\u7684\u7528\u6237\u672a\u7b7e\u5165"
                        } else {
                            if (L.Message == "313") {
                                D = "\u8f6c\u63a5\u7684\u7528\u6237\u6b63\u5728\u901a\u8bdd"
                            } else {
                                if (L.Message == "314") {
                                    D = "\u8f6c\u63a5\u7684\u7528\u6237\u6ca1\u6709\u4ee5\u901a\u8bdd\u65b9\u5f0f\u767b\u5f55"
                                } else {
                                    if (L.Message == "315") {
                                        D = "\u65e0\u6cd5\u547c\u901a\u8f6c\u63a5\u7684\u7528\u6237"
                                    } else {
                                        if (L.Message == "316") {
                                            D = "\u8f6c\u63a5\u7528\u6237\u4e0d\u5b58\u5728"
                                        } else {
                                            D = ""
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                ;
                if (D == "") {
                    icallcenter.hojotools.error("\u8f6c\u63a5\u5931\u8d25")
                } else {
                    icallcenter.hojotools.error("\u8f6c\u63a5\u5931\u8d25\uff1a" + D)
                }
                ;
                if (L.Expired) {
                    s._relogin()
                }
            }
        }, function (v, u) {
            icallcenter.hojotools.close();
            console.debug("ACTION\u8fd4\u56de\u9519\u8bef");
            console.dir(v)
        })
    },
    phone_cancelTransfer: function () {
        var s = this;
        if (s._otherChannel) {
            var p = {Channel: s._otherChannel};
            var bc = function (v) {
                icallcenter.hojotools.close();
                if (v.Succeed) {
                    icallcenter.hojotools.showSucc("\u53d6\u6d88\u8f6c\u63a5\u6210\u529f")
                } else {
                    icallcenter.hojotools.error("\u53d6\u6d88\u8f6c\u63a5\u5931\u8d25");
                    if (v.Expired) {
                        s._relogin()
                    }
                }
            };
            var bb = function () {
                icallcenter.hojotools.close();
                icallcenter.hojotools.error("\u53d6\u6d88\u8f6c\u63a5\u5931\u8d25")
            };
            s._sendAction("CancelTransfer", p, bc, bb)
        }
    },
    listen: function (bd) {
        var s = this;
        if (this._peerState._curPeerStateKey == "0") {
            icallcenter.hojotools.error("\u8bf7\u5148\u5c06\u7535\u8bdd\u7f6e\u4e3a\u5fd9\u788c");
            return
        }
        ;
        this._sendAction("Originate", {
            Channel: "SIP/" + s.sipNo,
            Application: "ChanSpy",
            Data: bd + "|bq",
            UserID: s.userId,
            Callerid: s.sipNo,
            Async: "true"
        }, function (v, u) {
            var L = v;
            console.dir(v);
            if (L.Succeed) {
                console.debug("\u76d1\u542c[%s]\u6210\u529f", bd);
                s._otherChannel = bd
            } else {
                console.debug("\u76d1\u542c[%s]\u5931\u8d25" + v.Message, bd);
                if (L.Expired) {
                    s._relogin()
                }
            }
        })
    },
    hangupChannel: function (bd) {
        var s = this;
        console.debug("\u5f3a\u62c6" + bd);
        var be = {Channel: bd};
        var bc = function (v, u) {
            console.dir(v);
            var L = v;
            if (L.Succeed) {
                console.debug("\u5f3a\u62c6\u52a8\u4f5c\u6210\u529f")
            } else {
                console.debug("\u5f3a\u62c6\u52a8\u4f5c\u5931\u8d25");
                if (L.Expired) {
                    s._relogin()
                }
            }
        };
        if (this._destroyed) {
            return
        }
        ;
        var p = {};
        var t = this.proxy_url;
        hojo.mixin(p, {
            Command: "Action",
            Action: "Hangup",
            ActionID: "ForceHangup" + Math.random(),
            PBX: this.pbx_in_ip,
            Account: this.accountId,
            SessionID: this.uniqueId
        });
        hojo.mixin(p, be);
        if (bc == null) {
            bc = function (v, u) {
                console.debug("ACTION\u8c03\u7528\u6210\u529f[%s]", v)
            }
        }
        ;
        console.debug("\u53d1\u9001ACTION");
        console.dir(p);
        hojo.io.script.get({
            url: t,
            content: {json: hojo.toJson(p)},
            callbackParamName: "callbackName",
            timeout: 60000,
            preventCache: true,
            load: bc,
            error: function (v, u) {
                console.debug("ACTION\u8fd4\u56de\u9519\u8bef");
                console.dir(v)
            }
        })
    },
    lootCall: function (bd) {
        var s = this;
        if (this._peerState._curPeerStateKey == "0") {
            icallcenter.hojotools.error("\u8bf7\u5148\u5c06\u7535\u8bdd\u7f6e\u4e3a\u5fd9\u788c");
            return
        }
        ;
        var P = this.accountId;
        console.debug("\u62a2\u63a5" + bd);
        s._sendAction("Transfer", {
            Exten: s.sipNo,
            Channel: bd,
            UserID: s.userId,
            CallType: "Loot",
            Context: P
        }, function (v, u) {
            console.dir(v);
            var L = v;
            if (L.Succeed) {
                console.debug("\u62a2\u63a5\u6210\u529f");
                s._isLooting = true
            } else {
                console.debug("\u62a2\u63a5\u5931\u8d25");
                if (L.Expired) {
                    s._relogin()
                }
            }
        })
    },
    kick: function (bf) {
        console.debug("\u7b7e\u51fa\u5ea7\u5e2d" + bf);
        var s = this;
        this._sendAction("Kick", {Exten: bf}, function (v, u) {
            var L = v;
            console.dir(v);
            if (L.Succeed) {
                console.debug("\u7b7e\u51fa[%s]\u6210\u529f", bf);
                var J = s.monitorPeers[bf];
                if (J) {
                    J.C5Status = "";
                    J.callNo = "";
                    J.callStatus = "Idle";
                    var G = new Date();
                    var H = G.valueOf();
                    J.timestamp = H / 1000;
                    linked = false;
                    J.channel = "";
                    J.linkedChannel = "";
                    console.debug("kickoff");
                    hojo.publish("EvtMonitorPeer", [J]);
                    s._base._updateQueueInfo()
                }
            } else {
                console.debug("\u7b7e\u51fa[%s]\u5931\u8d25" + v.Message, bf);
                if (L.Expired) {
                    s._relogin()
                }
            }
        })
    },
    pick: function (T) {
        console.debug("\u7b7e\u5165\u5ea7\u5e2d" + T);
        var s = this;
        var J = s.monitorPeers[T];
        if (J == null || J.localNo == null || J.localNo == "") {
            icallcenter.hojotools.error("\u4e0d\u80fd\u5bf9\u6ca1\u6709\u76f4\u7ebf\u53f7\u7801\u7684\u5ea7\u5e2d\u505a\u7b7e\u5165\u64cd\u4f5c");
            return
        }
        ;
        this._sendAction("SignIn", {User: T}, function (v, u) {
            var L = v;
            console.dir(v);
            if (L.Succeed) {
                console.debug("\u7b7e\u5165[%s]\u6210\u529f", T);
                icallcenter.hojotools.showSucc("\u5ea7\u5e2d\u7b7e\u5165\u6210\u529f")
            } else {
                console.debug("\u7b7e\u5165[%s]\u5931\u8d25" + L.Message, T);
                if (L.Expired) {
                    s._relogin()
                }
            }
        })
    },
    threeWayCall: function (K) {
        console.debug("\u5f00\u59cb\u4e09\u65b9\u901a\u8bdd\uff1a" + K);
        var s = this;
        this._sendAction("ThreeWayCall", {
            FromExten: s.sipNo,
            Exten: K,
            UserID: s.userId,
            Timeout: 60000
        }, function (v, u) {
            var L = v;
            console.dir(L);
            if (L.Succeed) {
                console.debug("\u4e09\u65b9\u901a\u8bdd[%s]\u6210\u529f", K);
                s._changeState("stThreeWayTalking")
            } else {
                console.debug("\u4e09\u65b9\u901a\u8bdd\u5931\u8d25");
                if (L.Expired) {
                    s._relogin()
                }
                ;
                icallcenter.hojotools.error("\u4e09\u65b9\u901a\u8bdd\u5931\u8d25")
            }
        })
    },
    consult: function (K, X) {
        console.debug("\u5f00\u59cb\u54a8\u8be2\u901a\u8bdd\uff1a" + K);
        var s = this;
        if (K.substr(0, 1) == "9" && X == "external") {
            if (K.length <= 5) {
                K = K.substr(1);
                var J = this._base._getUserViaExten(K);
                if (!J) {
                    K = "9" + K
                }
            }
        }
        ;
        if (K.length > 4 && X != "skillgroup") {
            icallcenter.hojotools.showLoading(K);
            fromCid = this.sipNo + "@" + this.didNum
        } else {
            if (K.length <= 4 && X != "skillgroup") {
                if (K.substr(0, 1) != "9") {
                    icallcenter.hojotools.showLoading("\u5de5\u53f7  " + K + " ")
                } else {
                    icallcenter.hojotools.showLoading(K)
                }
            } else {
                if (X == "skillgroup") {
                    icallcenter.hojotools.showLoading(K)
                }
            }
        }
        ;
        this._sendAction("Consult", {FromExten: s.sipNo, Exten: K, UserID: s.userId, Timeout: 60000}, function (v, u) {
            icallcenter.hojotools.close();
            var L = v;
            console.dir(L);
            if (L.Succeed) {
                console.debug("\u54a8\u8be2[%s]\u6210\u529f", K);
                icallcenter.hojotools.showSucc("\u54a8\u8be2\u6210\u529f");
                s._changeState("stConcultTalking")
            } else {
                icallcenter.hojotools.error("\u54a8\u8be2\u5931\u8d25");
                console.debug("\u54a8\u8be2\u5931\u8d25");
                if (L.Expired) {
                    s._relogin()
                }
            }
        }, function (v, u) {
            icallcenter.hojotools.close();
            icallcenter.hojotools.error("\u54a8\u8be2\u5931\u8d25");
            console.debug("ACTION\u8fd4\u56de\u9519\u8bef");
            console.dir(v)
        })
    },
    stopConsult: function () {
        console.debug("\u7ed3\u675f\u54a8\u8be2\u901a\u8bdd");
        var s = this;
        this._sendAction("StopConsult", {FromExten: s.sipNo, UserID: s.userId, Timeout: 60000}, function (v, u) {
            var L = v;
            console.dir(L);
            if (L.Succeed) {
                if (v.Message != undefined) {
                    if (v.Message == "Idle") {
                        s._changeState("stInvalid")
                    } else {
                        s._changeState("stTalking")
                    }
                } else {
                    s._changeState("stTalking")
                }
            } else {
                console.debug("\u7ed3\u675f\u54a8\u8be2\u5931\u8d25");
                if (L.Expired) {
                    s._relogin()
                }
                ;
                icallcenter.hojotools.error("\u7ed3\u675f\u54a8\u8be2\u901a\u8bdd\u5931\u8d25")
            }
        })
    },
    setBusy: function (bh, bg) {
        if (this._isSettingbusy) {
            return
        } else {
            this._isSettingbusy = true
        }
        ;
        var s = this;
        var be = {"Exten": s.userId, Busy: bh, BusyType: "" + bg};
        s._sendAction("Busy", be, function (v, u) {
            var bi = v;
            if (bi.Succeed) {
                console.debug("\u4fee\u6539\u72b6\u6001\u6210\u529f")
            } else {
                if (bi.Expired) {
                    s._relogin()
                }
            }
        });
        this._isSettingbusy = false
    },
    toIVR: function () {
        console.info("\u8f6cIVR\u9a8c\u8bc1");
        var s = this;
        var P = userInfo.accountId + "-validate";
        var bj = "Validate";
        if (userInfo.accountId == "Q000000003893" || userInfo.accountId == "B000000006069") {
            bj = "Check"
        }
        ;
        s._sendAction(bj, {Exten: "s", Channel: s._otherChannel, Context: P}, function (v, u) {
            console.dir(v);
            var L = v;
            if (L.Succeed) {
                console.debug("\u8f6cIVR\u6210\u529f")
            } else {
                console.debug("\u8f6cIVR\u5931\u8d25");
                if (L.Expired) {
                    s._relogin()
                }
                ;
                return ERR_NO_SUCH_CHANNEL
            }
        });
        this._isInvestigatting = false;
        return SUCCESS
    },
    phone_toMenu: function (bf) {
        var bj = "IvrMenu";
        var p = {Channel: this._otherChannel, Context: bf};
        phone._sendAction(bj, p, function (v) {
            console.dir(v);
            if (v.Succeed) {
                console.log("\u6765\u7535\u6b63\u5728\u8f6c\u9a8c\u8bc1\uff0c\u8bf7\u7a0d\u540e");
                icallcenter.hojotools.showSucc("\u6765\u7535\u6b63\u5728\u8f6c\u9a8c\u8bc1\uff0c\u8bf7\u7a0d\u540e")
            } else {
                console.log("\u8f6cIVR\u83dc\u5355\u5931\u8d25");
                icallcenter.hojotools.showSucc("\u8f6cIVR\u83dc\u5355\u5931\u8d25");
                if (json.Expired) {
                    self._relogin()
                }
            }
        }, function () {
            console.log("\u8f6cIVR\u83dc\u5355\u5931\u8d25");
            icallcenter.hojotools.showSucc("\u8f6cIVR\u83dc\u5355\u5931\u8d25")
        })
    },
    changePhone: function (bl, bk) {
        console.debug("\u53d8\u66f4\u767b\u5f55\u65b9\u5f0f[" + bl + "-" + bk + "]");
        var s = this;
        if (bl == "Local" || bl == "gateway") {
            if (s.extenType == "sip") {
                sipPhone.UnInitNoFire()
            }
            ;
            s.extenType = bl;
            s.sipNo = bk
        } else {
            return
        }
        ;
        s._sendAction("SetExtenType", {User: s.userId, ExtenType: bl, LoginExten: bk}, function (v, u) {
            console.dir(v);
            var L = v;
            if (L.Succeed) {
                console.debug("\u53d8\u66f4\u767b\u5f55\u65b9\u5f0f\u6210\u529f")
            } else {
                console.debug("\u53d8\u66f4\u767b\u5f55\u65b9\u5f0f\u5931\u8d25");
                if (L.Expired) {
                    s._relogin()
                }
            }
        })
    },
    _sendAction: function (bm, be, bc, bn) {
        if (this._destroyed) {
            return
        }
        ;
        var p = {};
        var t = this.proxy_url;
        hojo.mixin(p, {
            Command: "Action",
            Action: bm,
            ActionID: bm + Math.random(),
            PBX: this.pbx_in_ip,
            Account: this.accountId,
            SessionID: this.uniqueId
        });
        hojo.mixin(p, be);
        var bo = 60000;
        if (be.Timeout != "undefined") {
            bo = be.Timeout
        }
        ;
        if (bc == null) {
            bc = function (v, u) {
                console.debug("ACTION\u8c03\u7528\u6210\u529f[%s]", v)
            }
        }
        ;
        if (bn == null) {
            bn = function (v, u) {
                console.debug("ACTION\u8fd4\u56de\u9519\u8bef");
                console.dir(v)
            }
        }
        ;
        console.debug("\u53d1\u9001ACTION");
        console.dir(p);
        hojo.io.script.get({
            url: t,
            content: {json: hojo.toJson(p)},
            callbackParamName: "callbackName",
            timeout: bo,
            preventCache: true,
            load: bc,
            error: bn
        })
    },
    onAutoBusyTimeChanged: function (bp) {
        this.autoBusyTime = bp
    }
});
icallcenter.Phone.registerEvent = function (a) {
    var q = null;
    var t = a.proxy_url;
    var p = {
        Command: "Action",
        Action: "Login",
        ActionID: "Login" + Math.random(),
        ExtenType: a.extenType,
        Password: a.password,
        BusyType: a.busyType,
        Monitor: a.Monitor,
        User: a.User
    };
    var bq = this;
    hojo.io.script.get({
        url: t,
        content: {json: hojo.toJson(p)},
        callbackParamName: "callbackName",
        timeout: 15000,
        preventCache: true,
        load: function (v, u) {
            var z = v;
            console.dir(z);
            if (!z.Succeed) {
                q = false;
                var F = z.Result;
                if (F) {
                    if (F == 601) {
                        icallcenter.hojotools.error("\u60a8\u7684\u8d26\u6237\u901a\u8bdd\u5ea7\u5e2d\u767b\u5f55\u6570\u5df2\u8fbe\u6700\u5927\u6216\u8005\u5df2\u7ecf\u5230\u671f,\u8bf7\u4f7f\u7528\u65e0\u901a\u8bdd\u65b9\u5f0f\u767b\u5f55\u6216\u8054\u7cfb\u7ba1\u7406\u5458")
                    } else {
                        if (F == 602) {
                            icallcenter.hojotools.error("\u60a8\u7684\u8d26\u6237\u65e0\u901a\u8bdd\u5ea7\u5e2d\u767b\u5f55\u6570\u5df2\u8fbe\u6700\u5927\u6216\u8005\u5df2\u7ecf\u5230\u671f,\u8bf7\u4f7f\u7528\u8f6f\u7535\u8bdd/\u7f51\u5173/\u76f4\u7ebf\u65b9\u5f0f\u767b\u5f55\u6216\u8054\u7cfb\u7ba1\u7406\u5458")
                        } else {
                            icallcenter.hojotools.error("\u767b\u5f55\u5931\u8d25" + F + ",\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458")
                        }
                    }
                }
                ;
                bq.logonStatus = "logonFail"
            } else {
                if (z.SessionID) {
                    a.uniqueId = z.SessionID;
                    var G = new Date();
                    var H = G.valueOf();
                    a.currentServerTime = H - z.Timestamp * 1000;
                    a.PhonebarConfig = z.PhonebarConfig;
                    a.autoBusyTime = z.AutoBusyTime;
                    a.userId = z.UserID;
                    a.pbx_in_ip = z.PBX;
                    a.accountId = z.Account;
                    a.loginName = a.User;
                    a.sipNo = z.SipNum;
                    q = new icallcenter.Phone(a);
                    phone = q;
                    icallcenter.logon.afterPhone(phone);
                    bq.logonStatus = "logonSuccess"
                } else {
                    bq.logonStatus = "logonFail";
                    q = false
                }
            }
            ;
            hojo.publish("EvtLogon", [bq.logonStatus]);
            return v
        },
        error: function (v, u) {
            icallcenter.hojotools.error("\u8bf7\u6c42\u8d85\u65f6\uff0c\u8bf7\u68c0\u67e5\u672c\u5730\u7f51\u7edc");
            console.debug("\u6ce8\u518cass\u5931\u8d25");
            console.dir(v);
            bq.logonStatus = "logonFail";
            hojo.publish("EvtLogon", [bq.logonStatus]);
            return v
        }
    })
}